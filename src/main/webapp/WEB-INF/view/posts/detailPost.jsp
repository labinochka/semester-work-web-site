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
        <button id="submit" type="submit" class="btn btn-secondary mb-4" data-bs-toggle="modal"
                data-bs-target="#comment">Оставить комментарий
        </button>

        <div class="modal fade" id="comment" tabindex="-1" aria-labelledby="commentModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Оставить комментарий</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                    </div>
                    <div class="modal-body">
                        <textarea type="text" id="content" name="content" class="form-control" minlength="1" rows="5"
                                  cols="30" required></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <button type="button" class="btn btn-primary">Отправить</button>
                    </div>
                </div>
            </div>
        </div>
        <c:forEach items="${comment}" var="comment">
            <div class="comment-card">
                <h4 class="comment-author">${comment.author().username()}</h4>
                <h4 class="comment-content">${comment.date().getDate()}.${comment.date().getMonth() + 1}.${comment.date().getYear() + 1900}</h4>
                <h4 class="comment-content">${comment.content()}</h4>
                <br>
            </div>
        </c:forEach>
    </div>
</t:mainLayout>

