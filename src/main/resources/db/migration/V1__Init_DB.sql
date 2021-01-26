create sequence hibernate_sequence start 1 increment 1;

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

create table chat (
    id int8 not null,
    name varchar(255),
    existence boolean not null,
    attitude boolean not null,
    creator_id int8,
    participant_id int8,
    primary key (id)
);

create table chat_message (
    id int8 not null,
    attitude boolean not null,
    text varchar(2048) not null,
    user_id int8,
    chat_id int8,
    primary key (id)
);

create table report (
    id int8 not null,
    name varchar(2048) not null,
    message_id int8,
    primary key (id)
);

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;

alter table if exists chat
    add constraint chat_user_fk
    foreign key (creator_id) references usr;

alter table if exists chat
    add constraint chat_user_fk
    foreign key (participant_id) references usr;

alter table if exists chat_message
    add constraint chat_message_user_fk
    foreign key (user_id) references usr;

alter table if exists chat_message
    add constraint chat_message_chat_fk
    foreign key (chat_id) references chat;

alter table if exists report
    add constraint report_chat_message_fk
    foreign key (message_id) references chat_message;