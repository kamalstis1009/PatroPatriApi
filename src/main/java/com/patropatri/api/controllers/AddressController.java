package com.patropatri.api.controllers;

import com.patropatri.api.models.*;
import com.patropatri.api.services.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService mService;

    //======================================================| Country
    @GetMapping(value="/countries", produces = {"application/json"})
    public ResponseEntity<List<Country>> getAllCountries() {
        return new ResponseEntity<>(mService.getAllCountries(), HttpStatus.OK);
    }

    //======================================================| Division
    @GetMapping(value="/divisions/{countryId}", produces = {"application/json"})
    public ResponseEntity<List<Division>> getAllDivisionsByCountryId(@PathVariable("countryId") long countryId) {
        return new ResponseEntity<>(mService.getAllDivisionsByCountryId(countryId), HttpStatus.OK);
    }

    //======================================================| District
    @GetMapping(value="/divisions/{divisionId}", produces = {"application/json"})
    public ResponseEntity<List<District>> getAllDistrictsByDivisionId(@PathVariable("divisionId") long divisionId) {
        return new ResponseEntity<>(mService.getAllDistrictsByDivisionId(divisionId), HttpStatus.OK);
    }

    //======================================================| PoliceStation
    @GetMapping(value="/divisions/{districtId}", produces = {"application/json"})
    public ResponseEntity<List<PoliceStation>> getAllPoliceStationsByDistrictId(@PathVariable("districtId") long districtId) {
        return new ResponseEntity<>(mService.getAllPoliceStationsByDistrictId(districtId), HttpStatus.OK);
    }

    //======================================================| PostalCode
    @GetMapping(value="/divisions/{districtId}", produces = {"application/json"})
    public ResponseEntity<List<PostalCode>> getAllPostalCodesByDistrictId(@PathVariable("districtId") long districtId) {
        return new ResponseEntity<>(mService.getAllPostalCodesByDistrictId(districtId), HttpStatus.OK);
    }


}
