package com.digitalacademy.controller;


import com.digitalacademy.api.LoanApi;
import com.digitalacademy.model.response.GetLoanInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanControllerTest {

    @Mock
    LoanApi loanApi;

    @InjectMocks
    LoanController loanController;

    private MockMvc mvc;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loanController = new LoanController(loanApi);
        mvc = MockMvcBuilders.standaloneSetup(loanController)
                .build();
    }

    @DisplayName("test loan info should return loan information")
    @Test
    void testGetLoanInfo() throws Exception{
        Long reqId =  1L;
        when(loanApi.getLoanInfo(reqId)).thenReturn(new GetLoanInfoResponse());
        MvcResult mvcResult = mvc.perform(get("/loan/" + reqId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        verify(loanApi, times(1)).getLoanInfo(reqId);
    }
}
