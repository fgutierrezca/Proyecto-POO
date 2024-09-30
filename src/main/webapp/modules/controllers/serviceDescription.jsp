<%@page import="modelLibraries.DAORegistro"%>
<%@page import="programLibraries.FileManager"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String directorioActual = System.getProperty("user.dir");
	
	String path = String.format("%s/PostTwittModel.csv", directorioActual);
	
	FileManager fm = new FileManager();
	fm.setPath(path);
	
	String id = request.getParameter("id");
	
	DAORegistro dao = new DAORegistro();
	String json = dao.returnJSON(fm, id);
%>
<%=json%>