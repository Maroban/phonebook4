<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>전화번호 수정</h1>

  <p>수정 화면입니다. 아래 항목을 수정하고 "수정" 버튼을 클릭하세요.</p>

  <form action="${pageContext.request.contextPath}/modify" method="post">
    <label>이름:</label>
    <input type="text" name="name" value="${onePerson.name}">
    <br>

    <label>전화번호:</label>
    <input type="text" name="hp" value="${onePerson.hp}">
    <br>

    <label>회사:</label>
    <input type="text" name="company" value="${onePerson.company}">
    <br>

    <input type="hidden" name="person_id" value="${onePerson.person_id}">

    <button type="submit">수정</button>

  </form>
</body>
</html>