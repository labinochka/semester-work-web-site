<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="${beer.type()}">
    <br>
    <div class="text-center">
        <br>
        <br>
        <p class="h1">${beer.type()}</p>
        <br>
        <img src="${beer.image()}" class="rounded img-thumbnail">
        <br>
        <br>
        <p>
            <font size="5">${beer.content()}</font>
        </p>
        <br>
        <a href="<c:url value="/types/list"/>">
            <button id="exit" type="button" class="btn btn-outline-secondary mb-4">
                Назад
            </button>
        </a>
    </div>
</t:mainLayout>
