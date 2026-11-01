
package id.ac.ub.filkom.contoh.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String tampilDashboard() {
        return "dashboard";
    }
}