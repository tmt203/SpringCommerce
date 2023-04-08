package com.tdtu.library.service.impl;

import com.tdtu.library.model.City;
import com.tdtu.library.repository.CityRepository;
import com.tdtu.library.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepo;

    @Override
    public List<City> getAll() {
        return cityRepo.findAll();
    }
}
