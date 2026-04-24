package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class Publiccontroller {
    @Autowired
    private UserService userService;
      @PostMapping("/createuser")
   public ResponseEntity<?> createUser(@RequestBody User user){
       boolean isSaved=  userService.savenewUser(user);
       if(isSaved) {
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
       return  new ResponseEntity<>("Username already exists",HttpStatus.CONFLICT);
    }
@GetMapping("/health-check")
public ResponseEntity<String> healthcheck(){
    return new ResponseEntity<>("Ok",HttpStatus.OK);
}

}