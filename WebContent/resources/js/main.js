$(document).ready(function() {
					/*
					 * MASCARAS
					 */
					mensagem = "";
					
					$('.telefone').mask('(00) 0 0000-0000');
					$('.cep').mask('00000-000');
					$("#cpfCnpj").mask("999.999.999-99");
					$('.date').mask('00/00/0000');

					var options = {
							onKeyPress : function(cpf, ev, el, op) {
							var masks = [ '000.000.000-000','00.000.000/0000-00' ];
							$('.cpfCnpj').mask((cpf.length > 14) ? masks[1] : masks[0], op);
						}
					}

					$('.cpfCnpj').length > 11 ? $('.cpfCnpj').mask('00.000.000/0000-00', options) : $('.cpfCnpj')
							.mask('000.000.000-00', options);

					/*
					 * VALIDAÇÕES
					 */

					// Verifica se o input é campo obrigatorio 
					$('.verificar').blur(function() {

							if (!$(this).val()) { // Ibput vazio!
								this.style.border = "solid 1px  #FFDAB9"; // borda vermelha
							} else {
								this.style.border = "solid 1px #ccc"; // borda default
							}
						
						return false;
					})

					$("#nav-tabela").click(function(e) {
						$('.nav-tabs a[href="#menu3"]').tab('show')

					});

					$("#cancel").on('click',function() {
                          $('#myForm').get(0).setAttribute('action','SaveClienteServlet?acao=reset');
							});

					$("#submeter").click(function(e) {
						
						var inputs = $(".verificar");
						var aceitarInfo = true;
						/*
						 * Faz uma varredura em todos os inputs que estão
						 * marcados com class = "verificar". Se algum está
						 * vazio, pinta borda de vermelho e não envia pro
						 * servidor.
						 */
						$.each(inputs, function(i) {

							if (this.value == '' || mensagem != '') { // red
								// border controls
								this.style.border = "solid 1px  #FFDAB9";
								aceitarInfo = false;
							}
						});
						
						if (aceitarInfo) {
							$("#myForm").submit();
						} else {
							e.preventDefault();
						}

					});

					$(".cpfCnpj").blur(function() {

						var doc = $(this).val();
						var validate;
						var erro;

						mensagem = mensagem.replace(" Cpf inválido.", "");
						mensagem = mensagem.replace(" Cnpj inválido.", "");

						if ($("#tipoFisica").prop("checked") == true) {
							validateCPF.add(doc);
							erro = " Cpf inválido.";
							validate = validateCPF.validate();

						}
						if ($("#tipoJuridica").prop("checked") == true) {
							validateCNPJ.add(doc);
							validate = validateCNPJ.validate();
							erro = " Cnpj inválido.";
						}
						if (!validate) {
							this.style.border = "solid 1px  #FFDAB9"; // border
							// red
							this.style.backgroundColor = "white"; // border
							// red
							mensagem += erro;
						} else {
							this.style.border = "solid 1px #ccc"; // default
							// border
						}
						$("#status").text(mensagem);
						return false;

					});

					// Validar data pt-br
					$(".validarDataBr").blur(function() { // Perda do foco

						validateData.add($(this).val());
						var validate = validateData.validate();
						
						var erro = " Data inválida.";
						mensagem = mensagem.replace(erro, "");

						if (!validate) {
							this.style.border = "solid 1px  #FFDAB9"; // border
							// red
							this.style.backgroundColor = "white"; // border
							// red
							mensagem += erro;
						} else {
							this.style.border = "solid 1px #ccc"; // default
							// border
						}
						$("#status").text(mensagem);
						return false;
					});

					$("#nome").change(function() {
						$("#nomeDesc").text($(this).val());

					});
					$("#telefone1").change(function() {
						$("#foneDesc").text($(this).val());

					});
					$("#email").change(function() {
						$("#emailDesc").text($(this).val());

					});

					/*
					$('#tabela').DataTable({
						"scrollY" : "200px",
						"paging" : true,
						"pagingType" : "full_numbers",
						"order" : [ [ 2, "asc" ] ],
						"language" : {
							"emptyTable" : "Nenhum dado disponível na tabela",
							"lengthMenu" : "Mostrar _MENU_ entradas",
							"search" : "Pesquisa:",
							"zeroRecords" : "Nada encontrado",
							"info" : "Mostrando página _PAGE_ de _PAGES_",
							"sInfo" : "Mostrando _START_ / _END_ de _TOTAL_ registro(s)",
							"sInfoEmpty" : "Mostrando 0 / 0 de 0 registros",

							"infoEmpty" : "Nenhum registro disponível",
							"infoFiltered" : "(filtrado de _MAX_ registros no total)",
							"paginate" : {
								"first" : "Primeiro",
								"last" : "Ultimo",
								"next" : "Proximo",
								"previous" : "Anterior"
							},

						}

					});
*/
					
					$("#olho").click(function() {
						var x = $("#senha");
						var y = $(this);

						if (x.prop('type') == 'password') {
							x.attr('type', 'text');
							y.attr('src', 'resources/img/eye-solid.svg');
						} else {
							x.attr('type', 'password');
							y.attr('src', 'resources/img/eye-slash-solid.svg');
						}
						return false;
					});

					/*
					 * OBJETOS
					 */
					var validateData = {

						data : undefined,
						isValid : undefined,

						add : function(data) {
							this.data = data;
						},

						validate : function() {

							this.isValid = true;

							if (this.data.length != 10) {
								this.isValid = false;
							}

							var dia = this.data.substr(0, 2);
							var barra1 = this.data.substr(2, 1);
							var mes = this.data.substr(3, 2);
							var barra2 = this.data.substr(5, 1);
							var ano = this.data.substr(6, 4);
							if (dia > 31 || mes > 12) {
								this.isValid = false;
							}
							if ((mes == 4 || mes == 6 || mes == 9 || mes == 11)
									&& dia == 31) {
								this.isValid = false;
							}
							if (mes == 2
									&& (dia > 29 || (dia == 29 && ano % 4 != 0))) {
								this.isValid = false;
							}
							if (ano < 1900) {
								this.isValid = false;
							}

							return this.isValid;
						}
					}

					var validateCPF = {

						cpf : undefined,
						isValid : undefined,

						add : function(cpf) {
							this.cpf = cpf;
						},

						validate : function() {

							this.isValid = true;

							var soma;
							var resto;
							soma = 0;

							this.cpf = this.cpf.replace(/[^\d]+/g, '');

							if (this.cpf == "00000000000")
								this.isValid = false;

							if (this.cpf.length != 11) {
								this.isValid = false;
							}
							for (i = 1; i <= 9; i++)
								soma = soma
										+ parseInt(this.cpf.substring(i - 1, i))
										* (11 - i);
							resto = (soma * 10) % 11;

							if ((resto == 10) || (resto == 11))
								resto = 0;
							if (resto != parseInt(this.cpf.substring(9, 10))) {
								this.isValidate = false;
							}
							soma = 0;
							for (i = 1; i <= 10; i++)
								soma = soma
										+ parseInt(this.cpf.substring(i - 1, i))
										* (12 - i);
							resto = (soma * 10) % 11;

							if ((resto == 10) || (resto == 11))
								resto = 0;
							if (resto != parseInt(this.cpf.substring(10, 11))) {
								this.isValid = false;
							}
							return this.isValid;
						}

					}

					var validateCNPJ = {

						cnpj : undefined,
						isValid : undefined,

						add : function(cnpj) {
							this.cnpj = cnpj;
						},

						validate : function() {

							this.isValid = true;

							this.cnpj = this.cnpj.replace(/[^\d]+/g, '');
							if (this.cnpj == '') {
								this.isValid = false;
							}
							if (this.cnpj.length != 14) {
								this.isValid = false;
							}
							// Elimina this.cnpjs invalidos conhecidos
							if (this.cnpj == "00000000000000"
									|| this.cnpj == "11111111111111"
									|| this.cnpj == "22222222222222"
									|| this.cnpj == "33333333333333"
									|| this.cnpj == "44444444444444"
									|| this.cnpj == "55555555555555"
									|| this.cnpj == "66666666666666"
									|| this.cnpj == "77777777777777"
									|| this.cnpj == "88888888888888"
									|| this.cnpj == "99999999999999")
								this.isValid = false;

							// Valida DVs
							tamanho = this.cnpj.length - 2
							numeros = this.cnpj.substring(0, tamanho);
							digitos = this.cnpj.substring(tamanho);
							soma = 0;
							pos = tamanho - 7;
							for (i = tamanho; i >= 1; i--) {
								soma += numeros.charAt(tamanho - i) * pos--;
								if (pos < 2)
									pos = 9;
							}
							resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
							if (resultado != digitos.charAt(0)) {
								this.isValid = false;
							}

							tamanho = tamanho + 1;
							numeros = this.cnpj.substring(0, tamanho);
							soma = 0;
							pos = tamanho - 7;
							for (i = tamanho; i >= 1; i--) {
								soma += numeros.charAt(tamanho - i) * pos--;
								if (pos < 2)
									pos = 9;
							}
							resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
							if (resultado != digitos.charAt(1)) {
								this.isValid = false;
							}
							return this.isValid;
						}

					}

					/*
					 * PESQUISAR CEP
					 */
					$(".cep").blur(function() { // Perda do foco

						var cep = $(this).val().replace(/\D/g, '');
						if (cep != "") {

							// Consulta o webservice viacep.com.br/
							$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {

								if (!("erro" in dados)) {
									// Atualiza os campos com os valores da consulta.
									$("#mensagemCep").text("");
									$("#endereco").val(dados.logradouro);
									$("#complemento").val(dados.complemento);
									$("#bairro").val(dados.bairro);
									$("#cidade").val(dados.localidade);
									$("#estado").val(dados.uf);
								} // end if.
								else {
									// CEP pesquisado não foi encontrado.
									$("#status").text("Cep não encontrado!");
									limpaCamposCep();
								}

							});

						}

						return false;
							});

					function limpaCamposCep() {
						$("#cep").val("");
						$("#endereco").val("");
						$("#numero").val("");
						$("#complemento").val("");
						$("#bairro").val("");
						$("#cidade").val("");
						$("#estado").val("");

					}

										/*
					 * AJAX METHODS
					 */
					$("#adicionarTelefone").click(function() {
					
						if ( !$('#telefonePlus').val() ) {  // VAZIO
							return false;
						} 
						
						var request = $.ajax({
							url : 'ListaTelefonesServlet',
							type : 'Post',
							data : {
									id : $('#idTelefone').val(),
									telefoneName : $('#telefonePlus').val(),
									classification : $("#classificacao option:selected").val(),
									foreignKey : $("#id").val()
									}
						});
						
						request.done(function(pacote) {
							
							// Fazer o tratamento do pacote
							var codStatus = pacote[1];
							var status = pacote[2];
							var telefone = pacote[3];
							
							if (codStatus == "0") {  // okay do servidor
						      if (telefone.id != $('#idTelefone').val()) { // INCLUSÃO
					
						    	// Criar linha dinamicamente
								var id = $('<td>').append(telefone.id).addClass("text-center");
								var numero = $('<td>').append(telefone.numero).addClass("text-left");
								var tipo = $('<td>').append(telefone.tipo).addClass("text-center");

								var row = $('<tr>')
										.append(id)
										.append(numero)
										.append(tipo)
										.append('<td><a href="#">Excluir</a></td').addClass("text-center")
										.append('><td><a  href="#">Editar</a></td').addClass("text-center");

								$("#tabelaTelefones").append(row); // adicionar linha
								
						      } else {  // ALTERAÇÃO
						    	  
						    	  $('#myBody tr').each(function (index, element){
						    		     if ($(this).find('td').eq(0).text() == telefone.id) {
						    	            $(this).find('td').eq(1).text(telefone.numero);
						    	            $(this).find('td').eq(2).text(telefone.tipo);
						    	           
						    	        }           
						    	  });
						      }
						    }
						    
							// Imprimir resultados da operação
						    $("#msg").text(status);
						    // Limpar campos
						    $("#idTelefone").val('');
							$("#telefonePlus").val('');
							$("#classificacao").val("Residencial");
							
						});
						
						// request.fail() {}

					});

					$("#myBody").on('click', 'a', function(e) {
						e.preventDefault();
						var row = $(this).closest("tr"); // obtem a linha
															// completa
						var id = row.find("td:nth-child(1)").text();
						var numero = row.find("td:nth-child(2)").text();
						var tipo = row.find("td:nth-child(3)").text();
			
						var linkExcluir = row.find("td:nth-child(4)");
					   	
						if ($(this).text() == "Excluir") {
							var request = $.ajax({
								url : "ListaTelefonesServlet",
								method : "GET",
								data : {
									id : id
								},
							});

							request.done(function(data) {
								row.remove();
								$("#msg").text(data);
							});

							request.fail(function(jqXHR, textStatus) {
								$("#msg").text(textStatus);
							});

						} // endif excluir

						if ($(this).text() == "Editar") {
							$("#idTelefone").val(id);
							$("#telefonePlus").val(numero);
							$("#classificacao").val(tipo);
							
						} // endif edit

					});

					
					$('#fotoDesc').on('change',function(e){
		                var fileName = $(this).val().replace('C:\\fakepath\\', " ");
		                $(this).next('.custom-file-label').html(fileName);
		                
		                // Preview do arquivo img...
		                var reader = new FileReader();
		                reader.onload = function(){
			                  var dataURL = reader.result;
			                  $('#imgCliente').attr('src', dataURL);
		                };
		                reader.readAsDataURL(event.target.files[0]);
		            });	
					
					$("#clientesBody").on('click', 'a', function(e) {
						e.preventDefault();
						row = $(this).closest("tr"); // obtem a linha completa
						id = row.find("td:eq(0)").text();
						href = $(this).attr('href');
						if ($(this).hasClass('delete')) { // Excluir
							$('#msgDelecao').text('Deseja realmente remover o cliente #'+ id +' ?');
							$('#confirmaDelecao').modal('show');
						} // endif excluir
						 else { 
							 /*
							  * Instrucao javascript que redireciona de acordo com o href, no caso, 
							  * a servlet de editar
							  */
							 window.location.replace(href);
						 } 
						
					});
					
					// Botão de confirmacao de exclusão no modal
					$("#simDeleta").on('click',function() {
						/*
						 * Instrucao javascript que redireciona de acordo com o href, no caso, a servlet de delete
						 */
						window.location.replace(href); 
				
					});
					
					
					
				}); // fim do ready


/*
 * Métodos úteis no futuro
 * 
 * Respostas do servidor para o Ajax
     $.ajax("dominio.com")
         .done(fazAlgo1)
         .done(fazAlgo2)
         .fail( function (xhr, status, errorThrown ) {
             alert(xhr.responseText);
             alert(status);
         });
         .fail(deuErrado2);
*/
