<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Профиль">
    <br>
    <br>
    <div class="row py-5 px-4">
        <div class="col-md-5 mx-auto">
            <div class="bg-white shadow rounded overflow-hidden">
                <br>
                <div class="px-4 pt-0 pb-4 cover">
                    <div class="media align-items-end profile-head">
                        <div class="profile mr-3"><img src="${account.avatar()}" width="130"
                                                       class="rounded mb-2 img-thumbnail">
                        <div class="media-body mb-5">
                            <h4 class="mt-0 mb-0">${account.username()}</h4>
                            <p class="small mb-4">${account.name()}</p>
                            <p class="small mb-4">${account.lastname()}</p>
                            <p class="small mb-4">${account.email()}</p>
                        </div>
                            <a href="#" class="btn btn-primary btn-sm btn-block">Редактировать</a>
                            <a href="<c:url value="/sign-out"/>" class="btn btn-outline-secondary btn-sm btn-block">Выйти</a>
                        </div>
                    </div>
                </div>
                <div class="px-4 py-3"><h5 class="mb-0">Обо мне</h5>
                    <div class="p-4 rounded shadow-sm bg-light"><p>${account.about()}</p>
                    </div>
                </div>
            </div>
        </div>
</t:mainLayout>