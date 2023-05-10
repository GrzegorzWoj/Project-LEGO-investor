package pl.coderslab.legoinvestormanager.investment;

import org.springframework.stereotype.Service;
import pl.coderslab.legoinvestormanager.portfolio.PortfolioRepository;

import javax.persistence.EntityNotFoundException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentService {

    private final InvestmentRepository repository;
    private final PortfolioRepository portfolioRepository;
    private final InvestmentMapper mapper;

    public InvestmentService(InvestmentRepository repository, PortfolioRepository portfolioRepository, InvestmentMapper mapper) {
        this.repository = repository;
        this.portfolioRepository = portfolioRepository;
        this.mapper = mapper;
    }

    public InvestmentDTO read(Long id) {
        return mapper.mapInvestmentToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment not found")));
    }

    public InvestmentDTO create(InvestmentDTO investmentDTO) {
        Investment investment = mapper.mapDTOToInvestment(investmentDTO);
        investment.setPortfolio(portfolioRepository.findById(investmentDTO.getPortfolioId())
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found")));
        return mapper.mapInvestmentToDTO(repository.save(investment));
    }

    public InvestmentDTO update(Long id, InvestmentDTO investmentDTO) {
        Investment investment = mapper.mapDTOToInvestment(investmentDTO);
        Investment investm = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment not found"));
        if (!investm.getId().equals(investment.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        repository.save(investment);
        return mapper.mapInvestmentToDTO(investment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<InvestmentDTO> readAllByPortfolioId(Long id) {
        return repository.findAllByPortfolioId(id).stream()
                .map(mapper::mapInvestmentToDTO)
                .collect(Collectors.toList());
    }

    public List<InvestmentDTO> readAllByPortfolioIdAndPossessionStatus(Long id, int status) {
        return repository.findAllByPortfolioIdAndPossessionStatus(id, status).stream()
                .map(mapper::mapInvestmentToDTO)
                .collect(Collectors.toList());
    }

    public double income(Long id) {
        return read(id).getSellingPrice() - read(id).getPurchasePrice();
    }

    public double returnRate(Long id) {
        return (income(id) / read(id).getPurchasePrice()) * 100;
    }

    public double annualReturnRate(Long id) {
        double years = read(id).getPurchaseDate().until(read(id).getSellingDate(), ChronoUnit.DAYS) / 365.25;
        return (returnRate(id) / years);
    }
}
