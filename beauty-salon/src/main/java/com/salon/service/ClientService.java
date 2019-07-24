package com.salon.service;

import com.salon.entity.Client;
import com.salon.entity.RoleType;
import com.salon.repository.ClientRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepo;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepo,
                         PasswordEncoder passwordEncoder) {
        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Client save(@NonNull Client client) {
        client.setRole(RoleType.CLIENT);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepo.save(client);
    }

    public List<Client> getAll(){
        return clientRepo.findAll();
    }

    public Client findById(Long clientId) {
        return clientRepo.findById(clientId).orElseThrow(NoSuchElementException::new);
    }

    public Optional<Client> findByUsername(String clientUsername) {
        return clientRepo.findByUsername(clientUsername);
    }
}
