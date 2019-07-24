package com.salon.service;

import com.salon.entity.Master;
import com.salon.entity.RoleType;
import com.salon.repository.MasterRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class MasterService {

    private final MasterRepository masterRepo;
    private final PasswordEncoder passwordEncoder;

    public MasterService(MasterRepository masterRepo,
                         PasswordEncoder passwordEncoder) {
        this.masterRepo = masterRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Master save(@NonNull Master master) {
        master.setRole(RoleType.MASTER);
        master.setPassword(passwordEncoder.encode(master.getPassword()));
        return masterRepo.save(master);
    }

    public List<Master> getAll(){
        return masterRepo.findAll();
    }

    public Master findById(Long masterId) {
        return masterRepo.findById(masterId).orElseThrow(NoSuchElementException::new);
    }
}
