package com.horriblehades.toxictalks.controler;

import com.horriblehades.toxictalks.domain.Chat;
import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.repos.ChatRepo;
import com.horriblehades.toxictalks.service.ChatService;
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
    private ChatRepo chatRepo;

    @Autowired
    private ChatService chatService;


    @GetMapping("/mytopics")
    public String mytopics(Model model,
                           @AuthenticationPrincipal User user) {

        if (chatService.userIsBlocked(user)) {
            return "redirect:/";
        }

        Set<Chat> myChats = chatRepo.findMyTopics(user.getId());
        Set<Chat> chats = chatRepo.findChatToShow(user.getId());
        model.addAttribute("myChats", myChats);
        model.addAttribute("chats",chats);
        return "topics";
    }

    @GetMapping("/mytopics/delete")
    public String deleteRedTopic(@RequestParam Long myChatId,
    @AuthenticationPrincipal User user) {

        if (chatService.userIsBlocked(user)) {
            return "redirect:/";
        }

        chatRepo.delete(chatRepo.findById(myChatId).get());
        return "redirect:/mytopics";
    }
    @GetMapping("/mytopics/joinchat")
    public String joinChat(@RequestParam Long chatId,
                           @AuthenticationPrincipal User user) {

        if (chatService.userIsBlocked(user)) {
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

        if (chatService.userIsBlocked(user)) {
            return "redirect:/";
        }

        Set<Chat> chats = chatRepo.findByCreator(user);

        if(chats.size() == 5) {
            return "redirect:/mytopics";
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/mytopics";
        }

        String currentName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        if(!chatService.createChat(user, currentName, exampleRadios)) {
            return "redirect:/chats";
        }

        return "redirect:/mytopics";
    }

}
