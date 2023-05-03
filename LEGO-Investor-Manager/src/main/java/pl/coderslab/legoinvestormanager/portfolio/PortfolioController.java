package pl.coderslab.legoinvestormanager.portfolio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService service;

    public PortfolioController(PortfolioService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public PortfolioDTO getPortfolio(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioDTO createPortfolio(@RequestBody PortfolioDTO portfolioDTO) {
        return service.create(portfolioDTO);
    }

    @PutMapping("/{id}")
    public PortfolioDTO updatePortfolio(@PathVariable Long id, @RequestBody PortfolioDTO portfolioDTO) {
        return service.update(id, portfolioDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePortfolio(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/all/{id}")
    public List<PortfolioDTO> getAllLegoSets(@PathVariable Long id) {
        return service.readAllByUserId(id);
    }

}
