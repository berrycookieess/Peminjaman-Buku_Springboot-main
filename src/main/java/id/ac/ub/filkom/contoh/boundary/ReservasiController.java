
package id.ac.ub.filkom.contoh.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.ub.filkom.contoh.control.ReservasiService;

@Controller
public class ReservasiController {

    private final ReservasiService reservasiService;

    public ReservasiController(ReservasiService reservasiService) {
        this.reservasiService = reservasiService;
    }

    @PostMapping("/reservasi")
    public String ajukanReservasi(
            @RequestParam String idAnggota,
            @RequestParam String idBuku,
            RedirectAttributes redirectAttributes) {

        try {
            reservasiService.ajukanReservasi(idAnggota, idBuku);

            redirectAttributes.addFlashAttribute(
                    "pesan", "Reservasi berhasil diajukan!"
            );

        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("pesan", e.getMessage());
        }

        return "redirect:/pinjam";
    }
}
