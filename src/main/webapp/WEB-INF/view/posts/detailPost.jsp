<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Детали">
    <br>
    <div class="text-center">
        <br>
        <br>
        <p class="h1">${post.title()}</p>
        <br>
        <p class="h4">Автор: ${post.author().username()}</p>
        <br>
        <p class="h6">Дата публикации: ${post.date().getDate()}.${post.date().getMonth()}.${post.date().getYear()}</p>
        <img src="${post.image()}" class="rounded img-thumbnail"/>
        <br>
        <br>
        <p>
            <font size="5">${post.content()}</font>
        </p>
        <br>
    </div>
</t:mainLayout>

