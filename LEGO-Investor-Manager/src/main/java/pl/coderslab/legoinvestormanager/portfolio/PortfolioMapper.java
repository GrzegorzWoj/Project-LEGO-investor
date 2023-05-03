package pl.coderslab.legoinvestormanager.portfolio;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {

    @Mapping(source = "user.id", target = "userId")
    PortfolioDTO mapPortfolioToDTO(Portfolio portfolio);

    @Mapping(source = "userId", target = "user.id")
    Portfolio mapDTOToPortfolio(PortfolioDTO portfolioDTO);
}
