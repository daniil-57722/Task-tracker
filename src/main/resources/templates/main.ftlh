<#import "parts/commons.ftlh" as c>
<@c.page>
    <button class="btn btn-primary mt-3" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
        Добавить доску
    </button>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/add_board" enctype="multipart/form-data">
                <div class="form-group mt-3">
                    <input type="text" class="form-control" name="text" placeholder="Название" />
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group mt-3">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-4" data-masonry='{"percentPosition": true }'>
        <#list boards as board>
            <div class="col">
                <div class="card my-3" style="width: 18rem;">
                    <div class="m-2">
                        <span>${board.getDeskName()}</span>
                        <hr>
                        <div class="text-center">
                        <a href="/board/${board.idBoard}" class="btn btn-primary mx-auto" role="button"> Перейти </a>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <br><br>
    </div>
</@c.page>