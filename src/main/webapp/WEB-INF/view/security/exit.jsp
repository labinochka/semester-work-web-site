<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Выйти">
    <br>
    <div class="text-center">
        <br>
        <br>
        <br>
        <br>
        <h1 class="display-6">Хотите выйти?</h1>
        <a href="<c:url value="/sign-out"/>" class="btn btn-outline-secondary btn-sm btn-block">Выйти</a>
</t:mainLayout>