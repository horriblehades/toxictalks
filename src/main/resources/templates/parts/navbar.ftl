<#include "security.ftl">
<div>
    <ul class="nav nav-pills justify-content-end">
        <#if !isUser>
            <li class="nav-item">
                <a class="nav-link nav-link-font" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-font" href="/rules">Правила</a>
            </li>
        </#if>
        <#if isUser>
            <li class="nav-item">
                <a class="nav-link nav-link-font" href="/chats">Мои чаты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-font" href="/mytopics">Мои темы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-font" href="/user/profile">Настройки</a>
            </li>
        </#if>
        <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link nav-link-font" href="/reports">Жалобы</a>
            </li>
        </#if>
        <#if !isUser>
            <li class="nav-item">
                <a class="nav-link nav-link-font" href="/registration">Регистрация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-font" href="/login">Войти</a>
            </li>
        </#if>
        <#if isUser>
            <form class="logoutForm" action="/logout" method="post">
                <input class="logout nav-link-font" type="submit" value="Выход"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </#if>
    </ul>
</div>