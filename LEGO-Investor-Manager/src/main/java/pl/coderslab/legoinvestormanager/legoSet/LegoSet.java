package pl.coderslab.legoinvestormanager.legoSet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "LegoSet")
public class LegoSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long setNumber;
    private String setName;
    private String series;
    private int releaseYear;
    private int amountOfElements;
    private int amountOfMinifigures;
    private double originalPrice;
    private double lowestCurrentPrice;

}
