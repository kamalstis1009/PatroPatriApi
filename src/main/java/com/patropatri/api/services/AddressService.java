package com.patropatri.api.services;

import com.patropatri.api.models.*;

import java.util.List;

public interface AddressService {

    List<Country> getAllCountries();
    List<Division> getAllDivisionsByCountryId(Long countryId);

}
