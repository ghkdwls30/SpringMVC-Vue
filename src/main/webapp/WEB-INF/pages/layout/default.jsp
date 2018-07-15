<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" 	uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- 공통 스타일  -->

<!-- 페이지 모듈 스타일  -->
<tiles:insertAttribute name="pageCss" />
</head>
<body>
	<div id="header"></div>
	<div id="content">
		<tiles:insertAttribute name="content" />
	</div>
	<div id="footer"></div>
</body>
</html>
<!-- 공통스크립트  -->
<script src="/webjars/vue/2.5.13/vue.min.js" type="text/javascript"></script>

<!-- 페이지 모듈 스크립트  -->
<tiles:insertAttribute name="pageScript" />