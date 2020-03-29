<%@ page import="ru.kl.proj.entity.SmsTemplates" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">СМС шаблоны</h2>
<br>
<div class="rightSideContentSpace">
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
                    <input type='text' name='tid' value='<%= t.getTid() %>' hidden/>
                    <h4>Шаблон №<%= t.getTid() %>:</h4>
                </td>
                <td>
                    <textarea inputmode="true" rows="5" cols="50" name='template' ><%=t.getTemplate()%></textarea>
                </td>
                <p> </p>
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
            <tr>
                <td></td>
                <td></td>
                <td>
                    <%if (request.getAttribute("apply") != null && request.getAttribute("apply").equals("true")){%>
                    <h5 id="transparentText" class="DTransparentText">
                        Изменения применены :)
                    </h5>
                    <%}%>
                </td>
            </tr>
        </table>
    </form>
</div>
