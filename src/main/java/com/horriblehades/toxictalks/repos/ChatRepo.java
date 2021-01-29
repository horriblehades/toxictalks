package com.horriblehades.toxictalks.repos;

import com.horriblehades.toxictalks.domain.Chat;
import com.horriblehades.toxictalks.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ChatRepo extends CrudRepository<Chat, Long> {

    @Query(value = "SELECT * FROM chat " +
            "WHERE name = ?1 " +
            "AND " +
            "attitude != ?2 " +
            "AND " +
            "creator_id != ?3 "+
            "AND " +
            "participant_id IS NULL " +
            "AND " +
            "existence = false " +
            "LIMIT 1", nativeQuery = true)
    Optional<Chat> findChatToCreate(String name, Boolean approve, Long userId);

    @Query(value = "SELECT * FROM chat " +
            "WHERE participant_id IS NULL " +
            "AND " +
            "existence = false " +
            "AND " +
            "creator_id != ?1 " +
            "LIMIT 40", nativeQuery = true)
    Set<Chat> findChatToShow(Long id);

    @Query(value = "SELECT * FROM chat " +
            "WHERE creator_id = ?1 " +
            "AND " +
            "participant_id IS NULL", nativeQuery = true)
    Set<Chat> findUserTopics(Long userId);

    @Query(value = "SELECT * FROM chat " +
            "WHERE (creator_id = ?1 " +
            "OR " +
            "participant_id = ?1) " +
            "AND " +
            "existence = true", nativeQuery = true)
    Set<Chat> findChatToChatting(Long id);

    Set<Chat> findByCreator(User user);
    Optional<Chat> findById(Long id);

}
