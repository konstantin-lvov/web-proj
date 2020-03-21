<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.kl.proj.entity.EntityBucket" %>
<%
    EntityBucket entityBucket = null;
    if(request.getRemoteUser() != null) {
        entityBucket = (EntityBucket) request.getAttribute("entityBucket");
    }
%>
<div class="menuBar">
    <table width="100%">
        <tr>
            <td width="80%"><div class="logoText" onclick="window.location='/';">SUMMARY</div></td>
            <% if ( entityBucket == null ) {%>
            <td><div class="signIn" onclick="window.location='/login';">Войти</div></td>
            <td><div class="signUp" onclick="window.location='/registration';">Зарегистрироваться</div></td>
            <%} else {%>
            <td><div class="signIn" onclick="window.location='/accountMainPage';">Аккаунт</div></td>
            <td><div class="signUp" onclick="window.location='/logout';">Выйти</div></td>
            <%}%>
        </tr>
    </table>
</div>
