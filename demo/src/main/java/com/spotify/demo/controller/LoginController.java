package com.spotify.demo.controller;

import com.spotify.demo.dto.UserDTO;
import com.spotify.demo.model.User;
import com.spotify.demo.security.SecurityService;
import com.spotify.demo.service.IUserService;
import com.spotify.demo.validation.UserValidator;
import com.spotify.demo.web.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    private IUserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/")
    public ModelAndView showWelcomePage() {
        return new ModelAndView("index");
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
    public String registration(@ModelAttribute("user") @Valid final UserDTO userForm,
                               BindingResult bindingResult,
                               ModelMap map) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            map.put("user", userForm);
            return "registerPage";
        }
        try {
            userService.registerNewUserAccount(userForm);
        } catch (final UserAlreadyExistsException uaeEx) {
            map.put("user", userForm);
            map.put("message", "User under this username already exists");
            return "registerPage";
        }
        securityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/userPageAll";
    }

}
