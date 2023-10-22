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
        <p class="h4">Автор: ${post.author().username()}</p>
        <br>
        <p class="h6">Дата публикации: ${post.date()}</p>
        <img src="${post.image()}" class="rounded img-thumbnail"/>
        <br>
        <br>
        <p>
            <font size="5">${post.content()}</font>
        </p>
        <br>
        <button id="submit" type="submit" class="btn btn-secondary mb-4">Оставить комментарий</button>
        <c:forEach items="${comment}" var="comment">
            <div class="comment-card">
                <h4 class="comment-author">${comment.author().username()}</h4>
                <h4 class="comment-content">${comment.date().getDate()}.${comment.date().getMonth() + 1}.${comment.date().getYear() + 1900}</h4>
                <h4 class="comment-content">${comment.content()}</h4>
                <br>
            </div>
        </c:forEach>
        <a href="<c:url value="/posts/list"/>">
            <button id="exit" type="button" class="btn btn-outline-secondary mb-4">
                Назад
            </button>
        </a>
    </div>
</t:mainLayout>

