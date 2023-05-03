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
        return mapper.mapInvestmentToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment not found")));
    }

//    public Investment readNoDTO(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Investment not found"));
//    }

    public InvestmentDTO create(InvestmentDTO investmentDTO) {
        Investment investment = mapper.mapDTOToInvestment(investmentDTO);
        //        repository.save(investment);
        return mapper.mapInvestmentToDTO(repository.save(investment));
    }

    public InvestmentDTO update(Long id, InvestmentDTO investmentDTO) {
        Investment investment = mapper.mapDTOToInvestment(investmentDTO);
        Investment investmFromId = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment not found"));
        if (!investmFromId.getId().equals(investment.getId())) {
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
}
