<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String message = (String) request.getAttribute("loginFailure");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おすすめ高知</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="./stylesheets/login.css">
</head>
<body>
	<header>
	  <nav class="navbar navbar-expand-sm navbar-light bg-light">
	    <a class="navbar-brand" href="./product">おすすめ高知</a>
	    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#headerNav" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="ナビゲーションの切替">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="headerNav">
	      <ul class="navbar-nav mr-auto">
	        <li class="nav-item">
	          <a class="nav-link" href="./signup">サインアップ</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="./login">ログイン</a>
	        </li>
	      </ul>
	    </div>
	  </nav>
	</header>
	<main class="main">
		<h1>おすすめ高知へようこそ</h1>
		<hr>
		<% if (message != null) { %>
		  <p><%= message %></p>
		<% } %>
		<form method="post">
		  <label for="name">ユーザ名：</label>
		  <input type="text" name="name" id="name" /><br />
		  <label for="password">パスワード：</label>
		  <input type="password" name="password" id="password" /><br />
		  <input type="submit" value="ログイン" />
		</form>
	</main>
</body>
</html>