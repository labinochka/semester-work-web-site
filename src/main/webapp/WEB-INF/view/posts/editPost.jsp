<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Редактировать статью">
    <br>
    <br>
    <br>
    <br>
    <div class="tab-content">
        <div class="tab-pane fade show active" id="editPost" role="tabpanel" aria-labelledby="tab-login">
            <form id="formCreatePost" action="${pageContext.request.contextPath}/posts/edit"
                  enctype="multipart/form-data"
                  method="post">
                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <textarea type="text" id="title" name="title" class="form-control" minlength="6" cols="100"
                                  required>${post.title()}</textarea>
                        <label class="form-label" for="title">Название</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <textarea type="text" id="content" name="content" class="form-control" minlength="2" rows="10"
                                  cols="100" required>${post.content()}</textarea>
                        <label class="form-label" for="content">Текст</label>
                    </div>
                </div>

                <div class="d-flex justify-content-center">
                    <input id="image" name="image" type="file" accept=".jpg, .png, .jpeg"
                           class="btn btn-secondary mb-4">Изменить изображение</input>
                </div>
                <div class="d-flex justify-content-center">
                    <button id="submit" type="submit" class="btn btn-primary mb-4">Сохранить изменения</button>
                </div>
            </form>
            <div class="d-flex justify-content-center">
                <a href="<c:url value="/profile"/>">
                    <button id="exit" type="button" class="btn btn-outline-secondary mb-4">
                        Назад
                    </button>
                </a>
            </div>
            <br>
            <br>
        </div>
    </div>
</t:mainLayout>