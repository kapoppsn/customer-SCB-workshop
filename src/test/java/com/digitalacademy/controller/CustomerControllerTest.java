package com.digitalacademy.controller;


import com.digitalacademy.model.Customer;
import com.digitalacademy.service.CustomerService;
import com.digitalacademy.support.CustomerSupportTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mvc;

    public static final String urlCustomerList = "/customer/list";
    public static  final String urlCustomer = "/customer/";

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
        mvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();
    }

    @DisplayName("test get customer should return customer list")
    @Test
    void testGetCustomerList() throws Exception{
        when(customerService.getCustomerList()).thenReturn(CustomerSupportTest.getCustomerList());

        MvcResult mvcResult = mvc.perform(get(urlCustomerList))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals("1" , jsonArray.getJSONObject(0).get("id").toString());
        assertEquals("kapop" , jsonArray.getJSONObject(0).get("firstName").toString());
        assertEquals("pasinee" , jsonArray.getJSONObject(0).get("lastName").toString());
        assertEquals("kapop@gmail.com" , jsonArray.getJSONObject(0).get("email").toString());
        assertEquals("0811111111" , jsonArray.getJSONObject(0).get("phoneNo").toString());
        assertEquals("22" , jsonArray.getJSONObject(0).get("age").toString());

        assertEquals("2" , jsonArray.getJSONObject(1).get("id").toString());
        assertEquals("kapop2" , jsonArray.getJSONObject(1).get("firstName").toString());
        assertEquals("pasinee2" , jsonArray.getJSONObject(1).get("lastName").toString());
        assertEquals("kapop2@gmail.com" , jsonArray.getJSONObject(1).get("email").toString());
        assertEquals("0822222222" , jsonArray.getJSONObject(1).get("phoneNo").toString());
        assertEquals("23" , jsonArray.getJSONObject(1).get("age").toString());
    }

    @DisplayName("test get customer by id should return customer")
    @Test
    void testGetCustomerById() throws Exception{
        Long reqId =  1L;
        when(customerService.getCustomer(reqId)).thenReturn(CustomerSupportTest.getNewCustomer());

        MvcResult mvcResult = mvc.perform(get(urlCustomer+"/" + reqId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("1" , json.get("id").toString());
        assertEquals("kapop3" , json.get("firstName").toString());
        assertEquals("pasinee3" , json.get("lastName").toString());
        assertEquals("kapop3@gmail.com" , json.get("email").toString());
        assertEquals("0833333333" , json.get("phoneNo").toString());
        assertEquals("24" , json.get("age").toString());
    }

    @DisplayName("test get customer by id should return not found")
    @Test
    void testGetCustomerByIdNotfound() throws Exception{
        Long reqId =  5L;
        MvcResult mvcResult = mvc.perform(get(urlCustomerList+"/" + reqId))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @DisplayName("Test get customer by name should return not found")
    @Test
    void testGetCustomerByIdEmpty() throws Exception {
        Long reqId = 1L;
        when(customerService.getCustomer(reqId)).thenReturn(null);

        MvcResult mvcResult = mvc.perform(get(urlCustomer + "/" + reqId))
                .andExpect(status().isNotFound())
                .andReturn();
    }


        @DisplayName("test get customer by name should return customer")
    @Test
    void testGetCustomerByName() throws Exception{
        String reqName = "kapop";

        when(customerService.getCustomerName(reqName)).thenReturn(CustomerSupportTest.getCustomerNameKapopList());

        MvcResult mvcResult = mvc.perform(get(urlCustomer + "?name=" + reqName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals("1" , jsonArray.getJSONObject(0).get("id").toString());
        assertEquals("kapop" , jsonArray.getJSONObject(0).get("firstName").toString());
        assertEquals("pasinee" , jsonArray.getJSONObject(0).get("lastName").toString());
        assertEquals("kapop@gmail.com" , jsonArray.getJSONObject(0).get("email").toString());
        assertEquals("0811111111" , jsonArray.getJSONObject(0).get("phoneNo").toString());
        assertEquals("22" , jsonArray.getJSONObject(0).get("age").toString());

        assertEquals("2" , jsonArray.getJSONObject(1).get("id").toString());
        assertEquals("kapop2" , jsonArray.getJSONObject(1).get("firstName").toString());
        assertEquals("pasinee2" , jsonArray.getJSONObject(1).get("lastName").toString());
        assertEquals("kapop2@gmail.com" , jsonArray.getJSONObject(1).get("email").toString());
        assertEquals("0822222222" , jsonArray.getJSONObject(1).get("phoneNo").toString());
        assertEquals("23" , jsonArray.getJSONObject(1).get("age").toString());
    }

    @DisplayName("Test get customer by name should return not found")
    @Test
    void testGetCustomerByNameNotFound() throws Exception{
        String reqName = "kapop";

        MvcResult mvcResult = mvc.perform(get(urlCustomer +"?name="+ reqName))
                .andExpect(status().isNotFound())
                .andReturn();
    }


    @DisplayName("Test create customer should return success")
    @Test
    void testCreateCustomer() throws Exception{
        Customer reqCustomer = CustomerSupportTest.createNewCustomer();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.createCustomer(reqCustomer)).thenReturn(
                CustomerSupportTest.responseCreateNewCustomer());


        MvcResult mvcResult = mvc.perform(post(urlCustomer)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();

        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("8",json.get("id").toString());
        assertEquals("kapop4",json.get("firstName"));
        assertEquals("pasinee4",json.get("lastName"));
        assertEquals("kapop4@gmail.com",json.get("email"));
        assertEquals("0855555555",json.get("phoneNo"));
        assertEquals(26,json.get("age"));
    }

    @DisplayName("Test get customer should return not found")
    @Test
    void testCreateCustomerWithNameEmpty() throws Exception{
        Customer reqCustomer = CustomerSupportTest.createNewCustomer();
        reqCustomer.setFirstName("");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.createCustomer(reqCustomer)).thenReturn(
                CustomerSupportTest.responseCreateNewCustomer());

        MvcResult mvcResult = mvc.perform(post(urlCustomer)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("Validation failed for argument [0] in public org.springframework.http.ResponseEntity<?> com.digitalacademy.controller.CustomerController.createCustomer(com.digitalacademy.model.Customer): [Field error in object 'customer' on field 'firstName': rejected value []; codes [Size.customer.firstName,Size.firstName,Size.java.lang.String,Size]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [customer.firstName,firstName]; arguments []; default message [firstName],100,1]; default message [Please type your first name]] "
                ,mvcResult.getResolvedException().getMessage());
    }

    @DisplayName("Test update customer should return success")
    @Test
    void testUpdateCustomer() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getUpdateCustomer();
        Long reqId = 1L;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.updateCustomer(reqId, reqCustomer))
                .thenReturn(CustomerSupportTest.getUpdateCustomer());

        MvcResult mvcResult = mvc.perform(put(urlCustomer +"/"+ reqId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("1",json.get("id").toString());
        assertEquals("kapop32",json.get("firstName"));
        assertEquals("pasinee3",json.get("lastName"));
        assertEquals("kapop3@gmail.com",json.get("email"));
        assertEquals("0833333333",json.get("phoneNo"));
        assertEquals(24,json.get("age"));
    }

    @DisplayName("Test update customer should return id not found")
    @Test
    void testUpdateCustomerIdNotFound() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getUpdateCustomer();
        Long reqId = 1L;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.createCustomer(reqCustomer)).thenReturn(null);


        MvcResult mvcResult = mvc.perform(put(urlCustomer +"/"+ reqId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(customerService, times(1)).updateCustomer(reqId, reqCustomer);
    }

    @DisplayName("Test create customer with path id in not send")
    @Test
    void testUpdateCustomerWithPathIsNotSend() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getNewCustomer();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String reqJson = ow.writeValueAsString(reqCustomer);

        MvcResult mvcResult = mvc.perform(put(urlCustomer + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    @DisplayName("test testDeleteCustomer")
    @Test void testDeleteCustomer() throws Exception {
        Long reqId = 4L;

        when(customerService.deleteByID(reqId)).thenReturn(true);

        MvcResult mvcResult = mvc.perform(delete(urlCustomer + "/" + reqId )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

    }

    @DisplayName("test DeleteCustomerShouldReturnNotFound")
    @Test void testDeleteCustomerShouldReturnNotFound() throws Exception {
        Long reqId = 4L;

        when(customerService.deleteByID(reqId)).thenReturn(false);

        MvcResult mvcResult = mvc.perform(delete(urlCustomer + "/" + reqId )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

    }

}
