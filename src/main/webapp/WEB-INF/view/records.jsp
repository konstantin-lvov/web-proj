<%@ page import="java.util.List" %>
<%@ page import="ru.kl.proj.entity.AudioRecord" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">Информация о звонках</h2>
<div class="rightSideContentSpace">
    <form id="audioRecord" name="audioRecord" action="/audioRecord" method="post">
        <table>
            <%
                List <AudioRecord> listOfRecords = (List <AudioRecord>) request.getAttribute("audioRecord");
                for (AudioRecord a: listOfRecords) {
            %>
            <tr>
                <td>
                    <%= a.getRid() %>:
                    <input type="text" name="oid" value='<%= a.getOid() %>' hidden/>
                </td>
                <td>
                    <textarea rows="1" cols="25" name="name" disabled><%=a.getRecordFileName()%></textarea>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </form>
</div>