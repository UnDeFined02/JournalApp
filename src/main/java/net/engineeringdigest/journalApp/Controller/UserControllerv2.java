package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.JournalService;
import net.engineeringdigest.journalApp.Service.QuoteService;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.Service.WeatherService;
import net.engineeringdigest.journalApp.api.response.QuoteResponse;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserControllerv2 {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private QuoteService quoteService;

    @GetMapping("/weather")
   public ResponseEntity<?> greeting(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getweather("Mumbai");
        String greeting="";
        if(weatherResponse!=null){
            greeting=", Weather feels like "+weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hi "+authentication.getName()+greeting,HttpStatus.OK);
    }
    @GetMapping("/quote")
    public ResponseEntity<?> quote(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        QuoteResponse quoteResponse = quoteService.getquote();
        String quote="";
        if(quoteResponse!=null){
            quote=", The Quote of the day is "+quoteResponse.getQuote();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+quote,HttpStatus.OK);

    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userInDB=userService.findbyusername(username);
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());
        userService.savenewUser(userInDB);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        userRepo.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}