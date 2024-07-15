package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.repository.CajaPOSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CajaPOSServiceImpl implements ICajaPOSService {

    @Autowired
    private CajaPOSRepository cajaPOSRepository;
}
