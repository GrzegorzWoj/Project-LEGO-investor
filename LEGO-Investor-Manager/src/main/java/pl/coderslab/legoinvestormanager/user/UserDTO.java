package pl.coderslab.legoinvestormanager.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
}
