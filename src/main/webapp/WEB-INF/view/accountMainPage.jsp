<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Account</title>
    <link href='<spring:url value="/resources/styles.css"/>' rel="stylesheet" />
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
            <td width="5%"></td>
            <td valign="top" width="100%">
                <div class="rightSideCommonSpace">
                    <% if (request.getParameter("pageMarker") == null) {%>
                    <jsp:include page="organizationInfo.jsp" />
                    <%} else if (request.getParameter("pageMarker").equals("orgSettings")) {%>
                    <jsp:include page="organizationSettings.jsp" />
                    <%} else if (request.getParameter("pageMarker").equals("smsTemplates")) {%>
                    <jsp:include page="smsTemplates.jsp" />
                    <%} else if (request.getParameter("pageMarker").equals("keywords")) {%>
                    <jsp:include page="keywords.jsp" />
                    <%} else if (request.getParameter("pageMarker").equals("endlines")) {%>
                    <jsp:include page="endlineTemplates.jsp" />
                    <%} else if (request.getParameter("pageMarker").equals("contacts")) {%>
                    <jsp:include page="contacts.jsp" />
                    <%} else if (request.getParameter("pageMarker").equals("callsInfo")) {%>
                    <jsp:include page="callsInfo.jsp" />
                    <%} else if (request.getParameter("pageMarker").equals("audioRecord")) {%>
                    <jsp:include page="records.jsp" />
                    <%}%>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
