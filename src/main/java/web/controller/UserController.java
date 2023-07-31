package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    List<User> addIfEmpty = new ArrayList<>(Arrays.asList(
            new User("Ivan", "Sidorov","ivan@mail.ru"),
            new User("Oleg", "Semyonov","oleg@gmail.ru"),
            new User("Dmitriy", "Petrov","dimasik@gmail.com"),
            new User("Denis", "Komarov","den273@mail.ru"),
            new User("Sergey", "Fedorov","serega374@gmail.com")
    ));

    @Autowired
    private ApplicationContext applicationContext;



    @GetMapping(value="/user")
    public String getUserForm(Model model) {

        UserService userService = applicationContext.getBean(UserService.class);

        //userService.add(new User("name5", "secname5", "email5@mail.com"));
//        User userModTest = userService.findUserById(59L);
//        userModTest.setLastName("mazafaka");
//        userService.modify(userModTest);
        List<User> listUsers = userService.listUsers();
        if (listUsers.size() == 0){
            addIfEmpty.stream().forEach(t-> userService.add(t));
            listUsers = List.copyOf(addIfEmpty);
        }
        model.addAttribute("UserTitle", "User Controller page");
        model.addAttribute("ListOfUsers", listUsers);
        return "user";
    }

    @GetMapping(value="/user_create")
    public String createUser(Map<String, Object> model){
        User newUser = new User();
        model.put("newuser", newUser);
        return"user_create";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("newuser") User newuser){
        applicationContext.getBean(UserService.class).add(newuser);
        return "redirect:/user";
    }

    @GetMapping(value="/modify")
    public String modifyUser(@RequestParam Long id, Map<String, Object> model){
        User userForMod = applicationContext.getBean(UserService.class).findUserById(id);
        model.put("userForMod", userForMod);
        return "user_modify";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userForMod") User user){

        applicationContext.getBean(UserService.class).modify(user);
        return "redirect:/user";
    }


    @GetMapping(value="/delete")
    public String deleteUser(@RequestParam Long id){
        UserService userService = applicationContext.getBean(UserService.class);
        User userForDel = userService.findUserById(id);
        userService.remove(userForDel);
        return "redirect:/user";
    }
}
