<%@ page import="ru.kl.proj.entity.SmsTemplates" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script>
    function addOneForm() {
        let url = new URL('http://localhost:8080/accountMainPage?pageMarker=smsTemplates');
        let params = new URLSearchParams(url.search.slice(1));
        params.append('addForm', 'true');
        document.forms["templatesForm"].submit();
            // window.location.href = "http://localhost:8080/accountMainPage?pageMarker=smsTemplates&addForm=true";
        }
</script>
<h2 align="center">СМС шаблоны</h2>
<br>
<div class="rightSideContentSpace">
    <form id="templatesForm" name="smsTemplates" action="/smsTemplates" method="post">
        <table>
        <%
            List <SmsTemplates> listOfTemplates = (List <SmsTemplates>) request.getAttribute("smsTemplates");
            for (SmsTemplates t: listOfTemplates) {
        %>
            <tr>
                <td>
                    <input type="text" name="oid" value='<%= t.getOid() %>' hidden/>
                    <input type="hidden" name="deleteField<%= t.getTid() %>" />
                </td>
                <td>
                    <input type='text' name='tid' value='<%= t.getTid() %>' hidden/>
                    <h4>Шаблон №<%= t.getTid() %>:</h4>
                    <input name="deleteTemplate"  class="button" type="button" value="-" onclick="{document.smsTemplates.deleteField<%= t.getTid() %>.value=this.value;document.smsTemplates.submit();}"/>
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
                <td>
                    <input type="hidden" name="hiddenField" />
                </td>
                <td>

                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input name="addForm"  class="button" type="button" value="Добавить шаблон" onclick="{document.smsTemplates.hiddenField.value=this.value;document.smsTemplates.submit();}"/>
                </td>
                <td>
                    <input name="submitFrom" class="button" type="submit" value="Отправить" />
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

