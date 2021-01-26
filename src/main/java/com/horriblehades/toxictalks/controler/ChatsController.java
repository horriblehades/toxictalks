package com.horriblehades.toxictalks.controler;

import com.horriblehades.toxictalks.domain.Chat;
import com.horriblehades.toxictalks.domain.ChatMessage;
import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.repos.ChatMessageRepo;
import com.horriblehades.toxictalks.repos.ChatRepo;
import com.horriblehades.toxictalks.repos.UserRepo;
import com.horriblehades.toxictalks.service.ChatService;
import com.horriblehades.toxictalks.service.MessageService;
import com.horriblehades.toxictalks.service.ReportService;
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
import java.util.List;
import java.util.Set;

@PreAuthorize("hasAuthority('USER')")
@Controller
public class ChatsController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatMessageRepo chatMessageRepo;


    @GetMapping("/chats")
    public String chats(
            Model model,
            @AuthenticationPrincipal User user) {

        if (chatService.userIsBlocked(user)) {
            return "redirect:/";
        }

        Set<Chat> myChats = chatRepo.findChatToChatting(user.getId());
        model.addAttribute("myChats", myChats);
        return "chats";
    }

    @GetMapping("/chats/selectchat")
    public String selectChat(
            Model model,
            @RequestParam Long chatId,
            @AuthenticationPrincipal User user) {

        if (!chatRepo.findById(chatId).isPresent()) {
            return "redirect:/chats";
        }

        if (chatService.userIsBlocked(user)) {
            return "redirect:/";
        }

        Chat chat = chatRepo.findById(chatId).get();

        Set<Chat> myChats = chatRepo.findChatToChatting(user.getId());
        User currentUser = userRepo.findById(user.getId()).get();
        List<ChatMessage> messages = chatMessageRepo.findByChatOrderById(chat);

        if (!chat.getExistence() ||
                !((currentUser == chat.getCreator()) || (currentUser == chat.getParticipant()))
        ) {
            return "redirect:/chats";
        }


        model.addAttribute("currentChatId", chat.getId());
        model.addAttribute("myChats", myChats);
        model.addAttribute("titleTopic", chat.getName());
        model.addAttribute("chatMessages", messages);
        model.addAttribute("currentUser", user);
        return "chats";
    }

    @GetMapping("/chats/delete")
    public String deleteTopic(@RequestParam Long currentChatId,
                              @AuthenticationPrincipal User user) {
        if (chatService.userIsBlocked(user)) {
            return "redirect:/";
        }
        chatService.deactivationChat(currentChatId, user);
        return "redirect:/chats";
    }

    @PostMapping("/chats/send")
    public String sendMessage(
            @RequestParam Long currentChatId,
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @Valid ChatMessage chatMessage,
            BindingResult bindingResult) {

        if (!chatRepo.findById(currentChatId).isPresent()) {
            return "redirect:/chats";
        }

        if (chatService.userIsBlocked(user)) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            return ("redirect:/chats/selectchat?chatId=" + currentChatId);
        }

        messageService.createMessage(user, text, currentChatId);
        return ("redirect:/chats/selectchat?chatId=" + currentChatId);
    }

    @PostMapping("/chats/report")
    public String createReport(
            @AuthenticationPrincipal User user,
            @RequestParam Long messageId,
            @RequestParam Long currentChatId,
            @RequestParam String exampleRadios
    ) {

        if (!chatRepo.findById(currentChatId).isPresent()) {
            return "redirect:/chats";
        }

        if (chatService.userIsBlocked(user)) {
            return "redirect:/";
        }

        reportService.createReport(messageId, exampleRadios);
        return ("redirect:/chats/selectchat?chatId=" + currentChatId);
    }

}
