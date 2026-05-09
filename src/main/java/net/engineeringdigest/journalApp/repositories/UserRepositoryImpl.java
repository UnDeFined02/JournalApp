package net.engineeringdigest.journalApp.repositories;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getUserSA(){
        Query query=new Query();
        //query.addCriteria(Criteria.where("mail").exists(true).ne("").ne(null));
        query.addCriteria(Criteria.where("mail").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println("DEBUG: Found " + users.size() + " users matching Sentiment Analysis criteria.");
        for(User user: users){
            System.out.println(user.getUsername());
        }
        return users;
    }

}
