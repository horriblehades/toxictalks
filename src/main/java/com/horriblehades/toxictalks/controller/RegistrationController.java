package com.horriblehades.toxictalks.controller;


import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.service.UserService;
import com.horriblehades.toxictalks.service.ValidationService;
import com.horriblehades.toxictalks.utils.ErrorsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("/registration")
    public String registration() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          Model model
    ) {

        if (validationService.registrationValidate(user, model)) return "registration";

        if (bindingResult.hasErrors()) {

            Map<String, String> errors = ErrorsUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";

        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError",
                    "Пользователь с таким логином уже зарегистрирован."
            );
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {

        if (userService.activateUser(code)) {
            model.addAttribute("messageActivation", "Пользователь активирован.");
        } else {
            model.addAttribute("messageActivation", "Активационный код не найден");
        }

        return "login";
    }
}
