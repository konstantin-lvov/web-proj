<%@ page import="ru.kl.proj.entity.EntityBucket" %>
<%
    EntityBucket entityBucket = null;
    if(request.getRemoteUser() != null) {
        entityBucket = (EntityBucket) request.getAttribute("entityBucket");
    }
%>
<div class="menuBar">
    <table width="100%">
        <tr>
            <td width="80%"><div class="logoText" onclick="window.location='/';">SUMMARY</div></td>
            <% if ( entityBucket == null ) {%>
            <td><div class="signIn" onclick="window.location='/login';">Sign In</div></td>
            <td><div class="signUp" onclick="window.location='/registration';">Sign Up</div></td>
            <%} else {%>
            <td><div class="signIn" onclick="window.location='/accountMainPage';">Account</div></td>
            <td><div class="signUp" onclick="window.location='/logout';">Logout</div></td>
            <%}%>
        </tr>
    </table>
</div>
