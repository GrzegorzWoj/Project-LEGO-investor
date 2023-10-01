package pl.coderslab.legoinvestormanager.investment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
//    InvestmentMapper INSTANCE = Mappers.getMapper(InvestmentMapper.class);

    @Mapping(source = "portfolio.id", target = "portfolioId")
    @Mapping(source = "legoSet.id", target = "legoSetId")
    @Mapping(source = "legoSet.legoSetNumber", target = "legoSetNumber")
    @Mapping(source = "legoSet.legoSetName", target = "legoSetName")
    @Mapping(source = "legoSet.originalPrice", target = "originalPrice")
    @Mapping(source = "legoSet.lowestCurrentPrice", target = "lowestCurrentPrice")
    InvestmentDTO mapInvestmentToDTO(Investment investment);


    @Mapping(source = "portfolioId", target = "portfolio.id")
    @Mapping(source = "legoSetId", target = "legoSet.id")
    @Mapping(source = "legoSetNumber", target = "legoSet.legoSetNumber")
    @Mapping(source = "legoSetName", target = "legoSet.legoSetName")
    @Mapping(source = "originalPrice", target = "legoSet.originalPrice")
    @Mapping(source = "lowestCurrentPrice", target = "legoSet.lowestCurrentPrice")
    Investment mapDTOToInvestment(InvestmentDTO investmentDTO);
}
