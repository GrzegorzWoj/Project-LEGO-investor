package pl.coderslab.legoinvestormanager.legoSet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegoSetDTO {

    private Long id;
    private Long legoSetNumber;
    private String legoSetName;
    private String series;
    private int releaseYear;
    private int amountOfElements;
    private int amountOfMinifigures;
    private double originalPrice;
    private double lowestCurrentPrice;
}
