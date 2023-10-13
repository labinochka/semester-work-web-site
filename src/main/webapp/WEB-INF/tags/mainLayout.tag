<%@ tag description="Default Layout Tag" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="title"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
</head>
<body>
    <jsp:doBody/>
</body>
</html>