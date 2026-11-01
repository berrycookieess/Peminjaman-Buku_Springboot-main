package id.ac.ub.filkom.contoh.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BukuTest {

    @Test
    void testConstructorDanGetter() {
        Buku buku = new Buku("B001", "Belajar Java", 10);

        assertEquals("B001", buku.getIdBuku());
        assertEquals("Belajar Java", buku.getJudul());
        assertEquals(10, buku.getStok());
    }

    @Test
    void testCekStokTersedia() {
        Buku buku = new Buku("B001", "Belajar Java", 5);

        assertTrue(buku.cekStok());
    }

    @Test
    void testCekStokHabis() {
        Buku buku = new Buku("B001", "Belajar Java", 0);

        assertFalse(buku.cekStok());
    }

    @Test
    void testTambahStok() {
        Buku buku = new Buku("B001", "Belajar Java", 5);

        buku.tambahStok(3);

        assertEquals(8, buku.getStok());
    }

    @Test
    void testTambahStokTidakValid() {
        Buku buku = new Buku("B001", "Belajar Java", 5);

        assertThrows(
            IllegalArgumentException.class,
            () -> buku.tambahStok(0)
        );
    }

    @Test
    void testKurangiStok() {
        Buku buku = new Buku("B001", "Belajar Java", 5);

        buku.kurangiStok(2);

        assertEquals(3, buku.getStok());
    }

    @Test
    void testKurangiStokMelebihiPersediaan() {
        Buku buku = new Buku("B001", "Belajar Java", 2);

        assertThrows(
            IllegalStateException.class,
            () -> buku.kurangiStok(3)
        );
    }

    @Test
    void testKurangiStokTidakValid() {
        Buku buku = new Buku("B001", "Belajar Java", 5);

        assertThrows(
            IllegalArgumentException.class,
            () -> buku.kurangiStok(0)
        );
    }

    @Test
    void testKurangiSatuStok() {
        Buku buku = new Buku("B001", "Belajar Java", 5);

        buku.kurangiStok();

        assertEquals(4, buku.getStok());
    }

    @Test
    void testKurangiSatuStokSaatHabis() {
        Buku buku = new Buku("B001", "Belajar Java", 0);

        assertThrows(
            IllegalStateException.class,
            () -> buku.kurangiStok()
        );
    }
}