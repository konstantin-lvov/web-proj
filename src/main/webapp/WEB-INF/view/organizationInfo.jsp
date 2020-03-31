<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">Общая информация</h2>
</br>
<div class="rightSideContentSpace">
    <table>
        <tr>
            <td>
                <p>Идентификационный номер: ${organization.oid}</p>
                <p>Название организации: ${organization.organizationName}</p>
                <p>Почта: ${organization.email}</p>
                <p>Включен:
                    <c:if test="${organization.enabled == true}">
                        <input type="checkbox" size="10px" disabled checked>
                    </c:if>
                    <c:if test="${organization.enabled == false}">
                        <input type="checkbox" size="10px" disabled>
                    </c:if></p>
                </p>
                <p>Аккаунт-роль: ${organization.authority}</p>
            </td>
        </tr>
    </table>
</div>