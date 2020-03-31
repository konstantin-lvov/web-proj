<%@ page import="java.util.List" %>
<%@ page import="ru.kl.proj.entity.Keywords" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">Ключевые слова</h2>
<br>
<div class="rightSideContentSpace">
    <form id="keywordsForm" name="keywords" action="/keywords" method="post">
        <table>
            <%
                List <Keywords> listOfKeywords = (List <Keywords>) request.getAttribute("keywords");
                for (Keywords t: listOfKeywords) {
            %>
            <tr>
                <td>
                    <input type="text" name="oid" value='<%= t.getOid() %>' hidden/>
                    <input type="text" name="deleteField<%= t.getKid() %>" hidden/>
                    Слово №<%= t.getKid() %>:
                </td>
                <td>
                    <input type='text' name='kid' value='<%= t.getKid() %>' hidden/>
                    <textarea inputmode="true" rows="1" cols="20" name='keyword' ><%=t.getKeyword()%></textarea>
                </td>
                <td>

                    <input name="deleteField"  class="button" type="button" value="Удалить" onclick="{document.keywords.deleteField<%= t.getKid() %>.value=this.value;document.keywords.submit();}"/>
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
                    <input name="addForm"  class="button" type="button" value="Добавить слово" onclick="{document.keywords.hiddenField.value=this.value;document.keywords.submit();}"/>
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

