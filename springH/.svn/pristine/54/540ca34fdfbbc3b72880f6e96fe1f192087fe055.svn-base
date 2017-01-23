<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<tiles:importAttribute name="title" scope="request"/>
<tiles:importAttribute name="css" scope="request"/>
<tiles:importAttribute name="js" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="${title}"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="Keywords" content="" />
<script src="scripts/main.js"></script>
<link href="style/main.css" type="text/css" rel="stylesheet" />
<c:if test="${!empty css}">
<link href="<tiles:getAsString name="css"/>" type="text/css" rel="stylesheet" />
</c:if>
<script src="scripts/jquery/jQuery-3.1.0.js"></script>
<c:if test="${!empty js}">
<script src="<tiles:getAsString name="js"/>"></script>
</c:if>
</head>
<body>
<div id="position" style="position:relative">
<div><tiles:insertAttribute name="header"/></div>
<div><tiles:insertAttribute name="body"/></div>
<div><tiles:insertAttribute name="footer"/></div>
</div>
</body>
</html>