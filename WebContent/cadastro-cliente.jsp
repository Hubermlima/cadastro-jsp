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

	<section class="tab">

		<form class="" id="myForm" method="post" action="SaveClienteServlet"
			enctype="multipart/form-data">

			<div class="container-fluid"
				style="margin: auto; height: 100%; padding-top: 70px; padding-bottom: -5px;">

				<div class="card shadow p-3 mb-1 bg-white rounded">
					<!-- MOLDURA -->
					<div class="row">

						<div class="col-md-3 pr-1">
							<div class="card">
								<div class="card-header p-1 text-center">

									<b>Perfil</b>

									<div class="custom-file">
										<input type="file" class="custom-file-input" id="fotoDesc"
											name="fotoDesc"> <label
											class="custom-file-label text-left" for="fotoDesc"
											data-browse="Selecionar">Selecione uma foto</label>
									</div>

								</div>


								<div class="card rounded-0 mx-3 my-3">

									<c:choose>
										<c:when test="${empty cliente.miniaturaImagem}">
											<a><img name="imgCliente" id="imgCliente"
												src="resources/img/cliente-padrao.jpg"
												style="width: 100%; max-height: 300px; overflow: hidden;"></a>
										</c:when>
										<c:otherwise>
											<a title="Download foto"
												href="SaveClienteServlet?acao=download&cliente=${cliente.id}"><img
												name="imgCliente" id="imgCliente"
												src="<c:out value="${cliente.miniaturaImagem}"></c:out>"
												style="width: 100%; max-height: 300px; overflow: hidden;"
												alt="foto Cliente"></a>
										</c:otherwise>

									</c:choose>

								</div>

								<input type="hidden" id="miniaturaFotoBase64Oculto"
									name="miniaturaFotoBase64Oculto"
									value="${cliente.miniaturaImagem}"> <input
									type="hidden" id="tempMiniaturaFotoBase64Oculto"
									name="tempMiniaturaFotoBase64Oculto"
									value="${cliente.miniaturaImagem}">

								<p class="card-title text-center m-0">
									<span id="nomeDesc">${cliente.nome}</span>
								</p>
								<p class="text-center m-0">
									<span id="foneDesc" class="telefone">${cliente.telefone1}</span>
								</p>
								<p class="text-center m-0">
									<span id="emailDesc">${cliente.email}</span>
								</p>

								<div class="btn-group mx-auto p-2 mb-2" role="group"
									aria-label="Basic example">
									<button class="btn btn-success" type="submit" id="submeter"
										name="submeter">Submeter</button>

									<button id="cancel" type="submit" class="btn btn-danger">Cancelar</button>


								</div>

							</div>

						</div>
						<div class="col-md-9">


							<div class="card text-center">
								<h5 class="p-1">Cadastro de clientes</h5>

								<!-- Nav tabs -->
								<ul class="nav nv nav-tabs">
									<li class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#home">Características</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#menu1">Localização</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#menu2">Identificação</a></li>

									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#menu3">Lista</a></li>

								</ul>



								<!-- Tab panes -->
								<div class="tab-content">
									<div id="home" class="p-2 container-fluid tab-pane active">

										<!--  INICIO CARD GROUP CARACTERISTICAS -->
										<div class="card-group p-2 text-center">
											<div class="card">
												<div class="card-header p-1">Consignatário</div>
												<div class="card-body">

													<!-- Id -->
													<div class="input-group mb-3">
														<div class="input-group-prepend">
															<label class="input-group-text" for="id">Id</label>
														</div>

														<input class="form-control" type="text" id="id" name="id"
															value="${cliente.id}" readonly
															style="background-color: white;">
													</div>

													<!-- Titulo -->
													<div class="row">
														<div class="col">
															<label class="label">Título</label> <select
																class="custom-select mb-2" id="titulo" name="titulo"
																required>
																<option value="" disabled>Escolha opção</option>
																<option value="Sr."
																	${cliente.titulo == 'Sr.' ? 'selected' : ''}>Sr.</option>
																<option value="Sra."
																	${cliente.titulo == 'Sra.' ? 'selected' : ''}>Sra.</option>
																<option value="Srta."
																	${cliente.titulo == 'Srta.' ? 'selected' : ''}>Srta.</option>
															</select>
														</div>
													</div>

													<!-- Nome -->
													<div class="row">
														<div class="col">
															<label class="label" for="nome">Nome do cliente</label> <input
																class="form-control mb-2 verificar" type="text"
																id="nome" name="nome" value="${cliente.nome}">

														</div>
													</div>

													<!-- Genero -->
													<div class="row">
														<div class="col">
															<label class="label">Genero</label> <select
																class="custom-select mb-2" id="genero" name="genero">
																<option value="" disabled>Escolha opção</option>
																<option value="Masculino"
																	${cliente.genero == 'Masculino' ? 'selected' : ''}>Masculino</option>
																<option value="Feminino"
																	${cliente.genero == 'Feminino' ? 'selected' : ''}>Feminino</option>
																<option value="Trans"
																	${cliente.genero == 'Trans' ? 'selected' : ''}>Trans</option>
															</select>
														</div>
													</div>
												</div>

											</div>
											<div class="card">
												<div class="card-header p-1">Header</div>
												<div class="card-body">
													<!-- Estado civil -->
													<div class="row">
														<div class="col">
															<label class="label">Estado civil</label> <select
																class="custom-select mb-2" id="estadoCivil"
																name="estadoCivil">
																<option value="" disabled>Escolha opção</option>
																<option value="Solteiro"
																	${cliente.estadoCivil == 'Solteiro' ? 'selected' : ''}>Solteiro(a)</option>
																<option value="Casado"
																	${cliente.estadoCivil == 'Casado' ? 'selected' : ''}>Casado(a)</option>
																<option value="Separado"
																	${cliente.estadoCivil == 'Separado' ? 'selected' : ''}>Separado(a)</option>
																<option value="Divorciado"
																	${cliente.estadoCivil == 'Divorciado' ? 'selected' : ''}>Divorciado(a)</option>
																<option value="Viúvo(a)"
																	${cliente.estadoCivil == 'Viúvo(a)' ? 'selected' : ''}>Viúvo(a)</option>
															</select>
														</div>
													</div>

													<!-- Data nascimento -->
													<div class="row">
														<div class="col">
															<label class="label">Data de nascimento</label> <input
																type="text" id="dataNascimento" name="dataNascimento"
																value="${cliente.dataNascimento}"
																class="form-control date verificar validarDataBr">
														</div>
													</div>


												</div>

											</div>
										</div>

										<!-- FIM CARD GROUP CARACTERISTICA -->

									</div>
									<div id="menu1" class="container-fluid tab-pane fade">

										<!--  INICIO CARD GROUP LOCALIZACAO -->

										<div class="card-group p-2 text-center">
											<div class="card">
												<div class="card-header p-1">Endereço</div>
												<div class="card-body">

													<!-- CEP -->
													<div class="row">
														<div class="col">
															<label class="label" for="cep">Código postal</label> <input
																class="form-control mb-2 cep verificar" type="text"
																id="cep" name="cep" value="${cliente.cep}">

														</div>
													</div>

													<!-- Endereço -->
													<div class="row">
														<div class="col">
															<label class="label" for="endereco">Endereço</label> <input
																class="form-control mb-2 verificar" type="text"
																id="endereco" name="endereco"
																value="${cliente.endereco}">

														</div>
													</div>

													<!-- Numero -->
													<div class="row">
														<div class="col">
															<label class="label" for="numero">Número</label> <input
																class="form-control mb-2 verificar" type="text"
																id="numero" name="numero" value="${cliente.numero}">

														</div>
													</div>

													<!-- Complemento -->
													<div class="row">
														<div class="col">
															<label class="label" for="complemento">Complemento</label>
															<input class="form-control mb-2" type="text"
																id="complemento" name="complemento"
																value="${cliente.complemento}">

														</div>
													</div>


												</div>

											</div>
											<div class="card">
												<div class="card-header p-1">Contato</div>
												<div class="card-body">


													<!-- Bairro -->
													<div class="row">
														<div class="col">
															<label class="label" for="bairro">Bairro</label> <input
																class="form-control mb-2 verificar" type="text"
																id="bairro" name="bairro" value="${cliente.bairro}">

														</div>
													</div>

													<!-- Cidade -->
													<div class="row">
														<div class="col">
															<label class="label" for="cidade">Cidade</label> <input
																class="form-control mb-2 verificar" type="text"
																id="cidade" name="cidade" value="${cliente.cidade}">

														</div>
													</div>

													<!-- Estado -->
													<div class="row">
														<div class="col">
															<label class="label" for="estado">Estado</label> <input
																class="form-control mb-2 verificar" type="text"
																id="estado" name="estado" value="${cliente.estado}">

														</div>
													</div>

													<!-- Telefone1 -->
													<div class="row">
														<div class="col">
															<label class="label" for="telefone1">Telefone #1</label>
															<input class="form-control mb-2 telefone verificar"
																type="text" id="telefone1" name="telefone1"
																value="${cliente.telefone1}">

															<button type="button" class="btn btn-secondary"
																id="telefones" name="telefones" onclick="showModalTelefones()">
																<span aria-hidden="true">&uarr;</span>Fones
															</button>


														</div>
													</div>

												</div>

											</div>
											<div class="card">
												<div class="card-header p-1">Email</div>
												<div class="card-body">

													<!-- Telefone2 -->
													<div class="row">
														<div class="col">
															<label class="label" for="telefone1">Telefone #2</label>
															<input class="form-control mb-2 telefone" type="text"
																id="telefone2" name="telefone2"
																value="${cliente.telefone2}">
														</div>
													</div>

													<!-- Email -->
													<div class="row">
														<div class="col">
															<label class="label" for="email">Melhor email</label> <input
																class="form-control mb-2" type="text" id="email"
																name="email" value="${cliente.email}">
														</div>
													</div>

												</div>

											</div>
										</div>
										<!-- FIM CARD GROUP LOCALIZACAO -->

									</div>
									<div id="menu2" class="container-fluid tab-pane fade">


										<!--  INICIO CARD GROUP IDENTIFICACAO-->

										<div class="card-group p-2 text-center">
											<div class="card">
												<div class="card-header p-1">Header</div>
												<div class="card-body">

													<!-- Pessoa física /juridica-->
													<div class="row">
														<div class="col">
															<fieldset class="form-group">
																<legend>Selecione</legend>
																<div class="form-check-inline">
																	<label class="form-check-label"> <input
																		type="radio" class="form-check-input"
																		name="optionsRadios" checked id="tipoFisica" value="1"
																		${cliente.tipo == 1 ?'checked':''}> Pessoa
																		física
																	</label>
																</div>
																<div class="form-check-inline">
																	<label class="form-check-label"> <input
																		type="radio" class="form-check-input"
																		name="optionsRadios" id="tipoJuridica" value="2"
																		${cliente.tipo == 2 ?'checked':''}> Pessoa
																		jurídica
																	</label>
																</div>

															</fieldset>

														</div>
													</div>

													<div class="row">
														<div class="col">

															<label class="label" for="cpfCnpj" id="cpfCnpjLabel">CPF
																ou CNPJ</label> <input
																class="form-control mb-2 cpfCnpj verificar" type="text"
																id="cpfCnpj" name="cpfCnpj" value="${cliente.cpfCnpj}">

														</div>
													</div>

													<div class="row">
														<div class="col">

															<label class="label" for="rgInscricao"
																id="rgInscricaoLabel">RG ou Inscrição Estadual</label> <input
																class="form-control mb-2" type="text" id="rgInscricao"
																name="rgInscricao" value="${cliente.rgInscricao}">
														</div>
													</div>

													<div class="row">
														<div class="col">

															<label class="label" for="cnh">CNH</label> <input
																class="form-control mb-2" type="text" id="cnh"
																name="cnh" value="${cliente.CNH}">
														</div>
													</div>

												</div>

											</div>
											<div class="card">
												<div class="card-header p-1">Header</div>
												<div class="card-body">

													<!-- Tipo pessoa -->
													<div class="row">
														<div class="col">
															<label class="label" for="">Observações</label>
														</div>
													</div>
													<div class="row">
														<div class="col">

															<textarea class="form-control" id="infoAdicional"
																name="infoAdicional" rows="12">
															        <c:out value="${cliente.infoAdicional}"></c:out>
																</textarea>

														</div>

													</div>


												</div>

											</div>
										</div>
										<!-- FIM ULTIMO CARD GROUP  -->

									</div>
									<div id="menu3" class="container-fluid tab-pane fade">

										<div class="table-responsive p-2 scroll">
											<table id="tabela"
												class="table table-bordered table-hover table-sm table-striped mb-0"
												width="100%">

												<thead class="thead-dark">

													<tr class="text-center">
														<th scope="col">#</th>
														<th scope="col">Nome</th>
														<th scope="col">Estado Civil</th>
														<th scope="col">Cidade</th>
														<th scope="col">Telefone</th>
														<th scope="col">Excluir</th>
														<th scope="col">Editar</th>
													</tr>
												</thead>
												<tbody id="clientesBody">
													<c:forEach items="${clientes}" var="cliente">
														<tr>
															<td scope="row" class="text-center"><c:out
																	value="${cliente.id}"></c:out></td>
															<td class="text-left"><c:out value="${cliente.nome}"></c:out></td>
															<td class="text-left"><c:out
																	value="${cliente.estadoCivil}"></c:out></td>
															<td class="text-left"><c:out
																	value="${cliente.cidade}"></c:out></td>
															<td class="text-center telefone"><c:out
																	value="${cliente.telefone1}"></c:out></td>
															<td class="d-none"><c:out value="${cliente.email}"></c:out></td>
															<td class="text-center"><a class="delete"
																href="SaveClienteServlet?acao=delete&cliente=${cliente.id}"><img
																	alt="Excluir" title="Excluir cliente"
																	src="resources/img/trash-alt-solid.svg" width="15px"
																	height="15px"></a></td>
															<td class="text-center"><a class="update"
																href="SaveClienteServlet?acao=editar&cliente=${cliente.id}"><img
																	alt="Editar" title="Editar cliente"
																	src="resources/img/edit-solid.svg" width="15px"
																	height="15px"></a></td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>


									</div>

								</div>

								<div class="card-footer p-2">
									<h5 id="status" class="text-danger">${mensagem}</h5>
								</div>
							</div>
							<!-- end card  -->
						</div>
						<!-- end col -->
					</div>
					<!-- end row -->
				</div>
				<!-- moldura -->
			</div>
			<!-- end container-fluid -->
		</form>
	</section>

	<section>

		<!-- Modal Confirma deleção-->

		<div class="modal fade" id="confirmaDelecao" data-backdrop="static"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Confirmar deleção</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div id="msgDelecao" class="modal-body">Deseja realmente
						remover o cliente?</div>
					<div class="modal-footer">
						<button id="naoDeleta" type="button" class="btn btn-secondary"
							data-dismiss="modal">Não</button>
						<button id="simDeleta" type="button" class="btn btn-primary">Sim</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal Telefone-->

		<div class="modal fade" data-backdrop="static" id="telefoneModal"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered mw-100 w-75 h-75"
				role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">${cliente.nome}</h5>

						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>



					<div class="modal-body">

						<div class="container-fluid">

							<div class="form-row align-items-center">
								<div class="col-auto">
									<label for="idTelefone" id="idLabel">Id</label> <input
										class="form-control telefone" type="text" id="idTelefone"
										name="idTelefone" value="" readonly>
								</div>

								<div class="col-auto">
									<label for="telefonePlus" id="rgInscricaoLabel">Telefone</label>

									<input class="form-control telefone" type="text"
										id="telefonePlus" name="telefonePlus" value="">
								</div>

								<div class="col-auto">
									<label for="tipo" id="tipoFone">Classificação</label> <select
										class="custom-select" id="classificacao" name="classificacao"
										required>
										<option value="" disabled>Escolha uma opção</option>
										<option value="Residencial">Residencial</option>
										<option value="Trabalho">Trabalho</option>
										<option value="Terceiros">Terceiros</option>
									</select>

								</div>

							</div>

							<div class="form-row align-items-center">
								<div class="table-responsive p-2 scroll">
									<table
										class="table table-bordered table-hover table-sm table-striped mb-0"
										width="100%" id="tabelaTelefones" name="tabelaTelefones">

										<thead class="thead-dark">

											<tr class="text-center">
												<th scope="col">#</th>
												<th scope="col">Número</th>
												<th scope="col">Classificação</th>
												<th scope="col">Excluir</th>
												<th scope="col">Editar</th>
											</tr>
										</thead>

										<tbody id="myBody">

											<c:forEach items="${cliente.listaFone}" var="lista">
												<tr>
													<td class="text-center"><c:out value="${lista.id}"></c:out></td>
													<td class="text-left"><c:out value="${lista.numero}"></c:out></td>
													<td class="text-center"><c:out value="${lista.tipo}"></c:out></td>
													<td class="text-center"><a href="#">Excluir</a></td>
													<td class="text-center"><a href="#">Editar</a></td>
												</tr>
											</c:forEach>

										</tbody>

									</table>
									<p>
									<p id="msg" class="mt-2 mb-0"></p>
								</div>
							</div>
						</div>

					</div>

					<div class="modal-footer">

						<button class="form-control btn btn-primary"
							id="adicionarTelefone" name="adicionarTelefone">Adicionar/Atualizar
						</button>

						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fechar</button>

					</div>
				</div>
			</div>
		</div>

	</section>

	<section>

		<script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
		<script src="resources/js/jquery.validate.min.js"></script>
		<script src="resources/js/main.js"></script>

	</section>

	<script type="text/javascript">
		function showModalTelefones() {
			if ($("#id").val() != '') { // Só mostra o modal quando o cliente já estiver cadastrado
				$('#telefoneModal').modal('show');
			}
		}
	</script>
</body>
</html>