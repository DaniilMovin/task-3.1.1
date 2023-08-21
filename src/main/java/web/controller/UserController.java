package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String getAllUsers(ModelMap model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/create")
    public String showUserCreationPage(ModelMap map) {
        final User user = new User();
        map.addAttribute("user", user);
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, ModelMap map) {
        userService.saveUser(user);
        final String message = "User was added";
        map.addAttribute("user", new User());
        map.addAttribute("message", message);
        return "create";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, HttpSession session) {
        userService.removeUser(id);
        session.setAttribute("message", "User was deleted");
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String showUpdatePage(@RequestParam("id") Long id, ModelMap map) {
        map.addAttribute("user", userService.getUser(id));
        return "update";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, HttpSession session) {
        userService.updateUser(user);
        session.setAttribute("message", "User was updated");
        return "redirect:/users";
    }}


