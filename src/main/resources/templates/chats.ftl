<#import "parts/pageP.ftl" as c>
<@c.page>
    <#include "parts/navbar.ftl">
    <div class="chats">
        <div class="chats-head">
            <div class="chats-title">
                <div class="chats-title-text">Мои чаты</div>
            </div>
            <div class="dialogue-title">
                <#if titleTopic ??>
                    <div class="topic">${titleTopic}</div>

                    <form method="get" action="/chats/delete">
                        <#if currentChatId ??>
                            <input type="hidden" name="currentChatId" value=${currentChatId}>
                        </#if>
                        <button type="submit" class="chat-delete">
                            <svg version="1.0" xmlns="http://www.w3.org/2000/svg"
                                 width="25px" height="25px" viewBox="0 0 1280.000000 1280.000000"
                                 preserveAspectRatio="xMidYMid meet">
                                <metadata>
                                    Created by potrace 1.15, written by Peter Selinger 2001-2017
                                </metadata>
                                <g transform="translate(0.000000,1280.000000) scale(0.100000,-0.100000)"
                                   fill="#808080" stroke="none">
                                    <path d="M1342 11457 c-518 -518 -942 -947 -942 -952 0 -6 921 -931 2048
                                    -2058 l2047 -2047 -2047 -2047 c-1127 -1127 -2048 -2052 -2048 -2058 0 -13
                                    1882 -1895 1895 -1895 6 0 931 921 2058 2048 l2047 2047 2047 -2047 c1127
                                    -1127 2052 -2048 2058 -2048 13 0 1895 1882 1895 1895 0 6 -921 931 -2048
                                    2058 l-2047 2047 2047 2047 c1127 1127 2048 2052 2048 2058 0 13 -1882 1895
                                    -1895 1895 -6 0 -931 -921 -2058 -2048 l-2047 -2047 -2047 2047 c-1127 1127
                                    -2052 2048 -2058 2048 -6 0 -434 -424 -953 -943z"/>
                                </g>
                            </svg>
                        </button>
                    </form>
                </#if>
            </div>
        </div>
        <div class="chats-content">
            <div class="chats-content-title">
                <#if myChats ??>
                    <#list myChats as chat>
                        <form action="/chats/selectchat" method="get">
                            <input type="hidden" value="${chat.id}" name="chatId">
                            <div class="chats-content-item">
                                <button type="submit" class="btn-select-chat">
                                    <div class="chats-content-avatar">
                                        ${chat.name}
                                    </div>
                                    <div class="chats-content-title-text">
                                        ${chat.name}
                                    </div>
                                </button>
                            </div>
                        </form>
                    </#list>
                </#if>
            </div>
            <div class="chats-content-dialogue">
                <div class="chat-messages">
                    <#if chatMessages ??>
                        <#list chatMessages as message>
                            <form>
                                <#if message.attitude>
                                    <button type="button" class="chat-message-green" data-toggle="modal"
                                            data-target="#exampleModal" data-whatever=${message.id}>
                                        ${message.text}
                                    </button>
                                </#if>
                                <#if !message.attitude>
                                    <button type="button" class="chat-message-red" data-toggle="modal"
                                            data-target="#exampleModal" data-whatever=${message.id}>
                                        ${message.text}
                                    </button>
                                </#if>
                            </form>
                        </#list>
                    </#if>
                </div>

                <#if currentChatId ??>
                    <div class="chat-form-message">

                        <form action="/chats/send" method="post">
                            <#if currentChatId ??>
                                <input type="hidden" name="currentChatId" value=${currentChatId}>
                            </#if>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="text" class="chat-input"
                                   name="text" placeholder="Написать сообщение..." autocomplete="off">
                            <button type="submit" class="btn-send-message">
                                <div class="send-icon">
                                    <svg viewBox="0 0 512 512" xmlns="http://www.w3.org/2000/svg"><title/>
                                        <g data-name="1" id="_1">
                                            <path d="M291.48,449.94A15,15,0,0,1,278,441.5L207.5,296.57,62.57,226.08a15,15,0,0,1,1-27.41L435.48,49.08A15,15,0,0,1,455,68.6L305.4,440.54A15,15,0,0,1,292,449.93Zm-185.38-236,119.18,58a15,15,0,0,1,6.93,6.93l58,119.18L414,90Z"/>
                                            <path d="M218.72,300.35a15,15,0,0,1-10.6-25.61L430.47,52.39a15,15,0,1,1,21.21,21.22L229.33,296A15,15,0,0,1,218.72,300.35Z"/>
                                        </g>
                                    </svg>
                                </div>
                            </button>
                        </form>
                    </div>
                </#if>
            </div>
        </div>


        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Жалоба на пользователя</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Пожалуйста, сообщите нам причину, по которой пользователь должн быть заблокирован:
                        <form action="/chats/report" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="hidden" class="message-id" id="recipient-name" name="messageId">
                            <#if currentChatId??>
                                <input type="hidden" name="currentChatId" value=${currentChatId}>
                            </#if>
                            <div class="checkbox-group">
                                <div class="form-check">
                                    <label class="form-check-label" for="exampleRadios1">
                                        <input class="form-check-input" type="radio" name="exampleRadios"
                                               id="exampleRadios1"
                                               value="option1" checked>
                                        Порнография
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label" for="exampleRadios2">
                                        <input class="form-check-input" type="radio" name="exampleRadios"
                                               id="exampleRadios2"
                                               value="option2">
                                        Рассылка спама
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label" for="exampleRadios1">
                                        <input class="form-check-input" type="radio" name="exampleRadios"
                                               id="exampleRadios1"
                                               value="option3" checked>
                                        Реклама
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label" for="exampleRadios1">
                                        <input class="form-check-input" type="radio" name="exampleRadios"
                                               id="exampleRadios1"
                                               value="option4" checked>
                                        Другая причина
                                    </label>
                                </div>
                            </div>
                            <div class="modal-footer pr-0">
                                <button type="submit" class="btn-add">Пожаловаться</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <#--Scripts-->

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
                integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
                crossorigin="anonymous"></script>
        <script>
            $('#exampleModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget)
                var recipient = button.data('whatever')
                var modal = $(this)
                modal.find('.modal-body form .message-id').val(recipient)
            })
        </script>
    </div>
    <script>
        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }
    </script>
</@c.page>