package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.downloadablefox.tabbies.webserver.dtos.UserCreateDTO;
import dev.downloadablefox.tabbies.webserver.dtos.UserGetDTO;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.services.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public Collection<UserGetDTO> listUsers(Model model) {
        return userService.getAllUsers()
            .stream()
            .map(modelMapper::toUserDTO)
            .toList();
    }

    @PostMapping("/")
    @ResponseBody
    public UserGetDTO createUser(@RequestBody UserCreateDTO userCreateDTO) {
        User user = modelMapper.toUserEntity(userCreateDTO);
        userService.createUser(user);
        return modelMapper.toUserDTO(user);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserGetDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return modelMapper.toUserDTO(user);
    }
    
    @PutMapping("/{id}")
    public UserGetDTO updateUser(@PathVariable Long id, @RequestBody UserCreateDTO userCreateDTO) {
        User user = modelMapper.toUserEntity(userCreateDTO);
        userService.updateUser(id, user);
        return modelMapper.toUserDTO(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
