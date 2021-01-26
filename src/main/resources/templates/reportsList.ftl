<#import "parts/pageP.ftl" as c>
<@c.page>
    <#include "parts/navbar.ftl">
    <div class="container">
        <div class="card-columns mt-4">
            <#list reports as report>
                <div class="card my-3">
                    <form method="get" action="/reports/delete">
                        <input type="hidden" name="reportId" value=${report.id}>
                        <div class="m-2">
                            <span>${report.name}</span>

                            <button type="submit" class="close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </form>
                    <div class="card-footer">
                        <b> Сообщение:</b> <br>
                        ${report.message.text}
                    </div>
                    <form method="post" action="/reports/block">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="reportId" value=${report.id}>
                        <button type="submit" class="btn-block">Заблокировать пользователя</button>
                    </form>
                </div>
            <#else>
                <div class="non-report">
                    Нет жалоб
                </div>
            </#list>
        </div>
    </div>
</@c.page>