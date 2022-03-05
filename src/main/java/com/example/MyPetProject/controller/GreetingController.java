package com.example.MyPetProject.controller;

import com.example.MyPetProject.model.User;
import com.example.MyPetProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        return "index";
    }
    @GetMapping("/registrationForm")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "registrationForm";
    }

    @RequestMapping(value = "/processForm", method = {RequestMethod.POST})
    public String addUser(@RequestParam String firstName, @RequestParam String lastName,
                          @RequestParam String email, @RequestParam String password,
                          @RequestParam String matchingPassword, Model model){
        User user = new User(firstName, lastName, email, password, matchingPassword);
        userRepository.save(user);
        return "redirect:/";
    }

    /*@GetMapping("/processForm")
    public String processForm(@ModelAttribute("user") User theUser, Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        System.out.println("User's info: " + theUser.toString());
        return "user-confirmation";
    }*/
}
