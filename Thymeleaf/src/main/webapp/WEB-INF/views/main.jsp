<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<th>언어</th>
				<th>데이터베이스</th>
			
					
			
			</tr>
				
				<tr>
				
					<td>${map.language}</td>
					<td>${map.database}</td>
				
			
					
				</tr>
		</table>
		<div>
			<table>
				<c:forEach items="${list}" var='task'>
					<tr>
						<td>${task}</td>
					</tr>
				
				</c:forEach>
			</table>
		
		</div>
	</div>
</body>
</html>