<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View All Ads</title>
</head>
<body>

<h1>Showing All Ads</h1>

<c:forEach var="ad" items="${ads}">
    <h1>${ad.title}</h1>
    <p>${ad.description}</p>
</c:forEach>

</body>
</html>
