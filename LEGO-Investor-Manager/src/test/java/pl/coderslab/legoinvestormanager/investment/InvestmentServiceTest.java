package pl.coderslab.legoinvestormanager.investment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvestmentServiceTest {

    @InjectMocks
    private InvestmentService service;
    @Mock
    private InvestmentRepository repository;
    @Mock
    private InvestmentMapper mapper;

//    @BeforeEach
//    void init() {
//        service = new InvestmentService(repository, mapper);
//    }

    @Test
    void read() {
        //given
        Investment investment = new Investment();
        investment.setId(4L);
        InvestmentDTO investmentDTO = new InvestmentDTO();
        investmentDTO.setId(4L);
        //when
        when(repository.findById(4L)).thenReturn(Optional.of(investment));
        when(mapper.mapInvestmentToDTO(investment)).thenReturn(investmentDTO);
        InvestmentDTO result = service.read(4L);
        //then
        assertEquals(4L, result.getId());
    }

//    @Test
//    void create() {
//        //given
//        Investment investment = new Investment();
//        investment.setId(1L);
//        InvestmentDTO investmentDTO = new InvestmentDTO();
//        investmentDTO.setId(1L);
//        //when
//        when(repository.save(investment)).thenReturn(investment);
//        when(mapper.mapInvestmentToDTO(investment)).thenReturn(investmentDTO);
//        when(mapper.mapDTOToInvestment(investmentDTO)).thenReturn(investment);
//        InvestmentDTO result = service.create(investmentDTO);
//        //then
//        assertEquals(1L, result.getId());
//    }
//
//    @Test
//    void update() {
//        //given
//        //when
//        //then
//    }
//
//    @Test
//    void delete() {
//        //given
//        //when
//        //then
//    }
//
//    @Test
//    void readAllByPortfolioId() {
//        //given
//        //when
//        //then
//    }
//
//    @Test
//    void readAllByPortfolioIdAndPossessionStatus() {
//        //given
//        //when
//        //then
//    }

    @Test
    void income() {
        //given
        Investment investment = new Investment();
        investment.setId(4L);
        investment.setSellingPrice(250.50);
        investment.setPurchasePrice(100.00);
        InvestmentDTO investmentDTO = new InvestmentDTO();
        investmentDTO.setId(4L);
        investmentDTO.setSellingPrice(250.50);
        investmentDTO.setPurchasePrice(100.00);
        //when
        when(repository.findById(4L)).thenReturn(Optional.of(investment));
        when(mapper.mapInvestmentToDTO(investment)).thenReturn(investmentDTO);
        double result = service.income(4L);
        //then
        assertEquals(150.50, result);
    }

    @Test
    void returnRate() {
        //given
        Investment investment = new Investment();
        investment.setId(4L);
        investment.setSellingPrice(252.17);
        investment.setPurchasePrice(151.00);
        InvestmentDTO investmentDTO = new InvestmentDTO();
        investmentDTO.setId(4L);
        investmentDTO.setSellingPrice(252.17);
        investmentDTO.setPurchasePrice(151.00);
        //when
        when(repository.findById(4L)).thenReturn(Optional.of(investment));
        when(mapper.mapInvestmentToDTO(investment)).thenReturn(investmentDTO);
        double result = service.returnRate(4L);
        //then
        assertEquals(67.00, result);
    }

    @Test
    void annualReturnRate() {
        //given
        Investment investment = new Investment();
        investment.setId(4L);
        investment.setSellingPrice(252.17);
        investment.setPurchasePrice(151.00);
        investment.setSellingDate(LocalDate.parse("2022-12-01"));
        investment.setPurchaseDate(LocalDate.parse("2020-06-01"));
        InvestmentDTO investmentDTO = new InvestmentDTO();
        investmentDTO.setId(4L);
        investmentDTO.setSellingPrice(252.17);
        investmentDTO.setPurchasePrice(151.00);
        investmentDTO.setSellingDate(LocalDate.parse("2022-12-01"));
        investmentDTO.setPurchaseDate(LocalDate.parse("2020-06-01"));
        //when
        when(repository.findById(4L)).thenReturn(Optional.of(investment));
        when(mapper.mapInvestmentToDTO(investment)).thenReturn(investmentDTO);
        double result = service.annualReturnRate(4L);
        //then
        assertEquals(26.80, result, 0.01);
    }
}