<%--
  Created by IntelliJ IDEA.
  organization: fch
  Date: 09.03.2020
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="commonSpace">
    <table>
        <tr>
            <td>
                <div class="leftSideBarSpace">
                    <jsp:include page="leftSideBar.jsp" />
                </div>
            </td>
            <td>
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
