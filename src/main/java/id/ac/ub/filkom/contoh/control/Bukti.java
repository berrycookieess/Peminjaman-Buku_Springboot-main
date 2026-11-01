
package id.ac.ub.filkom.contoh.control;

import java.time.LocalDate;

import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;

public class Bukti {

    private String idTransaksi;
    private String namaAnggota;
    private String judulBuku;
    private LocalDate tanggalPinjam;

    private Bukti() {
    }

    public static Bukti dari(TransaksiPinjam transaksi) {
        Bukti bukti = new Bukti();

        bukti.idTransaksi = transaksi.getIdTransaksi();
        bukti.namaAnggota = transaksi.getAnggota().getNama();
        bukti.judulBuku = transaksi.getBuku().getJudul();
        bukti.tanggalPinjam = transaksi.getTanggalPinjam();

        return bukti;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }
}