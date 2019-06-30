package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDTO getAllUsers() {
        //TODO checking for an empty user list
        return new UsersDTO(userRepository.findAll());
    }

    public User findByUserLogin(UserDTO userDTO){
        //TODO check for user availability. password check
        return userRepository.findByEmail(userDTO.getEmail());
    }

    public void saveNewUser (User user){
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
