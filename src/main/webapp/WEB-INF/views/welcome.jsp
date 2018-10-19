<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container">
			<h2 id="article_header" class="text-warning" align="center">C Sign Demo Project</h2>
	    	<div> </div>
	    	
	    	<!-- Div to add a new post -->
	    	<div id="new-post">
	    			<c:url var="addUrl" value="/post/add" /><a id="add" href="${addUrl}" class="btn btn-success">Add user</a>
	    	</div>
	    	<div> </div>
			
	    	<!-- show all post -->
	    	<table id="post-list" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>title</th><th>Content</th><th colspan="2"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${posts}" var="post">
	                	<tr align="center">
	                    	<td><c:out value="${post.title}" /></td>
	                    	<td><c:out value="${post.content}" /></td>
	                    	<td>
	                        	<c:url var="editUrl" value="/post/edit?id=${post.id}" /><a id="update" href="${editUrl}" class="btn btn-warning">Update</a>
	                    	</td>
	                    	<td>
	                        	<c:url var="deleteUrl" value="/post/delete?id=${post.id}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Delete</a>
	                    	</td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>	    
	</body>
</html>
