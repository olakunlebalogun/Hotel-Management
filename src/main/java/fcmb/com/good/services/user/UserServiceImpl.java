package fcmb.com.good.services.user;


import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.othersRequest.AuthRequest;
import fcmb.com.good.model.dto.request.userRequest.UserRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.UserResponse;
import fcmb.com.good.model.entity.user.User;
import fcmb.com.good.repo.user.UserRepository;
import fcmb.com.good.utills.JwtUtil;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public ApiResponse<AuthRequest> authenticate(AuthRequest authRequest)  {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if(!authentication.isAuthenticated()){
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        } else {
            jwtUtil.generateToken(authRequest.getUsername());
        }
        return new ApiResponse<AuthRequest>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(authentication,AuthRequest.class));
    }

    @Override
    public ApiResponse<List<UserResponse>> getListOfUsers(int page, int size) {
            List<User> userList = userRepository.findAll(PageRequest.of(page,size)).toList();
        if(userList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label,
                HttpStatus.OK.value(),
                Mapper.convertList(userList, UserResponse.class));
    }

    @Override
    public ApiResponse<UserResponse> addUsers(@RequestBody UserRequest request) {


        User user = Mapper.convertObject(request,User.class);
        user=userRepository.save(user);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(user,UserResponse.class));

    }

    @Override
    public ApiResponse<UserResponse> getUsersById(@RequestParam("id") UUID userId) {
        Optional<User> user = userRepository.findByUuid(userId);
        if(user.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        User us = user.get();
        return new ApiResponse<UserResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(us,UserResponse.class));

    }

    private User validateUser(UUID uuid){
        Optional<User> user = userRepository.findByUuid(uuid);
        if(user.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return user.get();
    }

   @Override
    public ApiResponse<UserResponse> updateUser(UUID userId, @RequestBody UserRequest request) {
        User user = validateUser(userId);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setUsername(request.getUsername());

        user = userRepository.save(user);
        return new ApiResponse<UserResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
               Mapper.convertObject(user,UserResponse.class));
    }

    @Override
    public ApiResponse<String> deleteUser(UUID userId) {
        User user = validateUser(userId);
        userRepository.delete(user);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
