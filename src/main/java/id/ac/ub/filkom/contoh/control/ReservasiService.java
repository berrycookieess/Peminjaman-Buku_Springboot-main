
package id.ac.ub.filkom.contoh.control;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.AnggotaRepository;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.BukuRepository;
import id.ac.ub.filkom.contoh.entity.Reservasi;
import id.ac.ub.filkom.contoh.entity.ReservasiRepository;

@Service
public class ReservasiService {

    private final ReservasiRepository reservasiRepository;
    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;

    public ReservasiService(
            ReservasiRepository reservasiRepository,
            AnggotaRepository anggotaRepository,
            BukuRepository bukuRepository) {
        this.reservasiRepository = reservasiRepository;
        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
    }

    @Transactional
    public Reservasi ajukanReservasi(String idAnggota, String idBuku) {

        // Cari anggota berdasarkan ID.
        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() ->
                        new IllegalArgumentException("Anggota tidak ditemukan"));

        // Cari buku berdasarkan ID.
        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() ->
                        new IllegalArgumentException("Buku tidak ditemukan"));

        // Reservasi hanya dapat diajukan jika stok buku habis.
        if (buku.cekStok()) {
            throw new IllegalStateException(
                    "Buku masih tersedia. Silakan lakukan peminjaman.");
        }

        // Buat data reservasi baru.
        String idReservasi = UUID.randomUUID().toString();

        Reservasi reservasi = new Reservasi(
                idReservasi,
                anggota,
                buku,
                LocalDate.now(),
                "MENUNGGU"
        );

        return reservasiRepository.save(reservasi);
    }
}
