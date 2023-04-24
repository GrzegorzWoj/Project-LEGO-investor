package pl.coderslab.legoinvestormanager.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.sound.sampled.Port;
import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findAllByUserId(Long id);

}
