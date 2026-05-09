package net.engineeringdigest.journalApp.repositories;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigRepo extends MongoRepository<ConfigJournalAppEntity, String> {


}
