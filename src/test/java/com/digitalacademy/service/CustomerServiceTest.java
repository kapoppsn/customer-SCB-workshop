package com.digitalacademy.service;

import com.digitalacademy.model.Customer;
import com.digitalacademy.repositories.CustomerRepository;
import com.digitalacademy.support.CustomerSupportTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomerServiceTest.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }


    @DisplayName("Test get all customer should return list")
    @Test
    void testGetAllCustomer(){
        when(customerRepository.findAll()).thenReturn(CustomerSupportTest.getCustomerList());
        List<Customer> resp = customerService.getCustomerList();

        assertEquals(1,resp.get(0).getId().intValue());
        assertEquals("kapop",resp.get(0).getFirstName());
        assertEquals("pasinee",resp.get(0).getLastName());
        assertEquals("kapop@gmail.com",resp.get(0).getEmail());
        assertEquals("0811111111",resp.get(0).getPhoneNo());
        assertEquals(22,resp.get(0).getAge().intValue());

        assertEquals(2,resp.get(1).getId().intValue());
        assertEquals("kapop2",resp.get(1).getFirstName());
        assertEquals("pasinee2",resp.get(1).getLastName());
        assertEquals("kapop2@gmail.com",resp.get(1).getEmail());
        assertEquals("0822222222",resp.get(1).getPhoneNo());
        assertEquals(23,resp.get(1).getAge().intValue());

    }
    @DisplayName("Test get all customer by id should return list")
    @Test
    void testGetAllCustomerById(){
        Long reqParam = 1L;

        when(customerRepository.findAllById(reqParam))
                .thenReturn(CustomerSupportTest.getCustomerList()
                        .get(0));
        Customer resp = customerService.getCustomer(reqParam);

        assertEquals(1,resp.getId().intValue());
        assertEquals("kapop",resp.getFirstName());
        assertEquals("pasinee",resp.getLastName());
        assertEquals("kapop@gmail.com",resp.getEmail());
        assertEquals("0811111111",resp.getPhoneNo());
        assertEquals(22,resp.getAge().intValue());

    }
    @DisplayName("Test get all customer by id should return list")
    @Test
    void testGetAllCustomerByName(){
        String name = "kapop";

        when(customerRepository.findByFirstName(name))
                .thenReturn(CustomerSupportTest.getCustomerNameKapopList());
        List<Customer> resp = customerService.getCustomerName(name);

        assertEquals(1,resp.get(0).getId().intValue());
        assertEquals("kapop",resp.get(0).getFirstName());
        assertEquals("pasinee",resp.get(0).getLastName());
        assertEquals("kapop@gmail.com",resp.get(0).getEmail());
        assertEquals("0811111111",resp.get(0).getPhoneNo());
        assertEquals(22,resp.get(0).getAge().intValue());

        assertEquals(2,resp.get(1).getId().intValue());
        assertEquals("kapop2",resp.get(1).getFirstName());
        assertEquals("pasinee2",resp.get(1).getLastName());
        assertEquals("kapop2@gmail.com",resp.get(1).getEmail());
        assertEquals("0822222222",resp.get(1).getPhoneNo());
        assertEquals(23,resp.get(1).getAge().intValue());

    }
    @DisplayName("Test create customer should return success")
    @Test
    void testCreateCustomer(){
        Customer reqCustomer = new Customer();

        reqCustomer.setFirstName("kapop3");
        reqCustomer.setLastName("pasinee3");
        reqCustomer.setEmail("kapop3@gmail.com");
        reqCustomer.setPhoneNo("0833333333");
        reqCustomer.setAge(24);

        when(customerRepository.save(reqCustomer)).thenReturn(CustomerSupportTest.getNewCustomer());
        Customer resp = customerService.createCustomer(reqCustomer);

        assertEquals(1,resp.getId().intValue());
        assertEquals("kapop3",resp.getFirstName());
        assertEquals("pasinee3",resp.getLastName());
        assertEquals("0833333333",resp.getPhoneNo());
        assertEquals(24,resp.getAge().intValue());

    }

    @DisplayName("Test update customer should return success")
    @Test
    void testUpdateCustomer(){
        Long reqId = 1L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("kapop3");
        reqCustomer.setLastName("pasinee3");
        reqCustomer.setEmail("kapop3@gmail.com");
        reqCustomer.setPhoneNo("0833333333");
        reqCustomer.setAge(24);

        when(customerRepository.findAllById(reqId)).thenReturn(CustomerSupportTest.getNewCustomer());
        when(customerRepository.save(reqCustomer)).thenReturn(CustomerSupportTest.getNewCustomer());
        Customer resp = customerService.updateCustomer(reqId,reqCustomer);

        assertEquals(1,resp.getId().intValue());
        assertEquals("kapop3",resp.getFirstName());
        assertEquals("pasinee3",resp.getLastName());
        assertEquals("0833333333",resp.getPhoneNo());
        assertEquals(24,resp.getAge().intValue());


    }
    @DisplayName("Test update customer should return fail")
    @Test
    void testUpdateCustomerFail(){
        Long reqId = 4L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("kapop3");
        reqCustomer.setLastName("pasinee3");
        reqCustomer.setEmail("kapop3@gmail.com");
        reqCustomer.setPhoneNo("0833333333");
        reqCustomer.setAge(24);

        when(customerRepository.findAllById(reqId)).thenReturn(null);
        Customer resp = customerService.updateCustomer(reqId, reqCustomer) ;
        assertEquals(null,resp);


    }
    @DisplayName("Test update customer should return success")
    @Test
    void testDeleteCustomer(){
        Long reqId = 1L;
        doNothing().when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteByID(reqId);
        assertTrue(resp);
    }
    @DisplayName("Test update customer should return success")
    @Test
    void testDeleteCustomerFail(){
        Long reqId = 1L;
        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteByID(reqId);
        assertFalse(resp);
    }




}