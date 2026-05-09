package net.engineeringdigest.journalApp.cache;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repositories.ConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }
    public Map<String, String> appCache;

    @Autowired
    private ConfigRepo configRepo;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configRepo.findAll();

        System.out.println("Total documents found in config_journalApp: " + all.size());

        for (ConfigJournalAppEntity entity : all) {
         appCache.put(entity.getKey(),entity.getValue());
        }
    }
}