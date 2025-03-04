package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.services.UserService;

@Controller("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listPets(Model model) {
        Collection<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/users";
    }

    @GetMapping("/new")
    public String newPet() {
        return "users/new-user";
    }

    @PostMapping("/new")
    public String createPet(User user) {
        userService.createUser(user);
        return "redirect:/pets";
    }

    @GetMapping("/{id}")
    public String getPetById(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/user-details";
    }
    

    @GetMapping("/{id}/edit")
    public String editPet(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/edit-user";
    }

    @PostMapping("/{id}/edit")
    public String updatePet(@PathVariable Long id, User user) {
        userService.updateUser(id, user);
        return "redirect:/pets/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deletePet(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/pets";
    }
}
