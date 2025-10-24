# RESPONSI 1 MOBILE (H1D023001)

Nama : Athaya Raihan Annafi <br>
NIM : H1D023001 <br>
Shift Baru : A <br>
Shift Asal : F

# DEMO APLIKASI
![Responsi](demo/DemoAplikasi.mp4)

# PENJELASAN ALUR DATA
## Alur Aplikasi

Aplikasi ini menggunakan arsitektur MVVM dengan dukungan Retrofit, LiveData/StateFlow, dan ViewBinding untuk mengelola alur data secara terstruktur.

Berikut penjelasan alur datanya dalam 4 langkah utama:

### 1️⃣ Pemanggilan Data dari API

- Aplikasi mengambil data dari Football-Data.org API untuk mendapatkan informasi klub (team), pelatih (coach), dan skuad pemain (squad).
- Pemanggilan API didefinisikan pada interface Retrofit di file `app/src/main/java/.../data/network/OpenLibraryApi.kt`.

Contoh definisi endpoint Retrofit:

```kotlin
@GET("teams/{id}")
fun getTeamDetail(
	@Path("id") teamId: Int,
	@Header("X-Auth-Token") token: String
): Call<SearchResponse>
```

> Catatan: pada implementasi yang direkomendasikan, header `X-Auth-Token` bisa ditambahkan secara global lewat OkHttp interceptor di `RetrofitInstance` sehingga tidak perlu dilewatkan setiap pemanggilan.

### 2️⃣ Pengelolaan Data di Repository

- `TeamRepository` (atau `PlayerRepository` / `CoachRepository`) bertugas melakukan request asynchronous ke API.
- Jika respon berhasil, Retrofit + Gson converter otomatis mengubah JSON menjadi objek Kotlin (misal `SearchResponse` atau `Club`).
- Repository mem-publish hasilnya ke `MutableLiveData` atau `StateFlow` agar `ViewModel` dapat mengamatinya.

Contoh penggunaan `enqueue` dengan callback (pendekatan berbasis `Call`):

```kotlin
RetrofitInstance.api.getTeamDetail(teamId, token)
	.enqueue(object : Callback<SearchResponse> {
		override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
			if (response.isSuccessful && response.body() != null) {
				_teamData.postValue(response.body())
			} else {
				_error.postValue("Gagal memuat data: ${response.message()}")
			}
		}

		override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
			_error.postValue(t.message ?: "Terjadi kesalahan jaringan")
		}
	})
```

> Catatan: banyak bagian kode di repo juga memakai `suspend` + Coroutines (`viewModelScope.launch`) untuk penanganan asynchronous yang lebih bersih.

### 3️⃣ Distribusi Data ke ViewModel

- `TeamViewModel` bertindak sebagai penghubung antara UI dan Repository.
- ViewModel mengekspos `LiveData` atau `StateFlow` dari repository sehingga Activity/Fragment dapat mengamatinya.

Contoh sederhana `TeamViewModel`:

```kotlin
class TeamViewModel : ViewModel() {
	private val repository = TeamRepository()

	val teamData: LiveData<SearchResponse> = repository.teamData
	val error: LiveData<String> = repository.error

	fun loadTeam(teamId: Int, token: String) {
		repository.getTeamDetail(teamId, token)
	}
}
```

Karena `LiveData`/`StateFlow` bersifat reaktif, setiap kali repository mem-publish update, ViewModel meneruskan perubahan tersebut ke UI.

### 4️⃣ Penyajian Data di Layar

- Activity / Fragment mengamati `LiveData`/`StateFlow` pada `ViewModel`.
- `HeadCoachActivity` atau `CoachActivity` menampilkan data pelatih (nama, tanggal lahir, kebangsaan) dari objek `Coach`.
- `TeamSquadActivity` atau `PlayerActivity` menampilkan daftar pemain (`squad`) menggunakan `RecyclerView` dan `PlayerAdapter`.
  - Adapter mengikat setiap `Player` ke layout item (`list_player.xml`) dan memberi warna kartu berdasarkan posisi (Goalkeeper, Defence, Midfield, Offence).
  - Saat item pemain ditekan, Activity memanggil `PlayerDetailFragment` sebagai `BottomSheetDialogFragment` yang muncul dari bawah, menampilkan `name`, `dateOfBirth`, `nationality`, `position`, dan gambar penampil (menggunakan drawable `ic_player`).

Contoh pemanggilan dan observasi di `PlayerActivity`:

```kotlin
// memanggil load
viewModel.fetchPlayers(apiToken)

// mengamati data
lifecycleScope.launch {
	viewModel.players.collect { playerList ->
		adapter.updateData(playerList)
	}
}
```

## Catatan penting dan rekomendasi

- Penyimpanan API Key: jangan commit API key ke repository. Gunakan `local.properties` dan injeksikan ke `BuildConfig` (via Gradle) untuk keamanan.
- Interceptor: lebih baik menambahkan header `X-Auth-Token` di `RetrofitInstance` melalui OkHttp interceptor sehingga tidak perlu menambah parameter header di setiap method Retrofit.
- Error handling: perbaiki repository untuk mengembalikan `Result` atau status (Loading / Success / Error) agar UI bisa menampilkan loading spinner atau pesan error.
- Passing data ke Fragment: saat ini contoh Book menggunakan konstruktor BottomSheet class; lebih aman menggunakan `Bundle` dengan `Parcelable` (mis. membuat `Player: Parcelable`) agar Android dapat mengembalikan state fragment saat konfigurasi berubah.

---

Tambahkan bagian ini di bawah header `PENJELASAN ALUR DATA` pada `README.md` Anda. Jika ingin, saya bisa juga menambahkan contoh langkah-langkah untuk menaruh API key di `local.properties` dan mengubah `app/build.gradle.kts` agar menghasilkan `BuildConfig.FOOTBALL_API_TOKEN`.
