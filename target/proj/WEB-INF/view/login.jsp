<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login</title>
    <link href='<spring:url value="/resource/styles.css"/>' rel="stylesheet" />
</head>
<body onload='document.organization.organizationName.focus();'>
<div class="commonSpace" align="center">
    <h1>Авторизация</h1>

    <c:if test="${not empty errorMessge}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>

    <form name='organization' action="/login" method='POST'>
        <table>
            <tr>
                <td><h3>Название организации:</h3></td>
                <td><input type='text' name='organizationName' value=''></td>
            </tr>
            <tr>
                <td><h3>Пароль:</h3></td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" class="button" type="submit" value="Войти" /></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
</body>
</html>
