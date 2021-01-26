package com.horriblehades.toxictalks.repos;

import com.horriblehades.toxictalks.domain.Chat;
import com.horriblehades.toxictalks.domain.ChatMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepo extends CrudRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatOrderById(Chat chat);

    @Override
    Optional<ChatMessage> findById(Long messageId);

    List<ChatMessage> findByChat(Chat chat);
}
