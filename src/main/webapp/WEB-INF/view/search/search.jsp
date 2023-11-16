<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Поиск">
    <br>
    <br>
    <br>
    <br>
    <form id="searchForm" action="${pageContext.request.contextPath}/search/add" method="post">
        <div class="input-group">
            <input type="search" id="title" name="title" class="form-control rounded" placeholder="Поиск статей"
                   aria-label="Search"
                   aria-describedby="search-addon" required/>
            <button type="submit" id="search" class="btn btn-outline-primary">Найти</button>
        </div>
    </form>
    <br>
    <div id="post-card" class="post-card">
    </div>
    <script>
        $(document).ready(function() {
            let form = $("#searchForm")

            form.submit(() => {
                var title = $("#title").val();

                $.ajax
                ({
                    type: "POST",
                    data: {title: title},
                    url: "/BeerOK/search/add",
                    success: function (result) {
                        $('#post-card').html(result);
                    },
                    error: function () {
                        alert("error");
                    }
                });
                return false
            });
        });
    </script>
</t:mainLayout>
