<%@ page import="java.util.HashMap" %>
<%@ page import="ru.kl.proj.entity.Entity" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script>
    function submitForm() {
        document.forms['organization','settings'].submit();
    }
</script>
<h2 align="center">Основные настройки профиля</h2>
<div class="rightSideContentSpace">
    <form name="orgSettings" action="/settings" method="post">
        <table>
            <tr>
                <td>
                    <input type="text" name="oid" value="${organization.oid}" hidden/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="enabled" value="${organization.enabled}" hidden/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="authority" value="${organization.authority}" hidden/>
                </td>
            </tr>
            <tr>
                <td>
                    Название:
                </td>
                <td>
                    <input type="text" name="organizationName" value="${organization.organizationName}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Емейл:
                </td>
                <td>
                    <input type="text" name="email" value="${organization.email}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Пароль:
                </td>
                <td>
                    <input type="text" name="password" value="${organization.password}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Отложенная смс:
                </td>
                <td>
                    <input type="text" name="deferred" value="${settings.deferred}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Количество смс:
                </td>
                <td>
                    <input type="text" name="quantity" value="${settings.quantity}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Количество смс:
                </td>
                <td>
                    <input type="text" name="interval" value="${settings.interval}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <%if (request.getAttribute("apply") != null && request.getAttribute("apply").equals("true")){%>
                    <h5 id="transparentText" class="DTransparentText">
                        Изменения применены :)
                    </h5>
                    <%}%>
                </td>
                <td>
                    <input name="submit" class="button" type="submit" value="Отправить" />
                </td>
            </tr>
        </table>
    </form>
    <h5><font color="red">ВНИМАНИЕ!</font> При изменении названия организации Вам потребуется произвести повторную авторизацию.</h5>
</div>