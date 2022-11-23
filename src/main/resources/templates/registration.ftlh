<#import "parts/commons.ftlh" as c>
<#import "parts/logIn.ftlh" as l>
<@c.page>
    <div class="mb-1">
        <h5>Регистрация</h5>
    </div>
    <@l.login "/registration" true/>
    ${message!}
    <a href="/login">Назад</a>
</@c.page>