<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Виды">
    <br>
    <br>
    <br>
    <p class="h1">Эль</p>
    <h1 class="display-6">Эль варится из ячменного солода с использованием пивных дрожжей верхового брожения.</h1>
    <br>
    <p class="h5">Существуют следующие виды эля:</p>
    <br>
    <div class="beer-info">
        <c:forEach items="${beersAle}" var="beer">
            <div class="beer-card">
                <h4 class="beer-name"><a href="<c:url value="/types/${beer.uuid()}"/>">${beer.type()}</a></h4>
                <br>
            </div>
        </c:forEach>
    </div>
    <br>
    <p class="h1">Лагер</p>
    <h1 class="display-6">Лагер варится из ячменного солода с использованием пивных дрожжей низового брожения.</h1>
    <br>
    <p class="h5">Существуют следующие виды лагера:</p>
    <br>
    <div class="beer-info">
        <c:forEach items="${beersLager}" var="beer">
            <div class="beer-card">
                <h4 class="beer-name"><a href="<c:url value="/types/${beer.uuid()}"/>">${beer.type()}</a></h4>
                <br>
            </div>
        </c:forEach>
    </div>
    <br>
    <p class="h1">Смешанное (спонтанное брожение)</p>
    <h1 class="display-6">Пиво спонтанного брожения — это пиво, которое ферментируется благодаря влиянию окружающей среды, когда природные дрожжи и бактерии в буквальном смысле заражают пиво. Пиво, изготовленное таким образом, получается кислым и нефильтрованным.</h1>
    <br>
    <p class="h5">Существуют следующие виды пива спонтанного брожения:</p>
    <br>
    <div class="beer-info">
        <c:forEach items="${beersMixed}" var="beer">
            <div class="beer-card">
                <h4 class="beer-name"><a href="<c:url value="/types/${beer.uuid()}"/>">${beer.type()}</a></h4>
                <br>
            </div>
        </c:forEach>
    </div>
</t:mainLayout>