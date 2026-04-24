package net.engineeringdigest.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositories.JournalRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalService  {
    @Autowired
    private JournalRepo journalrepo;
    @Autowired
    private UserService userService;


    @Transactional
    public void savedEntry(JournalEntry journalEntry, String username){
        try {
            User user=userService.findbyusername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved=journalrepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.savedEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred",e);
        }

    }
    public void savedEntry(JournalEntry journalEntry){
        journalrepo.save(journalEntry);

    }
    public List<JournalEntry> getAll(){
        return journalrepo.findAll();
    }
    public Optional<JournalEntry> findbyid(String id){
        return journalrepo.findById(id);
    }
    @Transactional
    public  boolean deletebyid(String id, String username){
        boolean removed=false;
        try {
            User user = userService.findbyusername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.savedEntry(user);
                journalrepo.deleteById(id);
            }

        } catch (Exception e) {
            log.error("Error",e);
            throw new RuntimeException("An error occured while deleting entry",e);
        }
        return removed;
    }

}
