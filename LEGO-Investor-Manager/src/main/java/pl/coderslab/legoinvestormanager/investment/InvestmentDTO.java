package pl.coderslab.legoinvestormanager.investment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InvestmentDTO {

    private Long id;
    private double purchasePrice;
    private LocalDate purchaseDate;
    private double sellingPrice;
    private LocalDate sellingDate;
    private int possessionStatus;
    private String additionalInfo;
    private long legoSetNumber;
    private double originalPrice;
    private double lowestCurrentPrice;

}

