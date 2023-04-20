package pl.coderslab.legoinvestormanager.investment;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.legoinvestormanager.legoSet.LegoSet;
import pl.coderslab.legoinvestormanager.portfolio.Portfolio;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Investment")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
