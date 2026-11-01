
package id.ac.ub.filkom.contoh.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/** Entity: satu objek Reservasi = satu baris di tabel "reservasi". */
@Entity
@Table(name = "reservasi")
public class Reservasi {

    @Id
    @Column(name = "id_reservasi", length = 10)
    private String idReservasi;

    @ManyToOne
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @ManyToOne
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @Column(name = "tanggal_reservasi", nullable = false)
    private LocalDate tanggalReservasi;

    @Column(name = "status", length = 30, nullable = false)
    private String status;

    protected Reservasi() { }

    public Reservasi(String idReservasi, Anggota anggota,
                     Buku buku, LocalDate tanggalReservasi,
                     String status) {
        this.idReservasi = idReservasi;
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalReservasi = tanggalReservasi;
        this.status = status;
    }

    public String getIdReservasi() {
        return idReservasi;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public LocalDate getTanggalReservasi() {
        return tanggalReservasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
