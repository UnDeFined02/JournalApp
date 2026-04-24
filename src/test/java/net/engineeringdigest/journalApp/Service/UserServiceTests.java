package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Controller.Publiccontroller;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositories.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private JournalService journalService;
    @Autowired
    private UserService userService;

    @Test
    public void testfindbyusername(){

        assertNotNull(userRepo.findByUsername("maruf"));
        assertFalse(journalService.deletebyid("69dd186aa10d27b8fb3bf141","ammi"));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "maruf", "ammi", "abba"
    })
    public void test(String username){
        assertNotNull(userRepo.findByUsername(username));
    }



}
