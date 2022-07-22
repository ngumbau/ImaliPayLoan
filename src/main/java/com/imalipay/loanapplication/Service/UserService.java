package com.imalipay.loanapplication.Service;

import com.imalipay.loanapplication.DTO.UserRequest;
import com.imalipay.loanapplication.DTO.UserResponse;
import com.imalipay.loanapplication.Data.User;
import com.imalipay.loanapplication.Exception.LoanException;
import com.imalipay.loanapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserResponse createUser(UserRequest request) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .nationalId(request.getNationalId())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        user = userRepository.save(user);

        UserResponse response = UserResponse.builder()
                .user_id(user.getUserId())
                .first_name(user.getFirstName())
                .last_name(user.getLastName())
                .created_at(user.getCreatedAt().toString())
                .updated_at(user.getUpdatedAt().toString())
                .build();

        return response;
    }

    public User getUserById(Long id) {

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new LoanException(String.format("User with ID > '%s' cannot be found", id));
        }
        return user.get();
    }
}
