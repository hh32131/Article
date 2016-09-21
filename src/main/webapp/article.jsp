<%@page import="java.util.List"%>
<%@page import="net.hookies.vo.ArticleVO"%>
<%@page import="net.hookies.dao.ArticleDao"%>
<%@page import="net.hookies.dao.ArticleDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ArticleDao dao = new ArticleDaoImpl();
	List<ArticleVO> articles = dao.getArticles();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> ARTICLE</h1>
	<hr/>
	<table style="width: 100%; border-collapse: collapse; border: 1px solid #000000;">
		<tr style = "height: 50px">
			<th>ARTICLE ID</th>
			<th>SUBJECT</th>
			<th>CONTENTS</th>
			<th>CREATE DATE</th>
		</tr>
<%
	int aritlcesSize = articles.size();
	ArticleVO article = null;
	for( int i =0; i < aritlcesSize; i++ ){
		article = articles.get(i);

%>
		<tr>
			<td><%= article.getArticleId() %></td>
			<td><%= article.getSubject() %></td>
			<td><%=article.getContents() %></td>
			<td><%= article.getCreateDate() %></td>
		</tr>
<% } %>
	</table>
</body>
</html>