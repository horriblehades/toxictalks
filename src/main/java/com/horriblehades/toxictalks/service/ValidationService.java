package com.horriblehades.toxictalks.service;

import com.horriblehades.toxictalks.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ValidationService {

    public boolean registrationValidate(User user, Model model) {

        String password = user.getPassword();

        if (password != null && !password.equals(user.getPassword2())) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return true;
        }

        return passwordLengthValidate(password, model);
    }


    public boolean passwordLengthValidate(String password, Model model) {

        if (password.equals("")) {
            model.addAttribute("passwordError", "Вы забыли ввести пароль!");
            return true;
        }

        if (password.length() < 5) {
            model.addAttribute("passwordError", "Минимальная длина пароля 5 символов!");
            return true;
        }
        if (password.length() > 20) {
            model.addAttribute("passwordError", "Максимальная длина пароля 20 символов!");
            return true;
        }
        return false;
    }

}
