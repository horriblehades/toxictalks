package com.horriblehades.toxictalks.service;

import com.horriblehades.toxictalks.domain.Chat;
import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.repos.ChatMessageRepo;
import com.horriblehades.toxictalks.repos.ChatRepo;
import com.horriblehades.toxictalks.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserRepo userRepo;


    public Set<Chat> findChatToChatting(User user) {
        return chatRepo.findChatToChatting(user.getId());
    }

    public Chat findChatById(Long chatId) {
        return chatRepo.findById(chatId).get();
    }

    public Set<Chat> findUserTopics(User user) {
        return chatRepo.findUserTopics(user.getId());
    }

    public Set<Chat> findChatToShow(User user) {
        return chatRepo.findChatToShow(user.getId());
    }

    public Set<Chat> findChatByCreator(User user) {
        return chatRepo.findByCreator(user);
    }

    public boolean chatIsExist(Long chatId) {
        return chatRepo.findById(chatId).isPresent();
    }

    public void deleteTopic(Long myChatId) {
        chatRepo.delete(chatRepo.findById(myChatId).get());
    }

    public Boolean createChat(User user, String name, String exampleRadios) {

        String currentName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        Boolean approve = true;

        if (exampleRadios.equals("option2")) {
            approve = false;
        }

        Optional<Chat> chatToCreate = chatRepo.findChatToCreate(currentName, approve, user.getId());
        Chat chat;
        if (!chatToCreate.isPresent()) {
            chat = new Chat();
            chat.setCreator(user);
            chat.setName(currentName);
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

}
