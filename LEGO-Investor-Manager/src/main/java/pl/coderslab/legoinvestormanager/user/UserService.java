package pl.coderslab.legoinvestormanager.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;


    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDTO read(Long id) {
        return mapper.mapUserToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    public UserDTO create(UserDTO userDTO) {
        User user = mapper.mapDTOToUser(userDTO);
        user.hashPassword();
        return mapper.mapUserToDTO(repository.save(user));
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        User user = mapper.mapDTOToUser(userDTO);
        User usr = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!usr.getId().equals(user.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        user.hashPassword();
        repository.save(user);
        return mapper.mapUserToDTO(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<UserDTO> readAll() {
        return repository.findAll().stream()
                .map(mapper::mapUserToDTO)
                .collect(Collectors.toList());
    }


}
