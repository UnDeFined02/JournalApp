package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
@GetMapping("/all-users")
    public ResponseEntity<?> getalluser(){
    List<User> list= userService.getAll();
    if(list!=null&&!list.isEmpty()){
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
@PostMapping("/create-admin")
    public ResponseEntity<?> createadmin(@RequestBody User user){
    userService.savenewAdmin(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
}


}
