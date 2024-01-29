package com.gb.seminar3.controllers;

/**
 * Для примера используем JSON
 * [{"id":1, "name":"Ivan", "age":32}, {"id":2, "name":"Olga", "age":30}, {"id":3, "name":"Irina", "age":27}]
 */

import com.gb.seminar3.models.User;
import com.gb.seminar3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String allUsers(Model model) {
        List<User> all = userService.findAll();
        model.addAttribute("users", all);
        return "users/list";
    }

    @GetMapping("/user/id{id}")
    public String index(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "users/index";
    }
    @GetMapping("/add_user")
    public String addUser() {
        return "users/add";
    }

    @PostMapping("/create_user")
    public String add(@RequestParam String name, @RequestParam Integer age) {
        userService.save(name, age);
        return "redirect:/users";
    }


    @PostMapping("/delete_user")
    @ResponseBody
    public Object deleteObj(@RequestParam Long id) {
        userService.deleteObjById(id);
        Map<String, Object> object = new HashMap<>();
        object.put("answer", "ok");

        return object;
    }

    @GetMapping("/edit_user/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "users/edit";
    }



    @PostMapping("/edit_user")
    public String edit(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "age") Integer age) {
        userService.edit(name, age, id);

        return "redirect:/users";
    }

    @GetMapping(value = "/filter/{age}")
    public ResponseEntity<List<User>> filterByAge(@PathVariable(name = "age", required = false) Integer age) {
        System.out.println(age);
        return new ResponseEntity<>(userService.filterUserByAge(userService.findAll(), age), HttpStatus.OK);
    }

    @PostMapping(value = "/sort")
    public ResponseEntity<List<User>> sortUser(@RequestBody List<User> UserList) {
        return new ResponseEntity<>(userService.sortUserByAge(UserList), HttpStatus.OK);
    }

    @PostMapping(value = "/average")
    public ResponseEntity<Double> average(@RequestBody List<User> UserList) {
        return new ResponseEntity<>(userService.calculateAverageAge(UserList), HttpStatus.OK);
    }




}
