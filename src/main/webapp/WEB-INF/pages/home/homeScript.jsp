<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/dist/viewHelper.bundle.js" type="text/javascript"></script>
<script>    
    // 유효성 처리

    // 초기화 데이터 세팅
    var initData = {
      msg : '<c:out value="${serverTime}"></c:out>'
    }

    // 페이지 렌더링
    //console.log( pageEntry)
    //pageEntry.initalize( initData);
    
    // 공통 헤더 생성
    //viewHelper.createView("#header", "/common/HeaderView", initData);

    // 공통 하단 생성  
    //viewHelper.createView("#footer", "/common/FooterView", initData);

    // 모듈 뷰 생성
    //viewHelper.createView("#content", "/home/HomeView", initData);
    
    // 복합 페이지 생성
    viewHelper.createPage("/home/homePage", initData);

   
</script>