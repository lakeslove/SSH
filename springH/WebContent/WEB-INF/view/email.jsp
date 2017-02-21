<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
邮件发送成功<br>
<form:form modelAttribute="email" action="sendEmail.htm" method="post">
接收邮箱:<form:input path="toEmailAddresses"/>
邮件标题:<form:input path="subject"/>
邮件内容:<form:textarea path="content"/>
发出邮箱:<form:input path="fromEmailAddress"/>
接收人名:<form:input path="fromPersonName"/>
<input type="submit" value ="发送"/>
</form:form>
</body>
</html>