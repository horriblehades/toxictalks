package com.horriblehades.toxictalks.service;

import com.horriblehades.toxictalks.domain.Chat;
import com.horriblehades.toxictalks.domain.ChatMessage;
import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.repos.ChatMessageRepo;
import com.horriblehades.toxictalks.repos.ChatRepo;
import com.horriblehades.toxictalks.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private ChatMessageRepo chatMessageRepo;

    @Autowired
    private UserRepo userRepo;


    public Boolean createChat(User user, String name, String exampleRadios) {
        Boolean approve = true;

        if (exampleRadios.equals("option2")) {
            approve = false;
        }

        Optional<Chat> chatToCreate = chatRepo.findChatToCreate(name, approve, user.getId());
        Chat chat;
        if (!chatToCreate.isPresent()) {
            chat = new Chat();
            chat.setCreator(user);
            chat.setName(name);
            chat.setAttitude(approve);
            chat.setExistence(false);
            chatRepo.save(chat);
            return true;
        } else {
            chat = chatToCreate.get();
            chat.setParticipant(user);
            chat.setExistence(true);
            chatRepo.save(chat);
            return false;
        }
    }

    public boolean joinChat(Long chatId, User user) {
        Chat chat = chatRepo.findById(chatId).get();
        if (chat.getParticipant() == null) {
            chat.setParticipant(user);
            chat.setExistence(true);
            chatRepo.save(chat);
            return true;
        } else {
            return false;
        }
    }

    public void deactivationChat(Long currentChatId, User user) {
        Chat chat = chatRepo.findById(currentChatId).get();
        User currentUser = userRepo.findById(user.getId()).get();
        if (currentUser == chat.getParticipant() || currentUser == chat.getCreator()) {
          chat.setExistence(false);
          chatRepo.save(chat);
        }
    }

    public boolean userIsBlocked(User user) {
        return !userRepo.findById(user.getId()).get().isActive();
    }

}
