package com.docker.init.data;

import com.docker.init.models.User;
import com.docker.init.repos.UserRepo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepo repo;

    public DataLoader(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        User newUser = new User();
        User newUser2 = new User();
        User newUser3 = new User();

        newUser.setFirstName("User1");
        newUser.setLastName("Alpha");
        newUser.setCountry("Alphaland");

        newUser2.setFirstName("User2");
        newUser2.setLastName("Beta");
        newUser2.setCountry("Betaland");

        newUser3.setFirstName("User3");
        newUser3.setLastName("Charlie");
        newUser3.setCountry("Charlieland");

        repo.save(newUser);
        repo.save(newUser2);
        repo.save(newUser3);
    }
}
