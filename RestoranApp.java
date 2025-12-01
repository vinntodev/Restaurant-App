import java.util.Scanner;
import java.io.IOException;

public class RestoranApp {
    private static final Scanner input = new Scanner(System.in);
    private static final Menu menuRestoran = new Menu();
    private static Pesanan pesananSekarang = null;

    public static void main(String[] args) {
        tampilkanHeader();
        inisialisasiMenu();

        int pilihan;
        do {
            tampilkanMenuUtama();
            pilihan = inputPilihanMenu();

            try {
                prosesMenu(pilihan);
            } catch (Exception e) {
                System.out.println("✗ Terjadi kesalahan: " + e.getMessage());
            }

        } while (pilihan != 8);

        input.close();
    }

    private static void tampilkanHeader() {
        var garisBatas = "=".repeat(70);
        System.out.println("""
            
            %s
                    SELAMAT DATANG DI SISTEM MANAJEMEN RESTORAN
            %s
            """.formatted(garisBatas, garisBatas));
    }

    // Inisialisasi menu
    private static void inisialisasiMenu() {
        try {
            menuRestoran.muatDariFile();
        } catch (IOException e) {
            System.out.println("⚠ Tidak dapat memuat menu dari file. Menggunakan menu default.");
            menuRestoran.isiMenuDefault();
        }
    }

    // Tampilkan menu utama dengan text block
    private static void tampilkanMenuUtama() {
        var garisBatas = "=".repeat(70);
        System.out.println("""
            
            %s
                                       MENU UTAMA
            %s
              1. Tambahkan Item Baru ke Menu
              2. Tampilkan Menu Restoran
              3. Menerima Pesanan Pelanggan
              4. Hitung Total Biaya Pesanan
              5. Tampilkan Struk Pesanan
              6. Simpan Menu ke File
              7. Simpan Struk Pesanan ke File
              8. Keluar
            %s
            """.formatted(garisBatas, garisBatas, garisBatas));
    }

    // Input pilihan dengan exception handling
    private static int inputPilihanMenu() {
        System.out.print("Pilih menu [1-8]: ");
        try {
            int pilihan = input.nextInt();
            input.nextLine(); // Konsumsi newline
            return pilihan;
        } catch (Exception e) {
            input.nextLine(); // Clear buffer
            return -1;
        }
    }

    // Proses menu menggunakan enhanced switch (Java 25)
    private static void prosesMenu(int pilihan) {
        switch (pilihan) {
            case 1 -> tambahItemBaru();
            case 2 -> tampilkanMenu();
            case 3 -> terimaPesanan();
            case 4 -> hitungTotalPesanan();
            case 5 -> tampilkanStruk();
            case 6 -> simpanMenuKeFile();
            case 7 -> simpanStrukKeFile();
            case 8 -> System.out.println("\n✓ Terima kasih telah menggunakan aplikasi. Sampai jumpa!");
            default -> System.out.println("✗ Pilihan tidak valid! Silakan pilih 1-8.");
        }
    }

    // Fitur 1: Tambah item baru dengan pattern matching
    private static void tambahItemBaru() {
        var garisBatas = "=".repeat(70);
        System.out.println("""
            
            %s
                                  TAMBAH ITEM MENU BARU
            %s
            """.formatted(garisBatas, garisBatas));

        try {
            System.out.print("Nama item: ");
            var nama = input.nextLine();

            System.out.print("Harga: Rp ");
            var harga = input.nextDouble();
            input.nextLine();

            System.out.println("""
                
                Pilih tipe item:
                1. Makanan
                2. Minuman
                3. Item dengan Diskon
                """);
            System.out.print("Pilihan: ");
            var tipe = input.nextInt();
            input.nextLine();

            // Using enhanced switch expression (Java 25)
            MenuItem itemBaru = switch (tipe) {
                case 1 -> {
                    System.out.print("Jenis makanan (Nusantara/Western/Chinese/dll): ");
                    var jenisMakanan = input.nextLine();
                    yield new Makanan(nama, harga, jenisMakanan);
                }
                case 2 -> {
                    System.out.print("Jenis minuman (Dingin/Panas/Segar/dll): ");
                    var jenisMinuman = input.nextLine();
                    yield new Minuman(nama, harga, jenisMinuman);
                }
                case 3 -> {
                    System.out.print("Persentase diskon (%): ");
                    var diskon = input.nextDouble();
                    input.nextLine();
                    yield new Diskon(nama, harga, diskon);
                }
                default -> throw new IllegalArgumentException("Tipe item tidak valid!");
            };

            menuRestoran.tambahItem(itemBaru);
            System.out.println("\n✓ Item berhasil ditambahkan ke menu!");

        } catch (Exception e) {
            System.out.println("✗ Gagal menambahkan item: " + e.getMessage());
            input.nextLine(); // Clear buffer
        }
    }

    // Fitur 2: Tampilkan menu
    private static void tampilkanMenu() {
        menuRestoran.tampilkanSemuaMenu();
    }

    // Fitur 3: Terima pesanan
    private static void terimaPesanan() {
        var garisBatas = "=".repeat(70);
        System.out.println("""
            
            %s
                                    TERIMA PESANAN
            %s
            """.formatted(garisBatas, garisBatas));

        // Buat pesanan baru jika belum ada
        if (pesananSekarang == null) {
            System.out.print("Masukkan nama pelanggan: ");
            var namaPelanggan = input.nextLine();
            pesananSekarang = new Pesanan(namaPelanggan);
            System.out.println("✓ Pesanan untuk " + namaPelanggan + " dimulai.\n");
        }

        int pilihan;
        do {
            menuRestoran.tampilkanSemuaMenu();
            System.out.println("\nPilih nomor menu untuk dipesan (0 untuk selesai): ");
            
            try {
                pilihan = input.nextInt();
                input.nextLine();

                if (pilihan > 0 && pilihan <= menuRestoran.getJumlahItem()) {
                    var itemDipilih = menuRestoran.getItem(pilihan - 1);
                    pesananSekarang.tambahItem(itemDipilih);
                } else if (pilihan != 0) {
                    System.out.println("✗ Nomor menu tidak valid!");
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("✗ " + e.getMessage());
                pilihan = -1;
            } catch (Exception e) {
                System.out.println("✗ Input tidak valid!");
                input.nextLine();
                pilihan = -1;
            }

        } while (pilihan != 0);

        System.out.println("\n✓ Pesanan selesai dicatat!");
    }

    // Fitur 4: Hitung total
    private static void hitungTotalPesanan() {
        if (pesananSekarang == null || pesananSekarang.isEmpty()) {
            System.out.println("\n✗ Belum ada pesanan yang dicatat!");
            return;
        }

        var total = pesananSekarang.hitungTotal();
        var garisBatas = "=".repeat(70);
        System.out.println("""
            
            %s
            TOTAL BIAYA PESANAN: Rp %.0f
            %s
            """.formatted(garisBatas, total, garisBatas));
    }

    // Fitur 5: Tampilkan struk
    private static void tampilkanStruk() {
        if (pesananSekarang == null || pesananSekarang.isEmpty()) {
            System.out.println("\n✗ Belum ada pesanan yang dicatat!");
            return;
        }

        pesananSekarang.tampilkanStruk();
    }

    // Fitur 6: Simpan menu ke file
    private static void simpanMenuKeFile() {
        try {
            menuRestoran.simpanKeFile();
        } catch (IOException e) {
            System.out.println("✗ Gagal menyimpan menu: " + e.getMessage());
        }
    }

    // Fitur 7: Simpan struk ke file
    private static void simpanStrukKeFile() {
        if (pesananSekarang == null || pesananSekarang.isEmpty()) {
            System.out.println("\n✗ Belum ada pesanan yang dapat disimpan!");
            return;
        }

        try {
            pesananSekarang.simpanStrukKeFile();
            // Reset pesanan setelah disimpan
            pesananSekarang = null;
        } catch (IOException e) {
            System.out.println("✗ Gagal menyimpan struk: " + e.getMessage());
        }
    }

    // Minimal implementations untuk kelas yang digunakan agar file ini dapat dikompilasi.
    // Kelas-kelas ini dibuat sebagai nested static classes untuk menghindari file tambahan.

    private static abstract class MenuItem {
        protected String nama;
        protected double harga;

        protected MenuItem(String nama, double harga) {
            this.nama = nama;
            this.harga = harga;
        }

        public String getNama() {
            return nama;
        }

        public double getHarga() {
            return harga;
        }

        @Override
        public String toString() {
            return nama + " - Rp " + String.format("%.0f", getHarga());
        }
    }

    private static class Makanan extends MenuItem {
        private String jenis;

        public Makanan(String nama, double harga, String jenis) {
            super(nama, harga);
            this.jenis = jenis;
        }

        @Override
        public String toString() {
            return String.format("%s (Makanan - %s) - Rp %.0f", nama, jenis, getHarga());
        }
    }

    private static class Minuman extends MenuItem {
        private String jenis;

        public Minuman(String nama, double harga, String jenis) {
            super(nama, harga);
            this.jenis = jenis;
        }

        @Override
        public String toString() {
            return String.format("%s (Minuman - %s) - Rp %.0f", nama, jenis, getHarga());
        }
    }

    private static class Diskon extends MenuItem {
        private double persen; // dalam persen, misal 10 untuk 10%

        public Diskon(String nama, double harga, double persen) {
            super(nama, harga);
            this.persen = persen;
        }

        @Override
        public double getHarga() {
            double diskon = harga * (persen / 100.0);
            return harga - diskon;
        }

        @Override
        public String toString() {
            return String.format("%s (Diskon %.0f%%) - Rp %.0f", nama, persen, getHarga());
        }
    }

    private static class Menu {
        private final java.util.List<MenuItem> daftar = new java.util.ArrayList<>();

        public void muatDariFile() throws IOException {
            // Implementasi sederhana: jika tidak ada file, isi default
            // Untuk saat ini kita isi default sehingga pemanggil tidak selalu mendapat error
            isiMenuDefault();
        }

        public void isiMenuDefault() {
            daftar.clear();
            daftar.add(new Makanan("Nasi Goreng", 25000, "Nusantara"));
            daftar.add(new Minuman("Es Teh", 8000, "Dingin"));
            daftar.add(new Diskon("Ayam Bakar Promo", 40000, 10));
        }

        public void tambahItem(MenuItem item) {
            daftar.add(item);
        }

        public void tampilkanSemuaMenu() {
            if (daftar.isEmpty()) {
                System.out.println("\n--- Menu kosong ---");
                return;
            }
            System.out.println("\n--- Daftar Menu ---");
            int i = 1;
            for (MenuItem item : daftar) {
                System.out.println(i + ". " + item.toString());
                i++;
            }
        }

        public int getJumlahItem() {
            return daftar.size();
        }

        public MenuItem getItem(int index) {
            return daftar.get(index);
        }

        public void simpanKeFile() throws IOException {
            // Simpel: simpan satu string ke file "menu.txt"
            try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("menu.txt"))) {
                for (MenuItem item : daftar) {
                    writer.write(item.toString());
                    writer.newLine();
                }
            }
        }
    }

    private static class Pesanan {
        private final String namaPelanggan;
        private final java.util.List<MenuItem> items = new java.util.ArrayList<>();

        public Pesanan(String namaPelanggan) {
            this.namaPelanggan = namaPelanggan;
        }

        public void tambahItem(MenuItem item) {
            items.add(item);
            System.out.println("✓ Ditambahkan: " + item.getNama());
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }

        public double hitungTotal() {
            double total = 0.0;
            for (MenuItem item : items) {
                total += item.getHarga();
            }
            return total;
        }

        public void tampilkanStruk() {
            System.out.println("\n--- STRUK PESANAN ---");
            System.out.println("Pelanggan: " + namaPelanggan);
            int i = 1;
            for (MenuItem item : items) {
                System.out.println(i + ". " + item.toString());
                i++;
            }
            System.out.println("TOTAL: Rp " + String.format("%.0f", hitungTotal()));
        }

        public void simpanStrukKeFile() throws IOException {
            String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = "struk_" + timestamp + ".txt";
            try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filename))) {
                writer.write("--- STRUK PESANAN ---");
                writer.newLine();
                writer.write("Pelanggan: " + namaPelanggan);
                writer.newLine();
                int i = 1;
                for (MenuItem item : items) {
                    writer.write(i + ". " + item.toString());
                    writer.newLine();
                    i++;
                }
                writer.write("TOTAL: Rp " + String.format("%.0f", hitungTotal()));
                writer.newLine();
            }
            System.out.println("✓ Struk disimpan ke file: " + filename);
        }
    }
}