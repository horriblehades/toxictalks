package com.horriblehades.toxictalks.service;

import com.horriblehades.toxictalks.domain.Chat;
import com.horriblehades.toxictalks.domain.ChatMessage;
import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.repos.ChatMessageRepo;
import com.horriblehades.toxictalks.repos.ChatRepo;
import com.horriblehades.toxictalks.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ChatMessageRepo chatMessageRepo;

    public void createMessage(User user, String text, Long currentChatId) {

        if (text != null && text.length() < 200) {

            ChatMessage message = new ChatMessage();
            Chat chat = chatRepo.findById(currentChatId).get();
            User currentUser = userRepo.findById(user.getId()).get();

            message.setAttitude(
                    (chat.getAttitude() && (chat.getCreator() == currentUser)) ||
                            (!chat.getAttitude() && (chat.getParticipant() == currentUser))
            );

            message.setAuthor(user);
            message.setChat(chat);
            message.setText(text);
            chatMessageRepo.save(message);
        }
    }
}
