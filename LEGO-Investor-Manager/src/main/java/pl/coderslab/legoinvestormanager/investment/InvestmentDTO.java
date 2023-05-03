package pl.coderslab.legoinvestormanager.investment;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class InvestmentDTO {

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
    private Long portfolioId;
    private Long legoSetId;
    private long legoSetNumber;
    private double originalPrice;
    private double lowestCurrentPrice;

}

