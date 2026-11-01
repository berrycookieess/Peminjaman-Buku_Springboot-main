package id.ac.ub.filkom.contoh.boundary;

import id.ac.ub.filkom.contoh.control.StokService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HalamanStokController {

    private final StokService stokService;

    public HalamanStokController(StokService stokService) {
        this.stokService = stokService;
    }

    // Menampilkan halaman pengelolaan stok
    @GetMapping("/stok")
    public String halamanStok(Model model) {
        model.addAttribute("daftarBuku", stokService.daftarBuku());

        return "stok";
    }

    // Memproses penambahan stok
    @PostMapping("/stok/tambah")
    public String tambahStok(
            @RequestParam String idBuku,
            @RequestParam int jumlah,
            RedirectAttributes redirectAttributes) {

        try {
            stokService.tambahStok(idBuku, jumlah);
            redirectAttributes.addFlashAttribute(
                "pesanSukses", "Stok buku berhasil ditambahkan"
            );
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(
                "pesanError", e.getMessage()
            );
        }

        return "redirect:/stok";
    }

    // Memproses pengurangan stok
    @PostMapping("/stok/kurangi")
    public String kurangiStok(
            @RequestParam String idBuku,
            @RequestParam int jumlah,
            RedirectAttributes redirectAttributes) {

        try {
            stokService.kurangiStok(idBuku, jumlah);
            redirectAttributes.addFlashAttribute(
                "pesanSukses", "Stok buku berhasil dikurangi"
            );
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(
                "pesanError", e.getMessage()
            );
        }

        return "redirect:/stok";
    }
}