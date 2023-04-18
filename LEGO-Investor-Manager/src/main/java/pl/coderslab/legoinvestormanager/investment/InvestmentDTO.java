package pl.coderslab.legoinvestormanager.investment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InvestmentDTO {

    private long id;
    private double purchasePrice;
    private LocalDate purchaseDate;
    private double sellingPrice;
    private LocalDate sellingDate;
    private int possessionStatus;
    private String additionalInfo;
    private long setNumber;
    private double originalPrice;
    private double lowestCurrentPrice;

}

