package net.engineeringdigest.journalApp.repositories;

import net.engineeringdigest.journalApp.Controller.UserControllerv2;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepo extends MongoRepository<User, String> {
  User findByUsername(String username);

  void deleteByUsername(String username);
}
