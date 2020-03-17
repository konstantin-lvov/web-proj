<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Account</title>
    <link href='<spring:url value="/resource/styles.css"/>' rel="stylesheet" />
</head>
<body>
<jsp:include page="menuBar.jsp" />
<div class="commonSpace">
    <table>
        <tr>
            <td valign="top" width="350px">
                <div class="leftSideBarSpace">
                    <jsp:include page="leftSideBar.jsp" />
                </div>
            </td>
            <td valign="top">
                <div class="rightSideCommonSpace">
                    <% if (request.getParameter("pageMarker") == null) {%>
                    <jsp:include page="organizationInfo.jsp" />
                    <%} else if (request.getParameter("pageMarker").equals("orgSettings")) {%>
                    <jsp:include page="organizationSettings.jsp" />
                    <%}%>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
