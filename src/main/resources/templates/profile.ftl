<#import "parts/pageP.ftl" as c>
<@c.page>
    <#include "parts/navbar.ftl">
    <div class="profilePage">
    <div class="profileTitle">${username}</div>
    <div class="profileForm">
        <form method="post">
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
            <div class="form-group mb-4">
                <input type="email" name="email" class="form-control"
                       placeholder="Электронная почта" value="${email!''}">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div>
                <button type="submit" class="btn-profile">Сохранить</button>
            </div>
        </form>
    </div>
</@c.page>