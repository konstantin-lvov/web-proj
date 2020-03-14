<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body onload='document.organization.organizationName.focus();'>
<h1>Spring Security 5 - Login Form</h1>

<c:if test="${not empty errorMessge}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>

<form name='organization' action="/login" method='POST'>
    <table>
        <tr>
            <td>organizationName:</td>
            <td><input type='text' name='organizationName' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>
