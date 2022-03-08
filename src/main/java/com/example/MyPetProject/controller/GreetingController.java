package com.example.MyPetProject.controller;

import com.example.MyPetProject.model.User;
import com.example.MyPetProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showHomePage(Model model) {
        return "index";
    }
    @GetMapping("/registrationForm")
    public String showForm(Model model) {
        //model.addAttribute("user", new User());
        return "registrationForm";
    }

    @RequestMapping(value = "/processForm", method = {RequestMethod.POST})
    public String addUser(@RequestParam String firstName, @RequestParam String lastName,
                          @RequestParam String email, @RequestParam String password,
                          @RequestParam String matchingPassword, Model model){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(firstName, lastName, email, password, matchingPassword);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "registrationSuccess";
        //return "redirect:/";
    }
/*
    @GetMapping("/processForm")
    public String processForm(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user-confirmation";
    }
*/
    @GetMapping("/myLogin")
    public String login(Model model) {
        return "myLogin";
    }

    @GetMapping("/personAccount")
    public String accountLogin(Model model) {
        return "personAccount";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        Iterable<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
}
