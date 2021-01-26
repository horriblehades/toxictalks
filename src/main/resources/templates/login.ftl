<#import "parts/pageP.ftl" as c>
<@c.page>
    <div class="bgPage">
        <#include "parts/navbar.ftl">
        <div class="loginPage">
            <div class="loginTitle">Вход</div>
            <div class="loginForm">
                <form action="/login" method="post">
                    <div class="form-group mb-2 ${(error??)?string('is-invalid', '')}">
                        <input type="text" name="username" class="form-control"
                               placeholder="Логин">
                    </div>
                    <div class="form-group mb-4 ${(error??)?string('is-invalid', '')}">
                        <input type="password" name="password" class="form-control"
                               placeholder="Пароль">
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div>
                        <button type="submit" class="btn-login">Войти</button>
                    </div>
                    <div class="formLoginText">Нет аккаунта? <a href="/registration">Зарегестрируйтесь</a></div>
                    <#if messageActivation??>
                        <div class="formMessage">${messageActivation}</div>
                    </#if>
                    <#if error??>
                        <div class="formMessage">${error}</div>
                    </#if>
                </form>
            </div>
        </div>
    </div>
</@c.page>