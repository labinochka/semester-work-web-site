<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Статьи">
    <br>
    <br>
    <div class="post-info">
        <br>
        <p class="h1">Статьи</p>
        <br>
        <c:forEach items="${post}" var="post">
            <div class="post-card">
                <h4 class="post-name"><a href="<c:url value="/posts/detail?id=${post.uuid()}"/>">${post.title()}</a></h4>
                <br>
            </div>
        </c:forEach>
    </div>
</t:mainLayout>