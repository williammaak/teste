package br.sceweb.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

/**
 * Servlet implementation class ServletControle
 * @author William Makda
 * @version $Revision: 1.0 $
 */
public class ServletControle extends HttpServlet {
	/**
	 * Field serialVersionUID.
	 * (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field logger.
	 */
	Logger logger = Logger.getLogger(ServletControle.class);
	/**
	 * Field mensagem.
	 */
	String mensagem = "";
	/**
	 * Field empresaDAO.
	 */
	EmpresaDAO empresaDAO;
	/**
	 * Field cnpjParaExclusao.
	 */
	String cnpjParaExclusao = "";// seta o cnpj para exclusao

	/**
	 * Default constructor.
	 */
	public ServletControle() {
		// TODO Auto-generated constructor stub

	}

	/**
	
	 * @param request HttpServletRequest requisição HTTP
	 * @param response HttpServletResponse resposta HTTP
	 * @throws ServletException exceção do Servlet
	 * @throws IOException exceção de IO
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	
	 * @param request HttpServletRequest requisição HTTP
	 * @param response HttpServletResponse resposta HTTP
	 * @throws ServletException exceção do Servlet
	 * @throws IOException exceção de IO
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("erro", null);
		executaComando(request, response);
	}

	/**
	 * Method Executar um comando.
	 * @param request HttpServletRequest requisição HTTP
	 * @param response HttpServletResponse resposta HTTP
	 * @throws ServletException exceção do Servlet
	 * @throws IOException exceção de IO
	 */
	protected void executaComando(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametro = request.getParameter("acao");
		logger.info("acao do servletcontrole = " + parametro);
		String url = "";
		String resultado = "";
		request.setAttribute("erro", null);
		if (parametro.equals("IncluirEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			try {
				resultado = cadastrarEmpresa(request.getParameter("txtCNPJ"), request.getParameter("txtNomeDaEmpresa"),
						request.getParameter("txtNomeFantasia"), request.getParameter("txtEndereco"),
						request.getParameter("txtTelefone"));
				logger.info("resultado do cadastra = " + resultado);
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
				logger.info("erro  = " + e.getMessage());

			}
		}
		if (parametro.equals("ConsultarEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			Empresa empresa = new Empresa();
			String cnpj = request.getParameter("txtCNPJ");
			logger.info("consulta empresa  = " + cnpj);
			try {
				if (!cnpj.isEmpty()) {
					empresa = consulta(cnpj);
					if (empresa != null){
						logger.info("consulta empresa nome da empresa  = " + empresa.getNomeDaEmpresa());
						request.setAttribute("nomeDaEmpresa", empresa.getNomeDaEmpresa());
					    request.setAttribute("cnpj", empresa.getCnpj());
					    request.setAttribute("nomeFantasia", empresa.getNomeFantasia());
					    request.setAttribute("endereco", empresa.getEndereco());
					    request.setAttribute("telefone", empresa.getTelefone());
					    request.setAttribute("msg", "");
						url = "/visao/FormEmpresaResultadoDaConsulta.jsp";
					} else {
						request.setAttribute("msg", "cnpj invalido");
					}
				} else {
					request.setAttribute("msg", "cnpj invalido");
				}
			} catch (Exception e) {
				logger.info(e.getMessage() + e.getCause());
			}
			request.getRequestDispatcher(url).forward(request, response);
			
		}

	}

	/**
	 * Method Cadastrar uma Empresa.
	 * @param cnpj String CNPJ da empresa
	 * @param nomeDaEmpresa String nome da empresa
	 * @param nomeFantasia String nome fantasia
	 * @param endereco String endereço
	 * @param telefone String telefone
	 * @return String
	 */
	public String cadastrarEmpresa(String cnpj, String nomeDaEmpresa, String nomeFantasia, String endereco,
			String telefone) {
		String msg = "";
		Empresa empresa = new Empresa();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		try {
			empresa.setCnpj(cnpj);
			empresa.setNomeDaEmpresa(nomeDaEmpresa);
			empresa.setNomeFantasia(nomeFantasia);
			empresa.setEndereco(endereco);
			empresa.setTelefone(telefone);
			empresaDAO.adiciona(empresa);
			msg = "cadastro realizado com sucesso";

		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}

	/**
	 * Method Consultar uma empresa.
	 * @param cnpj String CNPJ da empresa
	 * @return Empresa
	 */
	public Empresa consulta(String cnpj) {
		logger.info("consulta empresa 2 = " + cnpj);
		EmpresaDAO empresaDAO = new EmpresaDAO();
		return empresaDAO.consultaEmpresa(cnpj);
	}

	/**
	 * Method Excluir uma empresa.
	 * @param cnpj String CNPJ da empresa
	 * @return String
	 */
	public String excluirEmpresa(String cnpj) {
		String msg = "";
		EmpresaDAO empresaDAO = new EmpresaDAO();
		try {
			empresaDAO.exclui(cnpj);
			msg = "excluido com sucesso";
		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}
}
