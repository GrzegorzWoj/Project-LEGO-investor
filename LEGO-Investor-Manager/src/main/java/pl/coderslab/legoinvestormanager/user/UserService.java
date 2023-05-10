package pl.coderslab.legoinvestormanager.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.legoinvestormanager.role.Role;
import pl.coderslab.legoinvestormanager.role.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, RoleRepository roleRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO read(Long id) {
        return mapper.mapUserToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    public UserDTO create(UserDTO userDTO) {
        User user = mapper.mapDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        repository.save(user);
        return mapper.mapUserToDTO(user);
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        User user = mapper.mapDTOToUser(userDTO);
        User usr = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!usr.getId().equals(user.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(usr.getRoles());
        user.setLogin(usr.getLogin());
        repository.save(user);
        return mapper.mapUserToDTO(user);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        repository.deleteById(id);
    }

    public List<UserDTO> readAll() {
        return repository.findAll().stream()
                .map(mapper::mapUserToDTO)
                .collect(Collectors.toList());
    }


}
