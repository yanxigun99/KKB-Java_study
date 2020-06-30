package com.abc.controller;

import com.abc.bean.Company;
import com.abc.bean.Country;
import com.abc.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/port")
    public String portHandler() {
        return "port = " + port;
    }

    // ------------------------

    @Value("${car.color}")
    private String color;

    @GetMapping("/color")
    public String colorHandler() {
        return "car.color = " + color;
    }

    // ------------------------

    @Autowired
    private Student student;

    @GetMapping("/student")
    public String studentHandler() {
        return "student = " + student;
    }


    // ------------------------------------

    @Autowired
    private Country country;

    @GetMapping("/country")
    public String countryHandler() {
        return "country = " + country.getCities();
    }

    // ------------------------------------


    @Autowired
    private Company company;

    @GetMapping("/company")
    public String companyHandler() {
        return "company = " + company.getDeparts();
    }

}
