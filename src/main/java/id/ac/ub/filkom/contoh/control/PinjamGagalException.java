
package id.ac.ub.filkom.contoh.control;

public class PinjamGagalException extends RuntimeException {

    private PinjamGagalException(String pesan) {
        super(pesan);
    }

    public static PinjamGagalException tidakDitemukan(String jenis) {
        return new PinjamGagalException(
                jenis + " tidak ditemukan."
        );
    }

    public static PinjamGagalException batasPinjam() {
        return new PinjamGagalException(
                "Batas maksimal peminjaman buku telah tercapai."
        );
    }

    public static PinjamGagalException stokHabis() {
        return new PinjamGagalException(
                "Stok buku sedang habis."
        );
    }
}
