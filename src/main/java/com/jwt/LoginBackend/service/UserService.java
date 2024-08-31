package com.jwt.LoginBackend.service;

import com.jwt.LoginBackend.model.Emergency;
import com.jwt.LoginBackend.model.Users;
import com.jwt.LoginBackend.repo.EmergencyRepo;
import com.jwt.LoginBackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;
    @Autowired
    private EmergencyRepo emergencyRepo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {

        if (user.getId() != 0 && repo.existsById(user.getId())) {
            throw new RuntimeException("A user with this ID already exists.");
        }

        if (repo.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("A user with this username already exists.");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }

    public Users getuserdetails(String username) {
        return repo.findByUsername(username);
    }

    public Emergency  createemergency(@RequestBody  Emergency emergency) {
        return emergencyRepo.save(emergency);
    }
    public Iterable<Emergency> getallemergency() {
        return emergencyRepo.findAll();
    }
  public Emergency  getemergency(int eid) {
        return emergencyRepo.findById(eid).get();
    }

    public void deleteemergency(int eid) {
        emergencyRepo.deleteById(eid);
    }

    public  Emergency  updateemergencystatus(@PathVariable int eid) {
        Emergency emergency = emergencyRepo.findById(eid).get();
        emergency.setStatus("Resolved");
        return emergencyRepo.save(emergency);

    }
}
