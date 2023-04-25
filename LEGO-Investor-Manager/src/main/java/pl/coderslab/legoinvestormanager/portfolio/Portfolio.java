package pl.coderslab.legoinvestormanager.portfolio;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.legoinvestormanager.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private User user;
}
