<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">Общая информация</h2>
</br>
<div class="rightSideContentSpace">
    <p>Идентификационный номер: ${entityBucket.organization.oid}</p>
    <p>Название организации: ${entityBucket.organization.organizationName}</p>
    <p>Почта: ${entityBucket.organization.email}</p>
    <p>Включен:
        <c:if test="${entityBucket.organization.enabled == true}">
            <input type="checkbox" size="10px" disabled checked>
        </c:if>
        <c:if test="${entityBucket.organization.enabled == false}">
            <input type="checkbox" size="10px" disabled>
        </c:if></p>
    </p>
    <p>Аккаунт-роль: ${entityBucket.organization.authority}</p>
</div>