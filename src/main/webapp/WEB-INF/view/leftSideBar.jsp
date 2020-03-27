<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<% if (request.getParameter("pageMarker") == null){%>
<div class="leftSideBarElement_picked" onclick="window.location='/accountMainPage';">
<%} else {%>
<div class="leftSideBarElement" onclick="window.location='/accountMainPage';">
<%}%>
    Информация
</div>
<% if(request.getParameter("pageMarker") != null && request.getParameter("pageMarker").equals("orgSettings")){%>
<div class="leftSideBarElement_picked" onclick="window.location='/accountMainPage?pageMarker=orgSettings';">
<%} else {%>
<div class="leftSideBarElement" onclick="window.location='/accountMainPage?pageMarker=orgSettings';">
<%}%>
    Настройки
</div>
<% if(request.getParameter("pageMarker") != null && request.getParameter("pageMarker").equals("smsTemplates")){%>
<div class="leftSideBarElement_picked" onclick="window.location='/accountMainPage?pageMarker=smsTemplates';">
<%} else {%>
<div class="leftSideBarElement" onclick="window.location='/accountMainPage?pageMarker=smsTemplates';">
<%}%>
    Смс шаблоны
</div>
<% if(request.getParameter("pageMarker") != null && request.getParameter("pageMarker").equals("smsSettings")){%>
<div class="leftSideBarElement_picked" onclick="window.location='/accountMainPage?pageMarker=smsSettings';">
<%} else {%>
<div class="leftSideBarElement" onclick="window.location='/accountMainPage?pageMarker=smsSettings';">
<%}%>
    Настройки смс
</div>
<% if(request.getParameter("pageMarker") != null && request.getParameter("pageMarker").equals("contacts")){%>
<div class="leftSideBarElement_picked" onclick="window.location='/accountMainPage?pageMarker=contacts';">
<%} else {%>
<div class="leftSideBarElement" onclick="window.location='/accountMainPage?pageMarker=contacts';">
<%}%>
    Контакты
</div>
<% if(request.getParameter("pageMarker") != null && request.getParameter("pageMarker").equals("callsInfo")){%>
<div class="leftSideBarElement_picked" onclick="window.location='/accountMainPage?pageMarker=callsInfo';">
<%} else {%>
<div class="leftSideBarElement" onclick="window.location='/accountMainPage?pageMarker=callsInfo';">
<%}%>
    Информация о звонках
</div>