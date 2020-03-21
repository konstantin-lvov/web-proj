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
            <td>Название организации: </td>
            <td><form:input path="organization.organizationName" required="" /></td>
        </tr>
        <tr>
            <td>Пароль: </td>
            <td><form:input path="organization.password" required="" /></td>
        </tr>
        <tr>
            <td>Почта: </td>
            <td><form:input path="organization.email" required="" /></td>
        </tr>
        <tr>
            <td>Отложенная смс: </td>
            <td><form:input path="settings.deferred" required="" /></td>
        </tr>
        <tr>
            <td>Количество смс: </td>
            <td><form:input path="settings.quantity" required="" /></td>
        </tr>
        <tr>
            <td>Интервал: </td>
            <td><form:input path="settings.interval" required="" /></td>
        </tr>
        <tr>
            <td></td>
            <td><form:button onclick="submitForm()" value="Отправить" /></td>
        </tr>
    </table>
</form:form>

