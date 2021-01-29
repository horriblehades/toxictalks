package com.horriblehades.toxictalks.controller;

import com.horriblehades.toxictalks.domain.Chat;
import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.service.ChatService;
import com.horriblehades.toxictalks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class TopicsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;


    @GetMapping("/mytopics")
    public String mytopics(Model model,
                           @AuthenticationPrincipal User user) {

        if (userService.userIsBlocked(user)) {
            return "redirect:/";
        }

        Set<Chat> myChats = chatService.findUserTopics(user);
        Set<Chat> chats = chatService.findChatToShow(user);

        model.addAttribute("myChats", myChats);
        model.addAttribute("chats",chats);

        return "topics";
    }

    @GetMapping("/mytopics/delete")
    public String deleteRedTopic(@RequestParam Long myChatId,
    @AuthenticationPrincipal User user) {

        if (userService.userIsBlocked(user)) {
            return "redirect:/";
        }

        chatService.deleteTopic(myChatId);

        return "redirect:/mytopics";
    }
    @GetMapping("/mytopics/joinchat")
    public String joinChat(@RequestParam Long chatId,
                           @AuthenticationPrincipal User user) {

        if (userService.userIsBlocked(user)) {
            return "redirect:/";
        }

        if (!chatService.joinChat(chatId, user)) {
            return "redirect:/mytopics";
        }

        return "redirect:/chats";
    }

    @PostMapping("/mytopics")
    public String addTopic(@AuthenticationPrincipal User user,
                           @RequestParam String name,
                           @RequestParam String exampleRadios,
                           @Valid Chat chat,
                           BindingResult bindingResult,
                           Model model) {

        if (userService.userIsBlocked(user)) {
            return "redirect:/";
        }

        Set<Chat> chats = chatService.findChatByCreator(user);

        if(chats.size() == 5) {
            return "redirect:/mytopics";
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/mytopics";
        }

        if(!chatService.createChat(user, name, exampleRadios)) {
            return "redirect:/chats";
        }

        return "redirect:/mytopics";
    }

}
