
package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.AnggotaRepository;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.BukuRepository;
import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;
import id.ac.ub.filkom.contoh.entity.TransaksiPinjamRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PinjamService {

    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;
    private final TransaksiPinjamRepository transaksiPinjamRepository;

    public PinjamService(
            AnggotaRepository anggotaRepository,
            BukuRepository bukuRepository,
            TransaksiPinjamRepository transaksiPinjamRepository) {
        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
        this.transaksiPinjamRepository = transaksiPinjamRepository;
    }

    @Transactional
    public Bukti pinjamBuku(String idAnggota, String idBuku) {

        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() -> PinjamGagalException.tidakDitemukan("Anggota"));

        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() -> PinjamGagalException.tidakDitemukan("Buku"));

        if (!anggota.cekBatasPinjam()) {
            throw PinjamGagalException.batasPinjam();
        }

        if (!buku.cekStok()) {
            throw PinjamGagalException.stokHabis();
        }

        TransaksiPinjam transaksi = TransaksiPinjam.buat(anggota, buku);

        transaksiPinjamRepository.save(transaksi);

        buku.kurangiStok();
        bukuRepository.save(buku);

        return Bukti.dari(transaksi);
    }
}