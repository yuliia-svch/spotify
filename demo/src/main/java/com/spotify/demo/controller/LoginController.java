package com.spotify.demo.controller;

import com.spotify.demo.dto.UserDTO;
import com.spotify.demo.model.User;
import com.spotify.demo.service.IUserService;
import com.spotify.demo.web.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class LoginController {

    @Autowired
    IUserService userService;

    @GetMapping(value = "/")
    public ModelAndView showWelcomePage() {
        return new ModelAndView("welcome");
    }


    @GetMapping(value = {"/adminPage"})
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminPage");
        return model;
    }

    @GetMapping(value = "/loginPage")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("message", "Logged out from Spotify successfully.");
        }

        model.setViewName("loginPage");
        return model;
    }

    @GetMapping("/registerPage")
    public String showRegistrationForm(final HttpServletRequest request, final Model model) {
        final UserDTO accountDto = new UserDTO();
        model.addAttribute("user", accountDto);
        return "registerPage";
    }

    @PostMapping("/registerPage")
    public ModelAndView registration(@ModelAttribute("user") @Valid final UserDTO userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registerPage", "user", userForm);
        }
        try {
            userService.registerNewUserAccount(userForm);
        } catch (final UserAlreadyExistsException uaeEx) {
            ModelAndView mav = new ModelAndView("registerPage", "user", userForm);
            mav.addObject("message", "User under this username already exists");
            return mav;
        }
        return new ModelAndView("registerSuccess", "user", userForm);
    }

}
