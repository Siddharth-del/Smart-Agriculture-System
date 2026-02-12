package com.SmartAgriculture.Cropp.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SmartAgriculture.Cropp.dtos.RegisterRequest;
import com.SmartAgriculture.Cropp.dtos.RegisterResponse;
import com.SmartAgriculture.Cropp.model.User;
import com.SmartAgriculture.Cropp.repository.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public RegisterResponse createUser(RegisterRequest request){
        User user=new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        
        User saved=userRepository.save(user);
        saved.getCreatedAt();
        return modelMapper.map(saved, RegisterResponse.class); 
    }
}
