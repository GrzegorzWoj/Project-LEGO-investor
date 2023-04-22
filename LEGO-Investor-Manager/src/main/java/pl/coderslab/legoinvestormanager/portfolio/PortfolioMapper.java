package pl.coderslab.legoinvestormanager.portfolio;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {

    PortfolioDTO mapToPortfolioDTO(Portfolio portfolio);
}
