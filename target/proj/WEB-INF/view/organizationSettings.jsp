<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <h3 align="center">Organization settings</h3>
    <br>
    <form:form action="/settings" method="POST" modelAttribute="entityBucket">
        <form:input path="organization.organizationName" required="" />
        <form:input path="organization.password" required="" />
        <form:input path="organization.email" required="" />
        <form:input path="settings.deferred" required="" />
    </form:form>
