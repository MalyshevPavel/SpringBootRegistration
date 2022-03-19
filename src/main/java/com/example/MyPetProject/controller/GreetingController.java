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
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/registrationForm")
    public String showForm() {
        return "registrationForm";
    }

    @RequestMapping(value = "/processForm", method = {RequestMethod.POST})
    public String addUser(@RequestParam String firstName, @RequestParam String lastName,
                          @RequestParam String email, @RequestParam String password,
                          @RequestParam String matchingPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(firstName, lastName, email, password, matchingPassword);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "registrationSuccess";
    }

    @GetMapping("/personAccount")
    public String accountLogin() {
        return "personAccount";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        Iterable<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/changePass")
    public String changePass() {
        return "changePass";
    }

    @PostMapping("/passUpdate")
    public String updatePass(@RequestParam String oldPassword,
                             @RequestParam String newPassword,
                             @RequestParam String passConfirm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (userRepository.findByPassword(oldPassword) != null) {
            User user = userRepository.findByPassword(oldPassword);
            String encodedPassword = encoder.encode(newPassword);
            user.setPassword(encodedPassword);
            user.setMatchingPassword(passConfirm);
            userRepository.save(user);
        }
        return "passUpdatedSuccess";
    }

    @GetMapping("/deleteUser")
    public String deleteUserForm(){
        return "deleteUser";
    }

    @PostMapping("deleteProcess")
    public String deleteAccount(@RequestParam String email){
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        return "accDeletedSuccessfully";
    }
}
