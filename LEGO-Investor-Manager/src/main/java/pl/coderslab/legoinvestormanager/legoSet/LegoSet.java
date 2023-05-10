package pl.coderslab.legoinvestormanager.legoSet;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lego_sets")
public class LegoSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String legoSetNumber;
    private String legoSetName;
    private String series;
    @Column(columnDefinition = "int default 0")
    private int releaseYear;
    @Column(columnDefinition = "int default 0")
    private int amountOfElements;
    @Column(columnDefinition = "double default 0")
    private int amountOfMinifigures;
    @Column(columnDefinition = "double default 0")
    private double originalPrice;
    @Column(columnDefinition = "double default 0")
    private double lowestCurrentPrice;


}
