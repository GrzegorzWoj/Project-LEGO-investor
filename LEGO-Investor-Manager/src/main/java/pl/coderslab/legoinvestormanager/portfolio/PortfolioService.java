package pl.coderslab.legoinvestormanager.portfolio;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PortfolioService {

    private final PortfolioRepository repository;
    private final PortfolioMapper mapper;

    public PortfolioService(PortfolioRepository repository, PortfolioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PortfolioDTO read(Long id) {
        return mapper.mapToPortfolioDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found")));
    }

    public PortfolioDTO create(Portfolio portfolio) {
        return mapper.mapToPortfolioDTO(repository.save(portfolio));
    }

    public PortfolioDTO update(Long id, Portfolio portfolio) {
        Portfolio portf = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found"));
        if (!portf.getId().equals(portfolio.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        repository.save(portfolio);
        return mapper.mapToPortfolioDTO(portfolio);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
