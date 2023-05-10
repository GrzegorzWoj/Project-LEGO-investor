package pl.coderslab.legoinvestormanager.investment;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.coderslab.legoinvestormanager.legoSet.LegoSet;
import pl.coderslab.legoinvestormanager.portfolio.Portfolio;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PositiveOrZero
    @Column(columnDefinition = "double default 0")
    private double purchasePrice;
    @PastOrPresent
    private LocalDate purchaseDate;
    @PositiveOrZero
    @Column(columnDefinition = "double default 0")
    private double sellingPrice;
    @PastOrPresent
    private LocalDate sellingDate;
    @Range(min = -1, max = 1)
    @Column(columnDefinition = "int default 1")
    private int possessionStatus;
    @Size(max = 200)
    private String additionalInfo;
    @ManyToOne
    private Portfolio portfolio;
    @ManyToOne
    private LegoSet legoSet;

    @PreRemove
    @PreUpdate
    @PrePersist
    private void preventUnauthorizedEdit() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (!this.portfolio.getUser().getLogin().equals(name) && authorities.stream().noneMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AuthorizationServiceException("User can only edit his own investments.");
        }
    }


}
