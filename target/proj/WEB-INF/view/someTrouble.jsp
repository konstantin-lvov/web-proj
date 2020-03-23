<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href='<spring:url value="/resource/styles.css"/>' rel="stylesheet" />
</head>
<body>
<jsp:include page="menuBar.jsp" />
<div class="commonSpace">
    <h2>Доступ запрещен</h2>
    <h4>${message}</h4>
</div>
</body>
</html>
