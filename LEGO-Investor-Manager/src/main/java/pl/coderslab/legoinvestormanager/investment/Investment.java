package pl.coderslab.legoinvestormanager.investment;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import pl.coderslab.legoinvestormanager.legoSet.LegoSet;
import pl.coderslab.legoinvestormanager.portfolio.Portfolio;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PositiveOrZero
    private double purchasePrice;
    @PastOrPresent
    private LocalDate purchaseDate;
    @PositiveOrZero
    private double sellingPrice;
    @PastOrPresent
    private LocalDate sellingDate;
    @Range(min = -1, max = 1)
    private int possessionStatus;
    @Size(max = 200)
    private String additionalInfo;
    @ManyToOne
    private Portfolio portfolio;
    @ManyToOne
    private LegoSet legoSet;


//    id	1
//    purchasePrice	400
//    purchaseDate	"2022-12-12"
//    sellingPrice	600
//    sellingDate	"2022-12-14"
//    possessionStatus	1
//    additionalInfo	"info"
//    portfolio	null
//    legoSet
//    id	1
//    setNumber	42110
//    setName	"LandRover"
//    series	"Technic"
//    releaseYear	2020
//    amountOfElements	50
//    amountOfMinifigures	2
//    originalPrice	400
//    lowestCurrentPrice	300

}
