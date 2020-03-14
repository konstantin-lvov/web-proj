<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page contentType="text/html;encoding=UTF-8" %>
<html>
<head>
    <title>Sign Up</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script>
        function check(e){
            var organization = document.getElementById("organizationName");
            var pas1 = document.getElementById("password");
            var pas2 = document.getElementById("repeatPassword");
            var email = document.getElementById("email");
            if (pas1.value === pas2.value && validateCred(organization, pas1)
            && validateEmail(email)) {
                document.forms['organization'].submit();
            }
            else {
                e.preventDefault();
            }
        }
        function validateEmail(email) {
            var pattern = /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
            if(email.value.match(pattern)){
                return true;
            } else {
                return false;
            }
        }
        function validateCred(organization, pas){
            var pattern = /^[A-Za-z]\w{2,14}$/;
            if(pas.value.match(pattern) && organization.value.match(pattern)){
                return true;
            } else {
                return false;
            }
        }
        function showPas(){
            var pas1 = document.getElementById("password");
            var pas2 = document.getElementById("repeatPassword");
            if (pas1.type === "password") {
                pas1.type = "text";
                pas2.type = "text";
            } else {
                pas1.type = "password";
                pas2.type = "password";
            }
        }
    </script>
</head>
<body>
    <form:form action="/registration" method="POST" modelAttribute="organization">
        <table>
            <tr>
                <td>Organization name:</td>
                <td><form:input path="organizationName" required="" /></td>
            </tr>
            <tr>
                <td>Organization email:</td>
                <td><form:input path="email" required="" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" type="password" required="" /></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input id="repeatPassword" type="password" required="" /></td>
            </tr>
            <tr>
                <td>Show password</td>
                <td><input type="checkbox" onclick="showPas()" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="Save Changes" onclick="check(event)"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
