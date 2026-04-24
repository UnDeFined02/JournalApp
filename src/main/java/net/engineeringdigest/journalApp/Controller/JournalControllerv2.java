package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Service.JournalService;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalControllerv2 {


 @Autowired
 private JournalService journalService;
 @Autowired
 private UserService userService;
@GetMapping
 public ResponseEntity<?> getJournalEntryofUser(){
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    String username=authentication.getName();
    User user=userService.findbyusername(username);
    List<JournalEntry> list=user.getJournalEntries();
    if(list!=null&&!list.isEmpty()){
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
@GetMapping("id/{myid}")
public ResponseEntity<JournalEntry> getbyId(@PathVariable String myid){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    String username=authentication.getName();
    User user=userService.findbyusername(username);
    List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
    if(!collect.isEmpty()){
        Optional<JournalEntry> journalEntry= journalService.findbyid(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
    }


    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/id/{myid}")
public ResponseEntity<?> deleteById(@PathVariable String myid){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    String username=authentication.getName();
    boolean removed=journalService.deletebyid(myid,username);
    if(removed){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
@PutMapping("/id/{id}")
public ResponseEntity<?> updateById(@PathVariable String id,@RequestBody JournalEntry newEntry){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    String username=authentication.getName();
    User user=userService.findbyusername(username);
    List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
    if(!collect.isEmpty()){
        Optional<JournalEntry> journalEntry= journalService.findbyid(id);
        if(journalEntry.isPresent()){
            JournalEntry old=journalEntry.get();
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            journalService.savedEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
@PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
    try {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        journalService.savedEntry(myEntry,username);
        return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
    }catch(Exception e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
}