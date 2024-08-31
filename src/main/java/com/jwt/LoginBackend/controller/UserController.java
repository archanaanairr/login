package com.jwt.LoginBackend.controller;


import com.jwt.LoginBackend.model.Emergency;
import com.jwt.LoginBackend.model.Users;
import com.jwt.LoginBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return service.verify(user);
    }

    @GetMapping("/userdetails/{username}")
    public Users userdetails(@PathVariable String username){
        return service.getuserdetails(username);
    }

    @PostMapping("/createemergency")
    public Emergency createemergency(@RequestBody Emergency emergency) {
        return service.createemergency(emergency);
    }

    @GetMapping("/getemergency/{eid}")
    public Emergency getemergency(@PathVariable int eid) {
        return service.getemergency(eid);
    }

    @PutMapping
    public Emergency updateemergencystatus(@PathVariable int eid) {
        return service.updateemergencystatus(eid);
    }

    @DeleteMapping
    public  void deleteemergency(@PathVariable int eid) {
        service.deleteemergency(eid);
    }
}
