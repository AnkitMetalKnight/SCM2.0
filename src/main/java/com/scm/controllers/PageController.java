package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")
    public String home(Model model)
    {
        System.out.println("Home Page Handler");

        //sending data to view
        model.addAttribute("name", "Substring Technology");
        model.addAttribute("youtubeChannel", "Learning code from youtuber");
        model.addAttribute("githubRepo", "https://github.com/AnkitMetalKnight?tab=repositories");

        return "home";
    }


    //about route
    @RequestMapping("/about")   
    public String aboutPage()
    {
        System.out.println("About page loading");
        return "about";
    }


    //services
    @RequestMapping("/services")   
    public String servicesPage()
    {
        System.out.println("Services page loading");
        return "services";
    }

    //contact page
    @GetMapping("/contact")
    public String contact()
    {
        return new String("contact");
    }

    // //LOGIN PAGE
    // @PostMapping("/login")
    // public String login()
    // {
    //     return new String("login");
    // }

    // this is showing login page
    @GetMapping("/login")
    public String login()
    {
        return new String("login");
    }

    // registration page
    // REGISTER
    @GetMapping("/register")
    public String register(Model model)
    {
        UserForm userForm = new UserForm();
        //default data ko v dal skte h
        // userForm.setName("ankit");
        model.addAttribute("userForm", userForm);

        return "register";
    }

    // processing register

    @RequestMapping(value="/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session)
    {
        System.out.println("Processing registration");
        //fetch form data
        //userform
        System.out.println(userForm);

        //validata form data
        if(rBindingResult.hasErrors())
        {
            return "register";
        }

        //save to database

        //userService

        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://i.pinimg.com/564x/b2/ea/a0/b2eaa0d4918d54021f9c7aa3fc3d3cf3.jpg")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        // user.setEnabled(false);
        user.setProfilePic("https://i.pinimg.com/564x/b2/ea/a0/b2eaa0d4918d54021f9c7aa3fc3d3cf3.jpg");
        
        
        User savedUser = userService.saveUser(user);
        System.out.println("User saved:");

        //message = "Registration Successfull!"

        // add the message 

        Message message = Message.builder().content("Registration Successfull").type(MessageType.green).build();
        session.setAttribute("message", message);

        //redirect to login page
        return "redirect:/register";
    }
    
}
