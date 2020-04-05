<%@ page import="java.util.List" %>
<%@ page import="ru.kl.proj.entity.CallsInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">Информация о звонках</h2>
<div class="rightSideContentSpace">
    <form id="callsInfo" name="callsInfo" action="/callsInfo" method="post">
        <table>
            <%
                List <CallsInfo> listOfCalls = (List <CallsInfo>) request.getAttribute("callsInfo");
                for (CallsInfo t: listOfCalls) {
            %>
            <tr>
                <td>
                    <%= t.getConvId() %>:
                    <input type="text" name="oid" value='<%= t.getOid() %>' hidden/>
                    <input type='text' name='convId' value='<%= t.getConvId() %>' hidden/>
                </td>
                <td>
                    <textarea rows="1" cols="25" name="phoneNumber" disabled><%=t.getPhoneNumber()%></textarea>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <textarea rows="1" cols="25" name="date" disabled><%=t.getDate()%></textarea>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <textarea rows="4" cols="50" name="info" disabled><%=t.getParsedSms()%></textarea>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </form>
</div>