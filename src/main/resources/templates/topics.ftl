<#import "parts/pageP.ftl" as c>
<@c.page>
    <#include "parts/navbar.ftl">
    <div class="my-topics">
        <div class="topics-title">
            Мои темы
        </div>
        <div class="add-icon">
            <button type="button" class="btn-topic" data-toggle="modal" data-target="#exampleModal">
                <g>
                    <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <g clip-path="url(#clip0)">
                            <path d="M9.98711 15.107H9.36211C9.10324 15.107 8.89336 15.1339 8.89336 14.875V9.6875L8.89355 4.375C8.89355 4.11613 9.10344 4.14297 9.3623 4.14297H9.9873C10.2462 4.14297 10.5002 4.11613 10.5002 4.375L10.5 9.6875V14.875C10.5 15.1339 10.246 15.107 9.98711 15.107Z"
                                  fill="#CCCCCC"/>
                            <path fill-rule="evenodd" clip-rule="evenodd"
                                  d="M9.6875 0C4.33762 0 0 4.33918 0 9.6875C0 15.0389 4.33762 19.375 9.6875 19.375C15.0374 19.375 19.375 15.0389 19.375 9.6875C19.375 4.33918 15.0374 0 9.6875 0ZM18.125 9.6875C18.125 5.00293 14.3211 1.25 9.6875 1.25C5.02352 1.25 1.25 5.03113 1.25 9.6875C1.25 14.353 5.02953 18.125 9.6875 18.125C14.3512 18.125 18.125 14.3473 18.125 9.6875Z"
                                  fill="#CCCCCC"/>
                            <path d="M15.4992 9.96875L15.4992 9.34375C15.4992 9.08488 15.526 8.875 15.2672 8.875C13.2413 8.875 12.1055 8.875 10.0797 8.875L4.76718 8.87519C4.50831 8.87519 4.53514 9.08508 4.53514 9.34394L4.53514 9.96894C4.53514 10.2278 4.50831 10.4818 4.76718 10.4818L10.0797 10.4816L15.2672 10.4816C15.526 10.4816 15.4992 10.2276 15.4992 9.96875Z"
                                  fill="#CCCCCC"/>
                        </g>
                        <defs>
                            <clipPath id="clip0">
                                <rect width="19.375" height="19.375" fill="white"/>
                            </clipPath>
                        </defs>
                    </svg>
                </g>
            </button>
        </div>
        <div class="topics-content">
            <#if myChats ??>
                <#list myChats as chat>
                    <#if chat.attitude == false>
                        <div class="topic topic-red">
                            <span>${chat.name}</span>
                            <form action="/mytopics/delete" method="get">
                                <input type="hidden" value="${chat.id}" name="myChatId">
                                <button type="submit" class="topic-delete">
                                    <svg width="9" height="9" viewBox="0 0 9 9" fill="none"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path d="M1.30371 1L8.30371 8M8.30371 1L1.30371 8" stroke="white"/>
                                    </svg>
                                </button>
                            </form>
                        </div>
                    </#if>
                    <#if chat.attitude == true>
                        <div class="topic topic-green">
                            <span>${chat.name}</span>
                            <form action="/mytopics/delete" method="get">
                                <input type="hidden" value="${chat.id}" name="myChatId">
                                <button type="submit" class="topic-delete">
                                    <svg width="9" height="9" viewBox="0 0 9 9" fill="none"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path d="M1.30371 1L8.30371 8M8.30371 1L1.30371 8" stroke="#808080"/>
                                    </svg>
                                </button>
                            </form>
                        </div>
                    </#if>
                </#list>
            </#if>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Добавить тему</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="post">
                            <input type="text" class="form-control"
                                   name="name" placeholder="Тема..."/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <div class="checkbox-group">
                                <div class="form-check">
                                    <label class="form-check-label" for="exampleRadios1">
                                        <input class="form-check-input" type="radio" name="exampleRadios"
                                               id="exampleRadios1"
                                               value="option1" checked>
                                        Нравится
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label" for="exampleRadios2">
                                        <input class="form-check-input" type="radio" name="exampleRadios"
                                               id="exampleRadios2"
                                               value="option2">
                                        Не нравится
                                    </label>
                                </div>
                            </div>
                            <div class="modal-footer pr-0">
                                <button type="submit" class="btn-add">Добавить</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="all-topics">
        <div class="topics-title">
            Темы других пользователей
        </div>
        <div class="quest-icon">
            <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M10 13.2812C9.39594 13.2812 8.90625 13.7709 8.90625 14.375C8.90625 14.9791 9.39594 15.4688 10 15.4688C10.6041 15.4688 11.0938 14.9791 11.0938 14.375C11.0938 13.7709 10.6041 13.2812 10 13.2812ZM10.2996 12.3438H9.67461C9.41574 12.3438 9.20586 12.1339 9.20586 11.875V11.8601C9.20586 9.11234 12.2309 9.375 12.2309 7.66449C12.2309 6.88262 11.5371 6.09375 9.98711 6.09375C8.84867 6.09375 8.25801 6.47066 7.67418 7.21453C7.52152 7.40906 7.24238 7.44871 7.03949 7.30734L6.52645 6.94992C6.30672 6.79684 6.25848 6.49012 6.42313 6.27895C7.25227 5.21539 8.23602 4.53125 9.98711 4.53125C12.0309 4.53125 13.7934 5.6934 13.7934 7.66449C13.7934 10.2979 10.7684 10.1586 10.7684 11.8601V11.875C10.7684 12.1339 10.5585 12.3438 10.2996 12.3438ZM10 1.5625C14.6336 1.5625 18.4375 5.31543 18.4375 10C18.4375 14.6598 14.6637 18.4375 10 18.4375C5.34203 18.4375 1.5625 14.6655 1.5625 10C1.5625 5.34363 5.33602 1.5625 10 1.5625ZM10 0.3125C4.65012 0.3125 0.3125 4.65168 0.3125 10C0.3125 15.3514 4.65012 19.6875 10 19.6875C15.3499 19.6875 19.6875 15.3514 19.6875 10C19.6875 4.65168 15.3499 0.3125 10 0.3125Z"
                      fill="#BFBFBF"/>
            </svg>
            <div class="quest-inform">
                Вы можете присоединиться к существующим чатам.<br>
                <br>
                Если вы поддерживаете данную тему выберете <span class="span-red">Красную кнопку</span>,
                вы будете общаться с человеком, который не поддерживает данную тему. <br>
                Не поддерживаете — <span class="span-green">Зеленую кнопку</span>.
            </div>
        </div>
        <div class="topics-content">
            <#if chats ??>
                <#list chats as chat>
                    <form action="/mytopics/joinchat" method="get">
                        <input type="hidden" value="${chat.id}" name="chatId">
                        <button type="submit" class="my-btn">
                            <#if chat.attitude == false>
                                <div class="topic topic-red">
                                    <span>${chat.name}</span>
                                </div>
                            </#if>
                            <#if chat.attitude == true>
                                <div class="topic topic-green">
                                    <span>${chat.name}</span>
                                </div>
                            </#if>
                        </button>
                    </form>
                </#list>
            </#if>
        </div>

    </div>
    <script>
        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }
    </script>
</@c.page>