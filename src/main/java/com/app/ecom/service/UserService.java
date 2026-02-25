package com.app.ecom.service;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.app.ecom.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private List<User> userList=new ArrayList<>();



    public List<UserResponse> fetchusers(){

        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }
    public void addusers( UserRequest userRequest){
        User user=new User();
        updateUserFromRequest(user,userRequest);
        userRepository.save(user);
    }



    public Optional<UserResponse> fetchAuser(Long id){
//        for(User user: userList){
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
//        return null;
//
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }
    public boolean updateUser(Long id,UserRequest updatedUserRequest ){
        return userRepository.findById(id)
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
                .map(existingUser ->{
                    updateUserFromRequest(existingUser,updatedUserRequest);
                   userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
    private void updateUserFromRequest(User user, UserRequest userRequest) {
    user.setFirstname(userRequest.getFirstname());
    user.setLastname(userRequest.getLastname());
    user.setEmail(userRequest.getEmail());
    user.setPhno(userRequest.getPhno());
    if(userRequest.getAddress()!=null){
        Address address=new Address();
        address.setCity(userRequest.getAddress().getCity());
        address.setState(userRequest.getAddress().getState());
        address.setZipcode(userRequest.getAddress().getZipcode());
        address.setCountry(userRequest.getAddress().getCountry());

        address.setStreet(userRequest.getAddress().getStreet());
        user.setAddress(address);
    }

    }
    private UserResponse mapToUserResponse(User user){
    UserResponse response=new UserResponse();
    response.setId(String.valueOf(user.getId()));
    response.setFirstname(user.getFirstname());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setPhno(user.getPhno());
        response.setRole(user.getRole());
        if(user.getAddress()!=null){
            AddressDTO addressDTO=new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setState(user.getAddress().getState());
            response.setAddress(addressDTO);
        }
        return response;
    }
}
