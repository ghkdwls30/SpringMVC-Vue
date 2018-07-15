<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/dist/homeView.bundle.js" type="text/javascript"></script>
<script>
    
    // 유효성 처리

    // 초기화 데이터 세팅
    var initData = {
      msg : '<c:out value="${serverTime}"></c:out>'
    }

    // 페이지 렌더링
    //console.log( pageEntry)
    pageEntry.initalize( initData);


   
</script>