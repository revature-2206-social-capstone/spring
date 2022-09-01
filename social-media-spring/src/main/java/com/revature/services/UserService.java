package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Deprecated
    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
    
    @Override
    public Optional<User> findByUsernameCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public List<User> searchUsers(String search) {
    	List<User> users = userRepository.findAll();
    	String username;
    	for(int x=0;x<users.size();x++) {
    		username=users.get(x).getUsername();
    		int y, w;
    		charMatch:
    		for(y=0, w=0;y<search.length();y++) {
    			while(w<username.length()) {
    				if (search.charAt(y)==username.charAt(w)) {
    					continue charMatch;
    				}
    				w++;
    			}
    			break;//If the length of username ran out.
    		}
    		if (w==username.length()) {
    			users.remove(users.get(x));
    		}
    	}
    	return users;
    }
   
}

