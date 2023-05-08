package pl.coderslab.legoinvestormanager.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return service.create(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return service.update(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return service.readAll();
    }


// just to test for now
    @GetMapping("/pass/{id}/{candidatePass}")
    public String checkPassword(@PathVariable Long id, @PathVariable String candidatePass) {
        if (!BCrypt.checkpw(candidatePass, service.read(id).getPassword())) {
            return "NO OK";
        }
        return "OK";
    }


}
