package pl.coderslab.legoinvestormanager.legoSet;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LegoSetMapper {

    LegoSetDTO mapToLegoSetDTO(LegoSet legoSet);
}
