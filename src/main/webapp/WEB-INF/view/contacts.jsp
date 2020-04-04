<%@ page import="java.util.List" %>
<%@ page import="ru.kl.proj.entity.Contacts" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<h2 align="center">Контакты</h2>
<div class="rightSideContentSpace">
    <h5>Формат записи (через пробелы): <font color="yellow">#Имя# #Фамилия# #Отчество# #Номер телефона(без +)#</font></h5>
    <form id="contacts" name="contacts" action="/contacts" method="post">
        <table>
            <%
                List <Contacts> listOfContacts = (List <Contacts>) request.getAttribute("contacts");
                for (Contacts t: listOfContacts) {
            %>
            <tr>
                <td>
                    <input type="text" name="oid" value='<%= t.getOid() %>' hidden/>
                    <input type="text" name="deleteField<%= t.getCid() %>" hidden/>
                    <%= t.getCid() %>:
                </td>
                <td>
                    <input type='text' name='cid' value='<%= t.getCid() %>' hidden/>
                    <textarea inputmode="true" rows="1" cols="50" name='contact' ><%=t.getContact()%></textarea>
                </td>
                <td>

                    <input name="deleteField"  class="button" type="button" value="Удалить" onclick="{document.contacts.deleteField<%= t.getCid() %>.value=this.value;document.contacts.submit();}"/>
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
                    <input name="addForm"  class="button" type="button" value="Добавить контакт" onclick="{document.contacts.hiddenField.value=this.value;document.contacts.submit();}"/>
                </td>
                <td>
                    <input name="submitFrom" class="button" type="submit" value="Сохранить" />
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