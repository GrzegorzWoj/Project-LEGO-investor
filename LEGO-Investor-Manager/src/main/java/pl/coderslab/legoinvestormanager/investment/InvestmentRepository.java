package pl.coderslab.legoinvestormanager.investment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    List<Investment> findAllByPortfolioId(Long id);

}
