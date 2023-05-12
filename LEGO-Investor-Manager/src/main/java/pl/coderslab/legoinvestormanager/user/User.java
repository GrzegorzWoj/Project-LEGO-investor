package pl.coderslab.legoinvestormanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.coderslab.legoinvestormanager.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Schema(description = "Unique identifier of the User.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Schema(description = "Unique nickname of the User. Also used to log into the app.",
            example = "User123", required = true)
    @Size(min = 3, max = 20)
    @Column(nullable = false, unique = true)
    private String login;
    @Schema(description = "Password used by the user to log into the app.",
            example = "hardPassword1!", required = true)
    private String password;
    private String salt;
    @Schema(description = "Email of the User. Should be correctly formatted e-mail.",
            example = "sample_mail@email.com", required = true)
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String firstName;
    private String lastName;
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @PreRemove
    @PreUpdate
    private void preventUnauthorizedEdit() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (!this.login.equals(name) && authorities.stream().noneMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AuthorizationServiceException("User can only edit his own account.");
        }
    }

}
