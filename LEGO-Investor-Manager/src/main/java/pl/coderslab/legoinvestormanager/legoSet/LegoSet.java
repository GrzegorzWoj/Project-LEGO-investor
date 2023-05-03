package pl.coderslab.legoinvestormanager.legoSet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lego_sets")
public class LegoSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
