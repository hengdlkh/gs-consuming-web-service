package com.example.consumingwebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.consumingwebservice.CountryClient;
import com.example.consumingwebservice.wsdl.GetCountryResponse;

@RestController
@RequestMapping("/country")
public class SoapClientController {

    @Autowired
    private CountryClient countryClient;

    @GetMapping("/{country}")
    public ResponseEntity<Object> getCountry(@PathVariable("country") String country) {
        try {
            GetCountryResponse response = countryClient.getCountry(country);
            
            String capital = response.getCountry().getCapital();
            
            return new ResponseEntity<Object>("{\"id\": \""+ capital +"\"}", HttpStatus.OK);
        }catch(Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
