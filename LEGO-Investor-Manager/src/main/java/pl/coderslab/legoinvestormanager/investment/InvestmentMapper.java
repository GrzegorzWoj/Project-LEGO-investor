package pl.coderslab.legoinvestormanager.investment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
//    InvestmentMapper INSTANCE = Mappers.getMapper(InvestmentMapper.class);

    @Mapping(source = "legoSet.setNumber", target = "setNumber")
    @Mapping(source = "legoSet.originalPrice", target = "originalPrice")
    @Mapping(source = "legoSet.lowestCurrentPrice", target = "lowestCurrentPrice")
    InvestmentDTO mapToInvestmentDTO(Investment investment);
}
