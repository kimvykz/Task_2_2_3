package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.UserService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model){

        List<String> messages = new ArrayList<>();
        messages.add("Welcome to my first project!");
        messages.add("I have just launched my first web application!");
        model.addAttribute("messages", messages);
        model.addAttribute("HelloTitle", "Task_2_2_3");
        model.addAttribute("userControllerPage", "user");


        return "index";

    }
}
