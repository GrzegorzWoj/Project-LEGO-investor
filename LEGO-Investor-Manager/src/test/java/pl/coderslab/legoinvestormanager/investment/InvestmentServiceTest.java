package pl.coderslab.legoinvestormanager.investment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(repository.findById(4L)).thenReturn(Optional.of(investment));
        //when
        //nie działa mapper - zwraca nulla
        InvestmentDTO result = service.read(4L);
        //then
        assertEquals(4L, result.getId());
    }

    @Test
    void create() {
        //given
        //when
        //then
    }

    @Test
    void update() {
        //given
        //when
        //then
    }

    @Test
    void delete() {
        //given
        //when
        //then
    }

    @Test
    void readAllByPortfolioId() {
        //given
        //when
        //then
    }

    @Test
    void readAllByPortfolioIdAndPossessionStatus() {
        //given
        //when
        //then
    }

    @Test
    void income() {
        //given
        //when
        //then
    }

    @Test
    void returnRate() {
        //given
        //when
        //then
    }

    @Test
    void annualReturnRate() {
        //given
        //when
        //then
    }
}