package service;

import repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private List<User> userList=new ArrayList<>();



    public List<User> fetchusers(){

        return userRepository.findAll();
    }
    public void addusers( User user){

        userRepository.save(user);
    }
    public Optional<User> fetchAuser(Long id){
//        for(User user: userList){
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
//        return null;
//
        return userRepository.findById(id);
    }
    public boolean updateUser(Long id,User updatedUser ){
        return userRepository.findById(id)
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
                .map(existingUser ->{
                    existingUser.setFirstname(updatedUser.getFirstname());
                    existingUser.setLastname(updatedUser.getLastname());
                   userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
