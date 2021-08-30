package com.patropatri.api.services;

import com.patropatri.api.models.*;

import java.util.List;

public interface AddressService {

    List<Country> getAllCountries();
    List<Division> getAllDivisionsByCountryId(Long countryId);
    List<District> getAllDistrictsByDivisionId(Long countryId);
    List<PoliceStation> getAllPoliceStationsByDistrictId(Long countryId);
    List<PostalCode> getAllPostalCodesByDistrictId(Long districtId);

}
