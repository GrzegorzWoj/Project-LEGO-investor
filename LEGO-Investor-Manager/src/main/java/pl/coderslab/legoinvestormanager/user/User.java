package pl.coderslab.legoinvestormanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "Users")
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
    private String login;
    @Schema(description = "Password used by the user to log into the app.",
            example = "hardPassword1!", required = true)
    private String password;
    @Schema(description = "Email of the User. Should be correctly formatted e-mail.",
            example = "sample_mail@email.com", required = true)
    @Email
    private String email;
    private String firstName;
    private String lastName;

}
