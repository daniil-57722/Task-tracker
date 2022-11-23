<#import "parts/commons.ftlh" as c>
<@c.page>
    List of users
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tr>
            <#list users as user>
        <tr>
            <td>${user.getFullName()}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit user info</a></td>
            <td>   <button type="button" class="btn btn-outline-primary">Удалить</button>   </td>
        </tr>
        </#list>

    </table>
</@c.page>