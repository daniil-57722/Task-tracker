<#macro login path isRegisterForm>

    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Логин: </label>
            <div class="col-sm-6">
                <input type="text" name="username" placeholder="Логин" class="form-control"/>
            </div>
        </div>
        <div class="form-group row mt-1">
            <label class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-6">
                <input type="password" name="password" placeholder="Пароль" class="form-control"/>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row mt-1">
                <label class="col-sm-2 col-form-label"> Email </label>
                <div class="col-sm-6">
                    <input type="email" name="email" placeholder="Email" class="form-control"/>
                </div>
            </div>
            <div class="form-group row mt-1">
                <label class="col-sm-2 col-form-label"> Фамилия </label>
                <div class="col-sm-6">
                    <input type="text" name="middlename" placeholder="Фамилия" class="form-control"/>
                </div>
            </div>
            <div class="form-group row mt-1">
                <label class="col-sm-2 col-form-label"> Имя </label>
                <div class="col-sm-6">
                    <input type="text" name="firstname" placeholder="Имя" class="form-control"/>
                </div>
            </div>
            <div class="form-group row mt-1">
                <label class="col-sm-2 col-form-label"> Отчество </label>
                <div class="col-sm-6">
                    <input type="text" name="lastname" placeholder="Отчество" class="form-control"/>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <br>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button>
        <br>
        <#if !isRegisterForm><a href="/registration">Зарегистрироваться</a></#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>