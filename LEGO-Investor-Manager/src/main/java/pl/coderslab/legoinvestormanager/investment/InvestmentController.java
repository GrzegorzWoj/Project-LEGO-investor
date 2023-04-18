package pl.coderslab.legoinvestormanager.investment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lego")
public class InvestmentController {

    private final InvestmentService service;


    public InvestmentController(InvestmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public InvestmentDTO getInvestment(@PathVariable Long id) {
        return service.read(id);
    }
}
