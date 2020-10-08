<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String name=request.getParameter("name");
	String addr=request.getParameter("addr");
	System.out.println(name+" | "+addr);
=======
<%
	String name=request.getParameter("name");
	String addr=request.getParameter("addr");
	System.out.println(name+"|"+addr);
>>>>>>> refs/remotes/upstream/master
%>
{"isSuccess":true}