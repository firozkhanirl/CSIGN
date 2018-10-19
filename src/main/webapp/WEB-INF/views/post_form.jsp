<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>User form</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
	    <div class="container">
	        <h3 id="form_header" class="text-warning" align="center">Post Form</h3>
	        <div> </div>
	
			<!-- form add or update and existing post-->
	        <c:url var="saveUrl" value="/post/save" />
	        <form:form id="post_form" modelAttribute="postAttr" method="POST" action="${saveUrl}">
	        	<form:hidden path="id" />
	            <label for="post_title">Title: </label>
	            <form:input id="post_title" cssClass="form-control" path="title" />
	            <div> </div>
	            <label for="post_content">Content: </label>
	            <form:input id="post_content" cssClass="form-control" path="content" />
	            <div> </div>

	            <button id="saveBtn" type="submit" class="btn btn-primary">Save</button>
	        </form:form>
	    </div>
	</body>
</html>
