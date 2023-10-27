<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="${post.title()}">
    <br>
    <div class="text-center">
        <br>
        <br>
        <p class="h1">${post.title()}</p>
        <br>
        <p class="h4"><a
                href="<c:url value="/someone?username=${post.author().username()}"/>">${post.author().username()}</a>
        </p>
        <br>
        <p class="h6">Дата публикации: ${post.date()}</p>
        <img src="${post.image()}" class="rounded img-thumbnail"/>
        <br>
        <br>
        <p align="left" style="white-space: pre-wrap;">
            <font size="5">${post.content()}</font>
        </p>
        <br>

        <form id="formCreateComment" action="${pageContext.request.contextPath}/posts/detail" method="post">
            <textarea type="text" id="content" name="content" class="form-control" minlength="1" rows="5"
                      cols="10" required></textarea>
            <br>
            <button id="submit" type="submit" value="create" class="btn btn-secondary mb-4" data-bs-toggle="modal"
                    data-bs-target="#comment">Оставить комментарий
            </button>
        </form>
    </div>

    <div class="modal fade" id="commentDelete" tabindex="-1" aria-labelledby="commentModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Удалить комментарий</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Закрыть"></button>
                </div>
                <div class="modal-body">
                    Вы уверены, что хотите удалить комментарий?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <button type="submit" value="edit" class="btn btn-primary">Удалить</button>
                </div>
            </div>
        </div>
    </div>

    <c:forEach items="${comment}" var="comment">
        <div class="comment-card">
            <h5 class="comment-author"><a
                    href="<c:url value="/someone?username=${comment.getAuthor().username()}"/>">${comment.getAuthor().username()}</a>
            </h5>
            <h6 class="comment-content">${comment.getDate().getDate()}.${comment.getDate().getMonth() + 1}.${comment.getDate().getYear() + 1900}</h6>
            <h4 class="comment-content">${comment.getContent()}</h4>
            <c:if test="${comment.isEdit() == true}">
                <a href="#">
                    <button class="btn btn-primary btn-sm btn-block">Редактировать</button>
                </a>
                <button class="btn btn-outline-secondary btn-sm btn-block" data-bs-toggle="modal"
                   data-bs-target="#commentDelete">Удалить</button>
            </c:if>
            <br>
            <br>
        </div>
    </c:forEach>
</t:mainLayout>

