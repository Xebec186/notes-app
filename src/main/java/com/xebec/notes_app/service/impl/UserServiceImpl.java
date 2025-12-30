package com.xebec.notes_app.service.impl;

import com.xebec.notes_app.dto.RegisterDto;
import com.xebec.notes_app.exception.UsernameAlreadyExistsException;
import com.xebec.notes_app.model.User;
import com.xebec.notes_app.repository.UserRepository;
import com.xebec.notes_app.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterDto registerDto) throws UsernameAlreadyExistsException {
        String username = registerDto.getUsername();
        if(userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("A user with that username already exists");
        }

        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }
}
