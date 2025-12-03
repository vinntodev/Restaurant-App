# Sistem Manajemen Restoran

Aplikasi konsol berbasis Java untuk mengelola menu restoran dan memproses pesanan pelanggan. Aplikasi ini menyediakan fitur lengkap untuk menambah item menu, menerima pesanan, menghitung total biaya, dan menyimpan struk pesanan.

## 📋 Fitur

- ✅ **Manajemen Menu**
  - Tambah item menu baru (Makanan, Minuman, atau Item dengan Diskon)
  - Tampilkan daftar menu restoran
  - Simpan menu ke file

- ✅ **Manajemen Pesanan**
  - Terima pesanan dari pelanggan
  - Hitung total biaya pesanan
  - Tampilkan struk pesanan
  - Simpan struk ke file dengan timestamp

- ✅ **Tipe Item Menu**
  - **Makanan**: Item makanan dengan kategori (Nusantara, Western, Chinese, dll)
  - **Minuman**: Item minuman dengan kategori (Dingin, Panas, Segar, dll)
  - **Diskon**: Item dengan persentase diskon otomatis

## 🛠️ Teknologi

- **Java** (menggunakan fitur Java modern seperti enhanced switch expressions dan text blocks)
- **Java Collections** (ArrayList)
- **Java I/O** (FileWriter, BufferedWriter)
- **Java Time API** (untuk timestamp pada struk)

## 📦 Persyaratan

- Java Development Kit (JDK) 17 atau lebih tinggi
- Terminal/Command Prompt untuk menjalankan aplikasi

## 🚀 Cara Menjalankan

### 1. Kompilasi Program

```bash
javac RestoranApp.java
```

### 2. Jalankan Aplikasi

```bash
java RestoranApp
```

## 📖 Cara Penggunaan

Setelah aplikasi dijalankan, Anda akan melihat menu utama dengan 8 pilihan:

1. **Tambahkan Item Baru ke Menu**
   - Pilih tipe item (Makanan/Minuman/Item dengan Diskon)
   - Masukkan nama, harga, dan informasi tambahan sesuai tipe

2. **Tampilkan Menu Restoran**
   - Menampilkan semua item menu yang tersedia

3. **Menerima Pesanan Pelanggan**
   - Masukkan nama pelanggan
   - Pilih item dari menu dengan memasukkan nomor
   - Masukkan 0 untuk menyelesaikan pesanan

4. **Hitung Total Biaya Pesanan**
   - Menampilkan total biaya dari pesanan yang sedang dibuat

5. **Tampilkan Struk Pesanan**
   - Menampilkan detail lengkap pesanan dalam format struk

6. **Simpan Menu ke File**
   - Menyimpan daftar menu ke file `menu.txt`

7. **Simpan Struk Pesanan ke File**
   - Menyimpan struk ke file dengan format `struk_YYYYMMDD_HHMMSS.txt`
   - Pesanan akan di-reset setelah disimpan

8. **Keluar**
   - Keluar dari aplikasi

## 📁 Struktur Proyek

```
AppRestoran/
├── RestoranApp.java    # File utama aplikasi
└── Readme.md           # Dokumentasi proyek
```

## 🏗️ Struktur Kelas

Aplikasi ini menggunakan nested classes dalam `RestoranApp`:

- **MenuItem** (abstract): Kelas dasar untuk semua item menu
- **Makanan**: Kelas untuk item makanan
- **Minuman**: Kelas untuk item minuman
- **Diskon**: Kelas untuk item dengan diskon
- **Menu**: Kelas untuk mengelola daftar menu
- **Pesanan**: Kelas untuk mengelola pesanan pelanggan

## 📝 Catatan

- Menu default akan dimuat saat pertama kali aplikasi dijalankan jika file menu tidak ditemukan
- Menu default berisi:
  - Nasi Goreng (Rp 25.000)
  - Es Teh (Rp 8.000)
  - Ayam Bakar Promo dengan diskon 10% (Rp 40.000)

## 👨‍💻 Pengembang

Dikembangkan sebagai proyek pembelajaran Java dengan fokus pada:
- Object-Oriented Programming (OOP)
- Exception Handling
- File I/O Operations
- Java Collections Framework

---

**Selamat menggunakan Sistem Manajemen Restoran!** 🍽️

