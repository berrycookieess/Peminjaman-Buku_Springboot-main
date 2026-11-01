
package id.ac.ub.filkom.contoh.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.ac.ub.filkom.contoh.control.Bukti;
import id.ac.ub.filkom.contoh.control.PinjamGagalException;
import id.ac.ub.filkom.contoh.control.PinjamService;
import id.ac.ub.filkom.contoh.entity.BukuRepository;

@Controller
@RequestMapping("/pinjam")
public class HalamanPinjamController {

    private final PinjamService pinjamService;
    private final BukuRepository bukuRepository;

    public HalamanPinjamController(
            PinjamService pinjamService,
            BukuRepository bukuRepository) {
        this.pinjamService = pinjamService;
        this.bukuRepository = bukuRepository;
    }

    @GetMapping
    public String tampilHalaman(Model model) {
        model.addAttribute("daftarBuku", bukuRepository.findAll());
        return "pinjam";
    }

    @PostMapping
    public String pinjam(
            @RequestParam String idAnggota,
            @RequestParam String idBuku,
            Model model) {

        try {
            Bukti bukti = pinjamService.pinjamBuku(idAnggota, idBuku);
            return tampilBukti(bukti, model);

        } catch (PinjamGagalException e) {
            model.addAttribute("pesan", e.getMessage());
            model.addAttribute("daftarBuku", bukuRepository.findAll());
            return "pinjam";
        }
    }

    private String tampilBukti(Bukti bukti, Model model) {
        model.addAttribute("bukti", bukti);
        return "bukti";
    }
}
