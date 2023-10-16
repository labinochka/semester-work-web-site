<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Регистрация">
    <br>
    <br>
    <br>
    <br>
    <div class="tab-content">
        <div class="tab-pane fade show active" id="signIn" role="tabpanel" aria-labelledby="tab-login">
            <form id="formRegistration" action="${pageContext.request.contextPath}/registration" method="post">

                <div class="col-md-12 d-flex justify-content-center">
                    <p style="color:red">${error}</p>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <input type="text" id="login" name="login" class="form-control" required/>
                        <label class="form-label" for="login">Логин</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="text" id="name" name="name" class="form-control" required/>
                        <label class="form-label" for="name">Имя</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="text" id="lastname" name="lastname" class="form-control" required/>
                        <label class="form-label" for="lastname">Фамилия</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="text" id="birthday" name="birthday" class="form-control" required/>
                        <label class="form-label" for="birthday">Дата рождения DD.MM.YYYY</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="text" id="email" name="email" class="form-control" required/>
                        <label class="form-label" for="email">Электронная почта</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="password" id="password" name="password" class="form-control" required/>
                        <label class="form-label" for="password">Пароль</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="password" id="repeatPassword" name="repeatPassword" class="form-control" required/>
                        <label class="form-label" for="repeatPassword">Пароль еще раз</label>
                    </div>
                </div>


                <div class="d-flex justify-content-center">
                    <button id="signInBtn" type="submit" class="btn btn-primary mb-4">Зарегистрироваться</button>
                </div>

            </form>
            <div class="d-flex justify-content-center">
                <a href="<c:url value="/sign-in"/>">
                <button id="exit" type="button" class="btn btn-outline-secondary mb-4">
                    Выйти
                </button>
                </a>
            </div>
            <br>
            <br>
        </div>
    </div>
</t:mainLayout>