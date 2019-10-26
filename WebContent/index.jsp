
<%-- 
<%@page import="beans.Calcula"%>
<jsp:useBean id="Calcula" class="beans.Calcula" type="beans.Calcula" scope="page"></jsp:useBean>
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body style="margin: 0px;">

	<!-- alinhamento: https://www.devmedia.com.br/como-centralizar-divs-em-html-e-css/37568 -->

	<div
		style="width: 100vw; height: 100vh; background: white; display: flex; flex-direction: row; justify-content: center; align-items: center">

		<div class="container-fluid row justify-content-md-center">

			<div class="col-md-4 shadow-lg p-3 mb-5 bg-white rounded">

				<div class="card">
					<div class="card-header text-center">Login</div>
					<div class="card-body">
						<form method="post" action="LoginServlet">
							
							<label for="login">Username:</label>
								
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1"><img
										alt="" title="Login do usuário"
										src="resources/img/user-tie-solid.svg" width="20px"
										height="20px"></span>
								</div>
								<input type="text" id="login" name="login" value="huber"
								placeholder="Username or Email" class="form-control" required>
							</div>
							
							
						    <label for="password">Password:</label> 
							

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1"><img
										alt="" title="Senha do usuário"
										src="resources/img/key-solid.svg" width="20px"
										height="20px"></span>
								</div>
								<input type="password"
									id="password" name="password" value="11111"
									placeholder="Password" class="form-control" required>
							</div>
								<button type="submit" name="commit" class="btn btn-primary">Login</button>
							

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>