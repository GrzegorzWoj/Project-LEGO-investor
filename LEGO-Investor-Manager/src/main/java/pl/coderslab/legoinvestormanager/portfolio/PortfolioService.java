package pl.coderslab.legoinvestormanager.portfolio;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    private final PortfolioRepository repository;
    private final PortfolioMapper mapper;

    public PortfolioService(PortfolioRepository repository, PortfolioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PortfolioDTO read(Long id) {
        return mapper.mapPortfolioToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found")));
    }

    public PortfolioDTO create(PortfolioDTO portfolioDTO) {
        Portfolio portfolio = mapper.mapDTOToPortfolio(portfolioDTO);
        return mapper.mapPortfolioToDTO(repository.save(portfolio));
    }

    public PortfolioDTO update(Long id, PortfolioDTO portfolioDTO) {
        Portfolio portfolio = mapper.mapDTOToPortfolio(portfolioDTO);
        Portfolio portf = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found"));
        if (!portf.getId().equals(portfolio.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        repository.save(portfolio);
        return mapper.mapPortfolioToDTO(portfolio);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    public List<PortfolioDTO> readAllByUserId(Long id) {
        return repository.findAllByUserId(id).stream()
                .map(mapper::mapPortfolioToDTO)
                .collect(Collectors.toList());
    }
}
