<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.kl.proj.entity.EntityBucket" %>
<%
    EntityBucket entityBucket = null;
    if(request.getRemoteUser() != null) {
        entityBucket = (EntityBucket) request.getAttribute("entityBucket");
    }
%>
<div class="menuBar">
    <div class="logoText" onclick="window.location='/';">SUMMARY</div>
    <div class="freeSpaceMenuBar"></div>
    <% if ( entityBucket == null ) {%>
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
