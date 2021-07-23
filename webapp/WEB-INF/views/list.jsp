<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <h1>전화번호 리스트</h1>
  <p>입력한 정보 내역입니다.</p>

  <c:forEach items="${pList}" var="pList">

    <table border="1">
      <tr>
        <td>이름</td>
        <td>${pList.name}</td>
      </tr>
      <tr>
        <td>전화번호</td>
        <td>${pList.hp}</td>
      </tr>
      <tr>
        <td>회사</td>
        <td>${pList.company}</td>
      </tr>
      <tr>
        <td><a href="${pageContext.request.contextPath}/delete/${pList.person_id}"> [삭제]</a></td>
        <td><a href="${pageContext.request.contextPath}/modifyForm/${pList.person_id}"> [수정]</a></td>
      </tr>
    </table>
    <br>

  </c:forEach>

  <a href="${pageContext.request.contextPath}/writeForm">[추가번호 등록]</a>

</body>
</html>