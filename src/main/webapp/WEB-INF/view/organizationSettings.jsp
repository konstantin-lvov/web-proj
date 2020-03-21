<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    function submitForm() {
        document.forms['entityBucket'].submit();
    }
</script>
<h3 align="center">Основные настройки профиля</h3>
<br>
<form:form action="/settings" method="POST" modelAttribute="entityBucket">
    <table>
        <tr>
            <td><h4>Название организации: </h4></td>
            <td><form:input path="organization.organizationName" required="" /></td>
        </tr>
        <tr>
            <td><h4>Пароль: </h4></td>
            <td><form:input path="organization.password" required="" /></td>
        </tr>
        <tr>
            <td><h4>Почта: </h4></td>
            <td><form:input path="organization.email" required="" /></td>
        </tr>
        <tr>
            <td><h4>Отложенная смс: </h4></td>
            <td><form:input path="settings.deferred" required="" /></td>
        </tr>
        <tr>
            <td><h4>Количество смс: </h4></td>
            <td><form:input path="settings.quantity" required="" /></td>
        </tr>
        <tr>
            <td><h4>Интервал: </td>
            <td><form:input path="settings.interval" required="" /></td>
        </tr>
        <tr>
            <td></td>
            <td><form:button onclick="submitForm()" value="Отправить" /></td>
        </tr>
    </table>
</form:form>

