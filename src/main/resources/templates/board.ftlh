<#import "parts/commons.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <button class="btn btn-primary mt-3" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample"
            aria-expanded="false" aria-controls="collapseExample">
        Добавить карточку
    </button>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/card/add" enctype="multipart/form-data">
                <div class="form-group mt-3">
                    <input type="text" class="form-control" name="text" placeholder="Введите название"/>
                </div>
                <div class="form-group mt-3">
                    <input class="form-control" type="file" name="file" id="customFile">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="board" value="${board}">
                <div class="form-group mt-3">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-4" data-masonry='{"percentPosition": true }'>
        <#list cards as cards>
            <div class="col">
                <div class="card my-3" style="width: 18rem;">
                    <div>
                        <#if cards.filename??>
                            <img src="/img/${cards.filename}" class="card-img-top">
                        </#if>
                    </div>
                    <div class="m-2">
                        <span>${cards.cardName}</span>
                    </div>
                    <form method="get" action="/board/${board}/card/${cards.idCard}">
                        <hr>
                        <div class="text-center m-2">
                            <button type="submit" class="btn btn-primary">
                                Просмотр
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
    <#if cardName??>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script type="text/javascript">
            $(window).on('load', function () {
                $('#myModal').modal('show');
            });
        </script>
        <div class="modal hide fade modal-lg" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">${cardName}</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <#list cardBody as tasks>
                                <div class="row">
                                    <div class="col-sm-8 bg-light p-3 border">
                                        <label><input type="checkbox" disabled class="form-check-label"
                                                      name="status" ${tasks.status?string("checked", "")}></label> ${tasks.taskName}
                                    </div>
                                    <div class="col">
                                        <form method="get" action="/task/${tasks.idTask}/changeStatus">
                                            <#if tasks.status=false>
                                                <button type="submit" class="btn btn-outline-success btn-sm">✔</button>
                                            <#else>
                                                <button type="submit" class="btn btn-outline-danger btn-sm">❌</button>
                                            </#if>
                                        </form>
                                    </div>
                                    <div class="col">
                                        <form method="get" action="/task/${tasks.idTask}/delete">
                                            <button type="submit" class="btn btn-outline-light btn-sm">🗑</button>
                                        </form>
                                    </div>
                                    <#if !tasks.issuer??>
                                    <div class="col">
                                        <form method="post" action="/task/user">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                            <input type="hidden" name="task" value="${tasks.idTask}">
                                            <button type="submit" class="btn btn-outline-info btn-sm">✋</button>
                                        </form>
                                    </div>
                                    <#else>
                                        <div class="col">
                                            <form method="post" action="/task/user">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                                <input type="hidden" name="task" value="${tasks.idTask}">
                                                <button type="submit"
                                                        class="btn btn-outline-info btn-sm">${tasks.issuer.firstname}</button>
                                            </form>
                                        </div>
                                        </#if>
                                        <hr>
                                </div>
                            </#list>
                        </div>
                        <button class="btn btn-primary mt-3" type="button" data-bs-toggle="collapse"
                                data-bs-target="#addTask" aria-expanded="false" aria-controls="collapseExample">
                            Новая задача
                        </button>
                        <div class="collapse" id="addTask">
                            <div class="form-group mt-3">
                                <form method="post" action="/task/add">
                                    <div class="form-group mt-3">
                                        <input type="text" class="form-control" name="text"
                                               placeholder="Введите задачу"/>
                                    </div>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <input type="hidden" name="board" value="${board}">
                                    <input type="hidden" name="card" value="${cardId}">
                                    <div class="form-group mt-3">
                                        <button type="submit" class="btn btn-primary">Добавить</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Сохранить</button>
                    </div>
                </div>
            </div>
        </div>
    </#if>
    <div class="sticky-bottom d-flex justify-content-end">
        <button class="btn btn-primary mb-5" type="button" data-bs-toggle="collapse" data-bs-target="#chatExample"
                aria-expanded="false" aria-controls="collapseExample">✉
        </button>
    </div>
    <div class="sticky-bottom d-flex justify-content-end">

        <div class="collapse me-3" id="chatExample">
            <div class="page-content page-container" id="page-content">
                <div class="row container">
                    <div class="col">
                        <div class="box box-warning direct-chat direct-chat-warning">
                            <div class="box-header with-border">
                                <h3 class="box-title">Сообщения</h3>
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title=""
                                            data-widget="chat-pane-toggle" data-original-title="Contacts">
                                        <i class="fa fa-comments"></i></button>
                                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                                class="fa fa-times"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="direct-chat-messages">
                                <#list messages as message>
                                    <#if message.author.id==userId>
                                        <div class="direct-chat-msg">
                                            <div class="direct-chat-info clearfix justify-content-end">
                                                <span class="direct-chat-name-sent">${message.author.firstname}</span>
                                            </div>
                                            <div class="direct-chat-text">
                                                ${message.text}
                                            </div>
                                        </div>
                                    <#else>
                                        <div class="direct-chat-msg right">
                                            <div class="direct-chat-info clearfix float-left">
                                                <span class="direct-chat-name ">${message.author.username}</span>
                                            </div>
                                            <div class="direct-chat-text">
                                                ${message.text}
                                            </div>
                                        </div>
                                    </#if>
                                </#list>
                                    <div class="sticky-bottom">

                                    <div class="box-footer">
                                    <form method="post" action="/message/add">
                                        <div class="input-group">
                                            <input type="text" name="text" placeholder="Type Message ..."
                                                   class="form-control">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                        <input type="hidden" name="board" value="${board}">
                                            <span class="input-group-btn">
                            <button type="submit" class="btn btn-primary btn-flat">➥</button>
                          </span>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</@c.page>