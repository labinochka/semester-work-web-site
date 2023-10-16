<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Детали">
    <br>
    <div class="text-center">
        <br>
        <br>
        <p class="h1">${account.username()}</p>
        <br>
        <img src="${account.avatar()}" class="rounded img-thumbnail">
    </div>
</t:mainLayout>