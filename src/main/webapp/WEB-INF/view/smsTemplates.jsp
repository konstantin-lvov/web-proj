<%@ page import="ru.kl.proj.entity.SmsTemplates" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">СМС шаблоны</h2>
<br>
<div class="rightSideCommonSpace">
    <form name="smsTemplates" action="/smsTemplates" method="post">
        <table>
        <%
            List <SmsTemplates> listOfTemplates = (List <SmsTemplates>) request.getAttribute("smsTemplates");
            for (SmsTemplates t: listOfTemplates) {
        %>
            <tr>
                <td>
                    <input type="text" name="oid" value='<%= t.getOid() %>' hidden/>
                </td>
                <td>
                    <input type='text' name='tid' value='<%= t.getTid() %>' class="customDisabledField" readonly="true">
                </td>
                <td>
                    <input type='text' name='template' value='<%= t.getTemplate()%>' />
                </td>
            </tr>
        <%
            }
        %>
            <tr>
                <td></td>
                <td></td>
                <td>
                    <input name="submit" class="button" type="submit" value="Отправить" />
                </td>
            </tr>
        </table>
    </form>

<%--   <form:form method="post" action = "edit" modelAttribute="smsTemplates">--%>
<%--        <table>--%>
<%--            <c:forEach items="${smsTemplates}" var="currentTemplate" varStatus="tStatus">--%>
<%--                <tr>--%>
<%--                    <td>${currentTemplate.tid}</td>--%>
<%--                    <td><form:input path="smsTemplates[${tStatus.index}].template"></form:input></td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>
<%--    </form:form>--%>
</div>