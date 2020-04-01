<%@ page import="java.util.List" %>
<%@ page import="ru.kl.proj.entity.EndlineTemplates" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">Шаблоны окончания строк</h2>
<br>
<div class="rightSideContentSpace">
    <form id="endlines" name="endlines" action="/endlines" method="post">
        <table>
            <%
                List <EndlineTemplates> listOfEndline = (List <EndlineTemplates>) request.getAttribute("endlines");
                for (EndlineTemplates t: listOfEndline) {
            %>
            <tr>
                <td>
                    <input type="text" name="oid" value='<%= t.getOid() %>' hidden/>
                    <input type="text" name="deleteField<%= t.getEtid() %>" hidden/>
                    Шаблон №<%= t.getEtid() %>:
                </td>
                <td>
                    <input type='text' name='etid' value='<%= t.getEtid() %>' hidden/>
                    <textarea inputmode="true" rows="1" cols="20" name='endline' ><%=t.getEndlineTemplate()%></textarea>
                </td>
                <td>

                    <input name="deleteField"  class="button" type="button" value="Удалить" onclick="{document.endlines.deleteField<%= t.getEtid() %>.value=this.value;document.endlines.submit();}"/>
                </td>
                <p> </p>
            </tr>
            <%
                }
            %>
            <tr>
                <td>
                    <input type="hidden" name="hiddenField" />
                </td>
                <td>
                    <input name="addForm"  class="button" type="button" value="Добавить слово" onclick="{document.endlines.hiddenField.value=this.value;document.endlines.submit();}"/>
                </td>
                <td>
                    <input name="submitFrom" class="button" type="submit" value="Отправить" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <%if (request.getAttribute("apply") != null && request.getAttribute("apply").equals("true")){%>
                    <h5 id="transparentText" class="DTransparentText">
                        Изменения применены :)
                    </h5>
                    <%}%>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </form>
</div>

