package pl.coderslab.legoinvestormanager.portfolio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.coderslab.legoinvestormanager.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private User user;

    @PreRemove
    @PreUpdate
    @PrePersist
    private void preventUnauthorizedEdit() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (!this.user.getLogin().equals(name) && authorities.stream().noneMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AuthorizationServiceException("User can only edit his own portfolios.");
        }
    }
}
