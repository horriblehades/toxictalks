package com.horriblehades.toxictalks.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым.")
    @Length(max = 40, message = "Тема слишком большая, максимальное значение 40 символов.")
    private String name;
    private Boolean existence;
    private Boolean attitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "participant_id")
    private User participant;

//    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ChatMessage> messages;

    public Chat() {

    }

    public Chat(String name, Boolean existence, Boolean attitude) {
        this.name=name;
        this.existence=existence;
        this.attitude=attitude;
    }

    public User getParticipant() {
        return participant;
    }

    public void setParticipant(User participant) {
        this.participant = participant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getExistence() {
        return existence;
    }

    public void setExistence(Boolean existence) {
        this.existence = existence;
    }

    public Boolean getAttitude() {
        return attitude;
    }

    public void setAttitude(Boolean attitude) {
        this.attitude = attitude;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
