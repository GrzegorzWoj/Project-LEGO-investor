package pl.coderslab.legoinvestormanager.investment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
@ComponentScan
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
}
