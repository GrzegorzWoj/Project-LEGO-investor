package pl.coderslab.legoinvestormanager.investment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    private final InvestmentService service;

    public InvestmentController(InvestmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public InvestmentDTO getInvestment(@PathVariable Long id) {
        return service.read(id);
    }

//    @GetMapping("/nodto/{id}")
//    public Investment getInvestmentNoDTO(@PathVariable Long id) {
//        return service.readNoDTO(id);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvestmentDTO createInvestment(@Valid @RequestBody InvestmentDTO investmentDTO) {
        return service.create(investmentDTO);
    }

    @PutMapping("/{id}")
    public InvestmentDTO updateInvestment(@PathVariable Long id, @Valid @RequestBody InvestmentDTO investmentDTO) {
        return service.update(id, investmentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvestment(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/all/{id}")
    public List<InvestmentDTO> getAllInvestmentsInPortfolio(@PathVariable Long id) {
        return service.readAllByPortfolioId(id);
    }

    @GetMapping("/{id}/profit")
    public String getInvestmentProfit(@PathVariable Long id) {
        double income = Math.round(service.income(id) * 100.0) / 100.0;
        double returnRate = Math.round(service.returnRate(id) * 100.0) / 100.0;
        double annualReturnRate = Math.round(service.annualReturnRate(id) * 100.0) / 100.0;

        if (service.read(id).getPossessionStatus() == -1) {
            return "Zysk ze sprzedaży: " + income + " zł \n" +
                    "Stopa zwrotu: " + returnRate + " % \n" +
                    "Roczna stopa zwrotu: " + annualReturnRate + " %";
        } else if (service.read(id).getPossessionStatus() == 1) {
            return "Szacowany zysk ze sprzedaży: " + income + " zł \n" +
                    "Szacowana stopa zwrotu: " + returnRate + " % \n" +
                    "Szacowana roczna stopa zwrotu: " + annualReturnRate + " %";
        }
        return "Zmień status inwestycji aby wyliczyć zysk.";
    }

}
