<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Детали">
    <br>
    <div class="text-center">
        <br>
        <br>
        <p class="h1">${beer.type()}</p>
        <br>
        <img src="${beer.image()}" class="rounded img-thumbnail">
        <br>
        <br>
        <p">
            <font size="5">${beer.content()}</font>
        </p>
        <br>
    </div>
</t:mainLayout>
