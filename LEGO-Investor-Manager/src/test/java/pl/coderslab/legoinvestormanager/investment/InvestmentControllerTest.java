package pl.coderslab.legoinvestormanager.investment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@WebMvcTest(InvestmentController.class)
class InvestmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvestmentService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getInvestment() throws Exception {
        //given
        InvestmentDTO investment = new InvestmentDTO();
        investment.setId(5L);
        //when
        when(service.read(5L)).thenReturn(investment);
        //then
        mockMvc.perform(get("/investment/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)));
    }

    @Test
    void createInvestment() {
        //given
        //when
        //then
    }

    @Test
    void updateInvestment() {
        //given
        //when
        //then
    }

    @Test
    void deleteInvestment() throws Exception {
        //given
        InvestmentDTO investment = new InvestmentDTO();
        investment.setId(5L);
        //when
        //then
        mockMvc.perform(delete("/investment/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllInvestmentsInPortfolio() throws Exception {
        //given
        InvestmentDTO investment1 = new InvestmentDTO();
        InvestmentDTO investment2 = new InvestmentDTO();
        investment1.setId(5L);
        investment1.setPortfolioId(2L);
        investment2.setId(8L);
        investment2.setPortfolioId(2L);
        List<InvestmentDTO> investmentList = List.of(investment1, investment2);
        //when
        when(service.readAllByPortfolioId(2L)).thenReturn(investmentList);
        //then
        mockMvc.perform(get("/investment/all/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(5)))
                .andExpect(jsonPath("$[0].portfolioId", is(2)))
                .andExpect(jsonPath("$[1].id", is(8)))
                .andExpect(jsonPath("$[1].portfolioId", is(2)));
    }

    @Test
    void getInvestmentProfit() {
    }
}