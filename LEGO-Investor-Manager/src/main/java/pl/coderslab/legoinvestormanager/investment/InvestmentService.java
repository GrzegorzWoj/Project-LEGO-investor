package pl.coderslab.legoinvestormanager.investment;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentService {

    private final InvestmentRepository repository;
    private final InvestmentMapper mapper;

    public InvestmentService(InvestmentRepository repository, InvestmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public InvestmentDTO read(Long id) {
        return mapper.mapToInvestmentDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment not found")));
    }

//    public Investment readNoDTO(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Investment not found"));
//    }

    public InvestmentDTO create(Investment investment) {
//        repository.save(investment);
        return mapper.mapToInvestmentDTO(repository.save(investment));
    }

    public InvestmentDTO update(Long id, Investment investment) {
        Investment investm = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment not found"));
        if (!investm.getId().equals(investment.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        repository.save(investment);
        return mapper.mapToInvestmentDTO(investment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<InvestmentDTO> readAllByPortfolioId(Long id) {
        return repository.findAllByPortfolioId(id).stream()
                .map(mapper::mapToInvestmentDTO)
                .collect(Collectors.toList());
    }
}
