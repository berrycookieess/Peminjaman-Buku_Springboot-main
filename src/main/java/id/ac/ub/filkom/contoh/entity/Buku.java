package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buku")
public class Buku {

    @Id
    @Column(name = "id_buku", length = 10)
    private String idBuku;

    @Column(name = "judul", length = 200, nullable = false)
    private String judul;

    @Column(name = "stok")
    private int stok;

    protected Buku() {
    }

    public Buku(String idBuku, String judul, int stok) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.stok = stok;
    }

    public boolean cekStok() {
        return stok > 0;
    }

    // Menambahkan stok buku
    public void tambahStok(int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException(
                "Jumlah penambahan harus lebih dari 0"
            );
        }

        stok += jumlah;
    }

    // Mengurangi stok berdasarkan jumlah yang dimasukkan
    public void kurangiStok(int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException(
                "Jumlah pengurangan harus lebih dari 0"
            );
        }

        if (jumlah > stok) {
            throw new IllegalStateException(
                "Pengurangan melebihi stok yang tersedia"
            );
        }

        stok -= jumlah;
    }

    // Digunakan oleh proses peminjaman yang sudah ada
    public void kurangiStok() {
        kurangiStok(1);
    }

    public String getIdBuku() {
        return idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public int getStok() {
        return stok;
    }
}