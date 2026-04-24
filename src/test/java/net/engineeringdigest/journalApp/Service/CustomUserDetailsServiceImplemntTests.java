package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.repositories.UserRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;



public class CustomUserDetailsServiceImplemntTests {
    @InjectMocks
    private CustomUserDetailsServiceImplemnt customUserDetailsServiceImplemnt;
    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @Disabled("Temporary disabling until DB issue is fixed")
    public void loadUserByUsernameTest(){
        when(userRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn((net.engineeringdigest.journalApp.entity.User) User.builder().username("ammi").password("mswdnwj").roles(String.valueOf(new ArrayList<String>())).build());
        UserDetails user= customUserDetailsServiceImplemnt.loadUserByUsername("ammi");
        Assertions.assertNotNull(user);
    }

}
