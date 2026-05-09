package net.engineeringdigest.journalApp.Controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Service.CustomUserDetailsServiceImplemnt;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.utilis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class Publiccontroller {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServiceImplemnt customUserDetailsServiceImplemnt;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
       boolean isSaved=  userService.savenewUser(user);
       if(isSaved) {
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
           return  new ResponseEntity<>("Username already exists",HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = customUserDetailsServiceImplemnt.loadUserByUsername(user.getUsername());
            String jwt=jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occured while creatingAuthToken",e);
            return new ResponseEntity<>("Incorrect Username or Password",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/health-check")
    public ResponseEntity<String> healthcheck(){
        return new ResponseEntity<>("Ok",HttpStatus.OK);
}

}