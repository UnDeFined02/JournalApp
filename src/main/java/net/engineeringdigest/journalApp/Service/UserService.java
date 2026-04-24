package net.engineeringdigest.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositories.JournalRepo;
import net.engineeringdigest.journalApp.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;



    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void savedEntry(User user){

        userRepo.save(user);
    }

    public boolean savenewUser(User user){
        try {
            user.setRoles(Arrays.asList("USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return true;
        } catch (Exception e) {
           log.info("username already exists for {}",user.getUsername());
           log.debug("falana dimkhana");
            return false;
        }

    }
    public void savenewAdmin(User user){
        user.setRoles(Arrays.asList("ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
    public List<User> getAll(){
        return userRepo.findAll();
    }
    public Optional<User> findbyid(String id){
        return userRepo.findById(id);
    }
    public  void deletebyid(String id){
        userRepo.deleteById(id);
    }
    public User findbyusername(String username){
        return userRepo.findByUsername(username);
    }
}
