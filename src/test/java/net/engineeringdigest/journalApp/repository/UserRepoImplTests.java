package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.repositories.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepoImplTests {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testSaveuser(){
        assertNotNull(userRepository.getUserSA());
    }

}
