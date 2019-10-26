<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cadastro de usuários</title>

</head>

<body>

	<header>
		<c:import url="menu-principal.jsp" />
	</header>

	<section>
		<div class="container-fluid"
			style="margin: auto; padding-top: 70px; padding-bottom: -5px;">

			<div class="border shadow p-3 mb-5 bg-white rounded">

				<label class="pt-0"><strong>Cadastro de usuários</strong></label>

				<div class="card border-info">

					<div class="card-header">

						<ul class="nav nav-tabs card-header-tabs">
							<li class="nav-item"><a
								class="nav-link active tab-control-link" id="one-tab"
								data-toggle="tab" href="#detalhe" role="tablist"
								aria-controls="One" aria-selected="true"><i
									class="fa fa-info-circle fa-fw"></i>Detalhes</a></li>

							<li class="nav-item"><a class="nav-link tab-control-link"
								id="two-tab" data-toggle="tab" href="#lista" role="tab"
								aria-controls="Two" aria-selected="false"><i
									class="fa fa-list-alt fa-fw"></i>Lista de usuários</a></li>
						</ul>

					</div>

					<div class="tab-content" id="myTabContent">

						<div class="tab-pane fade show active p-3" id="detalhe"
							role="tabpanel" aria-labelledby="one-tab">

							<strong>Detalhes de usuário</strong>


							<%-- Form principal --%>


							<form method="post" action="SaveUserServlet" id="acaoCancelar"
								class="needs-validation" novalidate>

								<div class="form-row">
									<div class="form-group col-3 mb-0">
										<label class="col-form-label" for="id">IDentificação</label> <input
											class="form-control font-weight-bold" type="text" id="id"
											name="id" readonly class="form-control-plaintext"
											value="${usuario.id}">
									</div>
									<div class="form-group col-6 mb-0">
										<label class="col-form-label" for="nome">Nome do
											usuário</label> <input class="form-control" type="text" id="nome"
											name="nome" value="${usuario.nome}" required>

										<div class="invalid-feedback">Por favor, nome requerido!</div>
										<div class="valid-feedback">Parece bom!</div>
									</div>
									<div class="form-group col-3 mb-0">
										<label class="col-form-label" for="telefone">Número
											telefone</label> <input class="form-control telefone" type="text"
											maxlength="25" id="telefone" name="telefone"
											value="${usuario.telefone}" placeholder="Número telefone"
											required>
										<div class="invalid-feedback">Por favor, número telefone requerido!</div>
										<div class="valid-feedback">Parece bom!</div>
									</div>
								</div>

								<div class="form-row">
									<div class="form-group col-md-6 mb-0">
										<label class="col-form-label" for="login">Login</label> <input
											class="form-control" type="text" id="login" name="login"
											value="${usuario.login}" placeholder="Username or Email"
											required>
										<div class="invalid-feedback">Por favor, login requerido!!</div>
										<div class="valid-feedback">Parece bom!</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-form-label" for="senha">Senha</label>

										<div class="input-group">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon1"><img
													alt="" title="Senha do usuário"
													src="resources/img/eye-slash-solid.svg" width="15px"
													height="20px" id="olho"></span>
											</div>

											<input class="form-control" type="password" id="senha"
												name="senha" maxlength="10" value="${usuario.senha}"
												placeholder="Password" required>
											<div class="invalid-feedback">Por favor, senha requerida!!</div>
											<div class="valid-feedback">Parece bom!</div>
										</div>

									</div>
								</div>

								<div class="form-row">
									<div class="form-group col-auto">
										<button type="submit" class="btn btn-primary bt-padrao">Submiter
											infos</button>
									</div>
									<div class="form-group col-auto">
										<button type="submit" class="btn btn-primary bt-padrao"
											onclick="document.getElementById('acaoCancelar').action = 'SaveUserServlet?acao=reset'">Desfazer
											infos</button>
											
									</div>
								</div>

							</form>
						</div>

						<div class="tab-pane fade p-3" id="lista" role="tabpanel"
							aria-labelledby="two-tab">

							<strong class="p-3">Lista de usuários</strong>

							<div class="table-responsive container-fluid scroll">
								<table id="tabela"
									class="table table-bordered table-hover table-sm table-striped mb-0"
									width="100%">

									<thead class="thead-dark">

										<tr class="text-center">
											<th scope="col">#</th>
											<th scope="col">User name</th>
											<th scope="col" class="d-none">Senha</th>
											<th scope="col">Nome do usuário</th>
											<th scope="col">Telefone</th>
											<th scope="col">Excluir</th>
											<th scope="col">Editar</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${usuarios}" var="user">
											<tr>
												<th scope="row" class="text-center"><c:out
														value="${user.id}"></c:out></th>
												<td><c:out value="${user.login}"></c:out></td>
												<td class="d-none"><c:out value="${user.senha}"></c:out></td>
												<td><c:out value="${user.nome}"></c:out></td>
												<td class="text-center"><c:out value="${user.telefone}"></c:out></td>
												<td class="text-center"><a
													href="SaveUserServlet?acao=delete&user=${user.id}"><img
														alt="Excluir" title="Excluir usuário" class="rounded"
														src="resources/img/trash-alt-solid.svg" width="15px"
														height="15px"></a></td>
												<td class="text-center"><a
													href="SaveUserServlet?acao=editar&user=${user.id}"><img
														alt="Editar" title="Editar usuário"
														src="resources/img/edit-solid.svg" width="15px"
														height="15px"></a></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>

					</div>

					<div class="card-footer text-muted p-3">
						${mensagem} 
					</div>
				</div>
			</div>
		</div>
	</section>

	<section>

		<script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="resources/js/main.js"></script>
	
	</section>

</body>
</html>