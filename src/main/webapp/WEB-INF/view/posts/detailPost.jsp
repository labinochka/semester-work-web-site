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
        <p class="h1">${post.author().username()}</p>
        <img src="${post.image()}" class="rounded img-thumbnail" alt="MISSING GIF"/>
        <br>
        <br>
        <p>
            <font size="5">${post.content()}</font>
        </p>
        <br>
    </div>
</t:mainLayout>

