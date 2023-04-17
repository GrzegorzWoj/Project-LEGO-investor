package pl.coderslab.legoinvestormanager.investment;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.legoinvestormanager.portfolio.Portfolio;
import pl.coderslab.legoinvestormanager.legoSet.LegoSet;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Investment")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double purchasePrice;
    private LocalDate purchaseDate;
    private double sellingPrice;
    private LocalDate sellingDate;
    private int possessionStatus;
    private String additionalInfo;
    @ManyToOne
    private Portfolio portfolio;
    @ManyToOne
    private LegoSet legoSet;

}
