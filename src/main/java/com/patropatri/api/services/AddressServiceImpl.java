package com.patropatri.api.services;

import com.patropatri.api.models.*;
import com.patropatri.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private CountryRepository mCountryRepository;

    @Autowired
    private DivisionRepository mDivisionRepository;

    @Autowired
    private DistrictRepository mDistrictRepository;

    @Autowired
    private PoliceStationRepository mPoliceStationRepository;

    @Autowired
    private PostalCodeRepository mPostalCodeRepository;

    //======================================================| Country
    @Override
    public List<Country> getAllCountries() {
        return mCountryRepository.findAll();
    }

    //======================================================| Country
    @Override
    public List<Division> getAllDivisionsByCountryId(Long countryId) {
        return mDivisionRepository.findAll();
    }

}
