<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Поиск">
    <br>
    <br>
    <br>
    <br>
    <form id="searchForm" action="${pageContext.request.contextPath}/search" method="post">
        <div class="input-group">
            <input type="search" class="form-control rounded" placeholder="Поиск" aria-label="Search" aria-describedby="search-addon" />
            <button type="button" class="btn btn-outline-primary">Найти</button>
        </div>
    </form>
    <c:forEach items="${post}" var="post">
        <div class="post-card">
            <h4 class="post-name"><a href="<c:url value="/posts/detail?id=${post.uuid()}"/>">${post.title()}</a></h4>
            <br>
        </div>
    </c:forEach>
</t:mainLayout>
