package com.training.service;

import com.training.dto.UserDTO;
import com.training.dto.UsersDTO;
import com.training.entity.RoleType;
import com.training.entity.User;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.training.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class UserService{

    private final UserRepository userRepository;
   // private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository /*, BCryptPasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
     //   this.passwordEncoder = passwordEncoder;
    }

    public UsersDTO getAll() {
        return new UsersDTO(userRepository.findAll());
    }

    public Optional<User> findByUsername(String username){
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public void register(@NonNull User user){
        user.setRole(RoleType.USER);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

}
