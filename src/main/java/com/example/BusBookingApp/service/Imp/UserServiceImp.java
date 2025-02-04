package com.example.BusBookingApp.service.Imp;

import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.exception.UserNotFoundException;
import com.example.BusBookingApp.model.LoginRequest;
import com.example.BusBookingApp.model.User;
import com.example.BusBookingApp.repository.UserRepository;
import com.example.BusBookingApp.service.Interface.UserService;
import com.example.BusBookingApp.service.JwtService;
import com.example.BusBookingApp.utils.UtilsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImp  implements UserService {
    private final UserRepository repo;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public UserServiceImp(UserRepository repo,AuthenticationManager authManager, JwtService jwtService) {
        this.repo = repo;
        this.authManager= authManager;
        this.jwtService = jwtService;
    }


    @Override
    public UserDto addUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(
                bCryptPasswordEncoder
                        .encode(user.getPassword())
        );

        User savedUser = repo.save(user);
        return UtilsService.toUserDto(savedUser);
    }

    @Override
    public UserDto getUser(Long userId) {

        User savedUser = repo.findById(userId).orElseThrow(()-> new UserNotFoundException("The User Not Found!!!"));
        return UtilsService.toUserDto(savedUser);

    }

    @Override
    public List<UserDto> getAllUsers() {
        return repo.findAll()
                .stream().map(UtilsService::toUserDto)
                .toList();
    }

    @Override
    public void deleteUser(long userId) {
        repo.findById(userId).orElseThrow(()-> new UserNotFoundException("The User Not Found!!!"));
        repo.deleteById(userId);
    }

    @Override
    public UserDto updateUser(User user) {
        User savedUser =repo.save(user);
        return UtilsService.toUserDto(savedUser);
    }

    @Override
    public String login(LoginRequest user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

        if( authentication.isAuthenticated()){
            String token = jwtService.generateToken(user.getEmail());
            System.out.println("token is "+token);
            return token;
        }
        return  "failed";
    }

    @Override
    public void payment(Long userID, BigDecimal amount) {
        User user = repo.findById(userID).orElseThrow(()-> new UserNotFoundException("User with Id"+userID+" not found"));
        user.withdraw(amount);
    }

}
