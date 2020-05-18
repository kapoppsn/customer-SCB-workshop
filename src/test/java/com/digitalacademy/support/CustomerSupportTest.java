package com.digitalacademy.support;

import com.digitalacademy.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerSupportTest {

    public static Customer createNewCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("kapop4");
        customer.setLastName("pasinee4");
        customer.setEmail("kapop4@gmail.com");
        customer.setPhoneNo("0844444444");
        customer.setAge(25);
        return customer;

    }

    public static Customer responseCreateNewCustomer() {
        Customer customer = new Customer();
        customer.setId(8L);
        customer.setFirstName("kapop4");
        customer.setLastName("pasinee4");
        customer.setEmail("kapop4@gmail.com");
        customer.setPhoneNo("0855555555");
        customer.setAge(26);
        return customer;

    }

    public static Customer getNewCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kapop3");
        customer.setLastName("pasinee3");
        customer.setEmail("kapop3@gmail.com");
        customer.setPhoneNo("0833333333");
        customer.setAge(24);
        return customer;
    }

    public static Customer getUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kapop32");
        customer.setLastName("pasinee3");
        customer.setEmail("kapop3@gmail.com");
        customer.setPhoneNo("0833333333");
        customer.setAge(24);
        return customer;
    }

    public static List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kapop");
        customer.setLastName("pasinee");
        customer.setEmail("kapop@gmail.com");
        customer.setPhoneNo("0811111111");
        customer.setAge(22);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("kapop2");
        customer.setLastName("pasinee2");
        customer.setEmail("kapop2@gmail.com");
        customer.setPhoneNo("0822222222");
        customer.setAge(23);
        customerList.add(customer);

        return customerList;
    }

    public static List<Customer> getCustomerNameKapopList() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kapop");
        customer.setLastName("pasinee");
        customer.setEmail("kapop@gmail.com");
        customer.setPhoneNo("0811111111");
        customer.setAge(22);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("kapop2");
        customer.setLastName("pasinee2");
        customer.setEmail("kapop2@gmail.com");
        customer.setPhoneNo("0822222222");
        customer.setAge(23);
        customerList.add(customer);

        return customerList;
    }
}
