<#import "parts/pageP.ftl" as c>
<@c.page>
    <div class="bgPage">
        <#include "parts/navbar.ftl">
        ${message?ifExists}
        <div class="registrationPage">
            <div class="registrationTitle">Регистрация</div>
            <div class="registrationForm">
                <form action="/registration" method="post">
                    <div class="form-group mb-2">
                        <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                               class="form-control ${(usernameError??)?string('is-invalid', '')}"
                               placeholder="Введите логин"/>
                        <#if usernameError??>
                            <div class="invalid-feedback">
                                ${usernameError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mb-2">
                        <input type="password" name="password"
                               class="form-control ${(passwordError??)?string('is-invalid', '')}"
                               placeholder="Введите пароль"/>
                        <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mb-2">
                            <input type="password" name="password2"
                                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                                   placeholder="Повторите пароль"/>
                            <#if passwordError??>
                                <div class="invalid-feedback">
                                    ${passwordError}
                                </div>
                            </#if>
                    </div>
                    <div class="form-group mb-3">
                            <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                                   class="form-control ${(emailError??)?string('is-invalid', '')}"
                                   placeholder="Введите вашу почту"/>
                            <#if emailError??>
                                <div class="invalid-feedback">
                                    ${emailError}
                                </div>
                            </#if>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn-registr" type="submit">Зарегистрироваться</button>
                    <div class="formRegistrText">Уже есть аккаунт? <a href="/login">Войдите</a></div>
                </form>
            </div>
        </div>
    </div>
</@c.page>