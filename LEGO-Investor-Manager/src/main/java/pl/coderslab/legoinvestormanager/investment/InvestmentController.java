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
    public InvestmentDTO createInvestment(@Valid @RequestBody Investment investment) {
        return service.create(investment);
    }

    @PutMapping("/{id}")
    public InvestmentDTO updateInvestment(@PathVariable Long id, @Valid @RequestBody Investment investment) {
        return service.update(id, investment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvestment(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/all/{id}")
    public List<InvestmentDTO> getAllLegoSets(@PathVariable Long id) {
        return service.readAllByPortfolioId(id);
    }

}
