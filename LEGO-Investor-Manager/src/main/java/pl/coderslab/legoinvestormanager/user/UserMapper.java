package pl.coderslab.legoinvestormanager.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO mapToUserDTO(User user);
}
