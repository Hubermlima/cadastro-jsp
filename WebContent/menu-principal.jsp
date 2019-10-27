<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Sistema On-line de Gestão</title>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet"
	href="resources/css/main.css"></link>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"></link>

<!-- Awesome icon -->	
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Table plugin 
<link href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet">
-->		
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<!-- Mascara plugin -->

<script type="text/javascript" src="resources/js/jquery.mask.min.js"></script>

<style>
  #menu_principal a:hover {
	color: whitesmoke !important;
	background: grey !important;
}
</style>

</head>

<body>

<nav class="navbar navbar-expand-sm bg-dark fixed-top navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="#">Logo</a>

		<!-- Links -->
		<ul id="menu_principal" class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="#">Link 1</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link 2</a></li>

			<!-- Dropdown -->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"> Dependências </a>
				
				<div class="dropdown-menu shadow p-3 mb-5 bg-white rounded">
					<a class="dropdown-item" href="SaveUserServlet?acao=listarTodos"> <span class="glyphicon glyphicon-user" aria-hidden="true"></span>Cadastro
						usuários</a>
							
					<a class="dropdown-item" href="SaveClienteServlet?acao=listarTodos"> <span class="fas fa-users-cog"></span>Cadastro
					
						clientes</a>	 
					
				</div></li>
				
			<li class="nav-item"><a class="nav-link" href="LoginServlet?deslogar=true">Sair</a></li>
		</ul>
	</nav>

</body>

</html>