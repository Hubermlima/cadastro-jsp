package servlets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.Cliente;
import dao.ClienteDao;
import validacao.ClienteValidacao;

@WebServlet("/SaveClienteServlet")
@MultipartConfig
public class SaveClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String id;
	String acao;
	Cliente cliente;
	ClienteDao clienteDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		acao = request.getParameter("acao");
		id = request.getParameter("cliente");

		cliente = new Cliente();
		clienteDao = new ClienteDao();

		switch (acao) {
		case "delete":
			doDelete(request);
			break;
		case "editar":
			doEdit(request);
			break;
		case "listarTodos":
			doListAll(request);
			break;
		default:
			doDownloadFoto(response);
			return;
		}
		
		redireciona(response);
		
	} // end doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int tipo = 0;
		String miniaturaBase64 = "";
		String tempMiniatura = "";
		String fotoBase64 = "";
		String contentType = "";
		String cadastroInvalido = null;

		acao = request.getParameter("acao");
		id = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String nome = request.getParameter("nome");
		String genero = request.getParameter("genero");
		String estadoCivil = request.getParameter("estadoCivil");
		String dataNascimento = request.getParameter("dataNascimento");
		String cep = request.getParameter("cep");
		String endereco = request.getParameter("endereco");
		String numero = request.getParameter("numero");
		String complemento = request.getParameter("complemento");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String telefone1 = request.getParameter("telefone1");
		String telefone2 = request.getParameter("telefone2");
		String email = request.getParameter("email");
		String cpfCnpj = request.getParameter("cpfCnpj");

		if (cpfCnpj.replaceAll("[^0-9]", "").length() == 11) {
			tipo = 1; // CPF
		}

		if (cpfCnpj.replaceAll("[^0-9]", "").length() == 14) {
			tipo = 2; // CNPJ
		}

		String rgInscricao = request.getParameter("rgInscricao");
		String cnh = request.getParameter("cnh");
		String infoAdicional = request.getParameter("infoAdicional");

		// Obtem foto do formulário enviado
		miniaturaBase64 = request.getParameter("miniaturaFotoBase64Oculto");
		tempMiniatura = request.getParameter("tempMiniaturaFotoBase64Oculto");

		// ------------ Rotina de captura do upload de fotos!

		if (ServletFileUpload.isMultipartContent(request)) {

			Part imageFoto = request.getPart("fotoDesc");

			// Testar se é outra foto, porque senão dará erro se fazer conversao
			// desnecessária (mesma base64)
			
			if (imageFoto.getContentType().split("\\/")[0].equalsIgnoreCase("image")) {
				
				byte[] bytesImagem = convertStringtoByte(imageFoto.getInputStream());
				new Base64();
				fotoBase64 = Base64.encodeBase64String(bytesImagem);
				contentType = "data:" + imageFoto.getContentType() + ";base64,";

				// Inicio Miniatura imagem

				new Base64();
				byte[] imageByteDecode = Base64.decodeBase64(fotoBase64);
				BufferedImage bIMG = ImageIO.read(new ByteArrayInputStream(imageByteDecode));

				// Pegar tipo de imagem
				int type = bIMG.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bIMG.getType();

				// Criar imagem em miniatura
				BufferedImage resizedImage = new BufferedImage(400, 400, type);
				Graphics2D g = resizedImage.createGraphics();
				g.drawImage(bIMG, 0, 0, 400, 400, null);
				g.dispose();

				// Escrever imagem novamente
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(resizedImage, "png", baos);

				miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());

				// Fim miniatura imagem
			}
		}

		cliente = new Cliente(id.isEmpty() ? null : Long.valueOf(id), titulo, nome, genero, estadoCivil, dataNascimento,
				cep, endereco, numero, complemento, bairro, cidade, estado, telefone1, telefone2, email, tipo, cpfCnpj,
				rgInscricao, cnh, infoAdicional, fotoBase64, contentType, isVariavelNull(tempMiniatura) ? miniaturaBase64 : miniaturaBase64 + "_" + tempMiniatura);

		clienteDao = new ClienteDao(cliente);

		if (acao != null && acao.equalsIgnoreCase("reset")) { // Operação de cancelamento

			request.setAttribute("clientes", clienteDao.completeList());
			request.setAttribute("mensagem", "Operação cancelada!");

		} else {

			cadastroInvalido = ClienteValidacao.validation(clienteDao);
			if (!cadastroInvalido.isEmpty()) {
				request.setAttribute("mensagem", cadastroInvalido);
				request.setAttribute("cliente", cliente);
			} else if (cliente.getId() == null) {
				clienteDao.insert();
				request.setAttribute("mensagem", "Inclusão realizada com sucesso!");
			} else {
				clienteDao.update();
				request.setAttribute("mensagem", "Atualização realizada com sucesso!");
			}

			request.setAttribute("clientes", clienteDao.completeList());

		} // endif

		redireciona(response);

	} // fim doPost

	private boolean isVariavelNull(String tempMiniatura) {
		if (tempMiniatura == null) {
		return true;
		}
		return false;
	}

	private void doDelete(ServletRequest request) {
		clienteDao.delet(Long.valueOf(id));
		request.setAttribute("clientes", clienteDao.completeList());
		request.setAttribute("mensagem", "Cliente #" + id + " apagado com sucesso!");
	}

	private void doEdit(ServletRequest request) {
		cliente = clienteDao.consult(Long.valueOf(id));
		request.setAttribute("cliente", cliente);
	}

	private void doListAll(ServletRequest request) {
		request.setAttribute("clientes", clienteDao.completeList());
	}

	private void doDownloadFoto(HttpServletResponse response) {
		cliente = clienteDao.consultFoto(Long.valueOf(id));

		if (cliente != null) {

			String[] b = cliente.getContentType().split("\\/");
			String[] c = b[1].split(";");
			response.setHeader("Content-Disposition", "attachment;filename=arquivo." + c[0]);

			new Base64();
			byte[] imageFotoBytes = Base64.decodeBase64(cliente.getFotoBase64());
			InputStream is = new ByteArrayInputStream(imageFotoBytes);

			// Envio da resposta
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream os;
			try {
				os = response.getOutputStream();
				while ((read = is.read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Converte a entrada de fluxo de dados em array de bytes
	private byte[] convertStringtoByte(InputStream image) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		int reads = image.read();
		while (reads != -1) {
			outStream.write(reads);
			reads = image.read();
		}
		return outStream.toByteArray();
	}
	
	private void redireciona(HttpServletResponse response) throws IOException {
		response.sendRedirect("cadastro-cliente.jsp");		
	}

} // fim classe
