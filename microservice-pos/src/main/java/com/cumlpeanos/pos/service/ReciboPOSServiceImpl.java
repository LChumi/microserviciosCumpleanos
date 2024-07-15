package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.repository.ReciboPOSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReciboPOSServiceImpl implements IReciboPOSService{

    @Autowired
    private ReciboPOSRepository reciboPOSRepository;
}
