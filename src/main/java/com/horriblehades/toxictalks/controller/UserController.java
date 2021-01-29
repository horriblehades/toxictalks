package com.horriblehades.toxictalks.controller;

import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.service.UserService;
import com.horriblehades.toxictalks.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email,
                                Model model
    ) {

        validationService.passwordLengthValidate(password, model);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        userService.updateProfile(user, password, email);

        return "profile";
    }

}
