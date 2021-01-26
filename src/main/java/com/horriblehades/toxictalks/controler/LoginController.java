package com.horriblehades.toxictalks.controler;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(String error, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        if(error != null) {
            model.addAttribute("error", "Вы ввели не верный логин или пароль. " +
                    "Проверьте почту, вомзожно вы не активировали аккаунт.");
        }

        return "login";
    }

}
