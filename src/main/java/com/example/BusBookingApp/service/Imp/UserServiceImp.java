package com.example.BusBookingApp.service.Imp;

import com.example.BusBookingApp.UserRequest;
import com.example.BusBookingApp.dto.UserDto;
import com.example.BusBookingApp.exception.UserNotFoundException;
import com.example.BusBookingApp.model.LoginRequest;
import com.example.BusBookingApp.model.Role;
import com.example.BusBookingApp.model.User;
import com.example.BusBookingApp.repository.UserRepository;
import com.example.BusBookingApp.service.Interface.UserService;
import com.example.BusBookingApp.service.JwtService;
import com.example.BusBookingApp.utils.UtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp  implements UserService {
    private final UserRepository userRepo;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;


    @Override
    public UserDto createUser(UserRequest userRequest) {
        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword())
        );
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setRole(Role.USER);

        User savedUser = userRepo.save(user);
        return UtilsService.toUserDto(savedUser);
    }

    @Override
    public UserDto getUser(Long userId) {
        User savedUser = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("The User Not Found!!!"));
        return UtilsService.toUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream().map(UtilsService::toUserDto)
                .toList();
    }

    @Override
    public void deleteUser(long userId) {
        userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("The User Not Found!!!"));
        userRepo.deleteById(userId);
    }

    @Override
    public UserDto updateUser(long userId,User updatedUser) {
        Optional<User> user=userRepo.findById(userId);
        if (user.isEmpty()) {
            throw  new UserNotFoundException("the user with id "+userId+"not found");
        }

        user.get().setUserId(updatedUser.getUserId());
        User savedUser =userRepo.save(user.get());
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
        User user = userRepo.findById(userID).orElseThrow(()-> new UserNotFoundException("User with Id"+userID+" not found"));
        user.withdraw(amount);
    }

    public BigDecimal withdraw(User user,BigDecimal amount){

        if (user.getWalletAmount().compareTo(BigDecimal.valueOf(0)) <0 || user.getWalletAmount().compareTo(amount)<0 ) {
            throw new RuntimeException("Invalid transaction");
        }

       user.getWalletAmount() =walletAmount.subtract(amount);
        this.setWalletAmount(walletAmount);
        return walletAmount;
    }

}
