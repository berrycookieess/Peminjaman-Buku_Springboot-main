package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.BukuRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StokService {

    private final BukuRepository bukuRepository;

    public StokService(BukuRepository bukuRepository) {
        this.bukuRepository = bukuRepository;
    }

    // Mengambil seluruh data buku
    public List<Buku> daftarBuku() {
        return bukuRepository.findAll();
    }

    // Menambahkan stok buku
    @Transactional
    public void tambahStok(String idBuku, int jumlah) {

        // 1. Mencari buku berdasarkan ID
        Buku buku = bukuRepository.findById(idBuku)
            .orElseThrow(() ->
                new IllegalArgumentException("Buku tidak ditemukan")
            );

        // 2. Menambahkan stok pada entity Buku
        buku.tambahStok(jumlah);

        // 3. Menyimpan perubahan stok
        bukuRepository.save(buku);
    }

    // Mengurangi stok buku
    @Transactional
    public void kurangiStok(String idBuku, int jumlah) {

        // 1. Mencari buku berdasarkan ID
        Buku buku = bukuRepository.findById(idBuku)
            .orElseThrow(() ->
                new IllegalArgumentException("Buku tidak ditemukan")
            );

        // 2. Mengurangi stok pada entity Buku
        buku.kurangiStok(jumlah);

        // 3. Menyimpan perubahan stok
        bukuRepository.save(buku);
    }
}