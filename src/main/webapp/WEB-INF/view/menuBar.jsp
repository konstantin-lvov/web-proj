<%@ page import="ru.kl.proj.entity.Organization" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Organization organization = null;
    if(request.getRemoteUser() != null) {
        organization = (Organization) request.getAttribute("organization");
    }
%>
<div class="menuBar">
    <div class="logoText" onclick="window.location='/';">SUMMARY</div>
    <div class="freeSpaceMenuBar"></div>
    <% if ( organization == null ) {%>
    <div class="rightSideMenuBarCommonSpace">
        <div class="signIn" onclick="window.location='/login';">Войти</div>
        <div class="signUp" onclick="window.location='/registration';">Создать аккаунт</div>
    </div>
    <%} else {%>
    <div class="rightSideMenuBarCommonSpace">
        <div class="signIn" onclick="window.location='/accountMainPage';">Личный кабинет</div>
        <div class="signUp" onclick="window.location='/logout';">Выйти</div>
    </div>
    <%}%>
</div>
