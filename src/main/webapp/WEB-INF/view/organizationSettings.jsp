<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script>
    function submitForm() {
        document.forms['organization','settings'].submit();
    }
</script>
<h2 align="center">Основные настройки профиля</h2>
<br>
<div class="rightSideContentSpace">
    <form:form action="/settings" method="POST" modelAttribute="list">
        <table valign="center">
            <tr>
                <td><h4>Идентификационный номер: </h4></td>
                <td><form:input path="list.organization.oid" readonly="true" class="customDisabledField"/></td>
            </tr>
            <tr>
                <td><h4>Аккаунт-роль:   </h4></td>
                <td><form:input path="list.organization.authority" readonly="true" class="customDisabledField"/></td>
            </tr>
            <tr>
                <td><h4>Включен: </h4></td>
                <td>
                    <c:if test="${list.organization.enabled == true}">
                        <h4>Да</h4>
                    </c:if>
                    <c:if test="${list.organization.enabled == false}">
                        <h4>Нет</h4>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td><h4>Название организации: </h4></td>
                <td><form:input path="list.organization.organizationName" required="" /></td>
            </tr>
            <tr>
                <td><h4>Пароль: </h4></td>
                <td><form:input path="list.organization.password" required="" /></td>
            </tr>
            <tr>
                <td><h4>Почта: </h4></td>
                <td><form:input path="list.organization.email" required="" /></td>
            </tr>
            <tr>
                <td><h4>Отложенная смс: </h4></td>
                <td><form:input path="list.settings.deferred" required="" /></td>
            </tr>
            <tr>
                <td><h4>Количество смс: </h4></td>
                <td><form:input path="list.settings.quantity" required="" /></td>
            </tr>
            <tr>
                <td><h4>Интервал: </h4></td>
                <td><form:input path="list.settings.interval" required="" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="button" class="button" value="Сохранить" onclick="submitForm()"/></td>
            </tr>
        </table>
    </form:form>
    <h5><font color="red">ВНИМАНИЕ!</font> При изменении названия организации Вам потребуется произвести повторную авторизацию.</h5>
</div>