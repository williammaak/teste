package br.sceweb.teste;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

/**
 * @author Lab103
 * @version $Revision: 1.0 $
 */
public class UC01CadastrarEmpresa {
	/**
	 * Field empresa.
	 */
	public static Empresa empresa;
	/**
	 * Field empresaDao.
	 */
	public static EmpresaDAO empresaDao;
	
	/**
	 * Method setUpBeforeClass.
	
	 * @throws Exception */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresa = new Empresa();
		empresaDao = new EmpresaDAO();
		
		empresa.setCnpj("54545454");
		empresa.setNomeDaEmpresa("Empresa");
		empresa.setNomeFantasia("Nome Fantasia");
		empresa.setEndereco("Endereco");
		empresa.setTelefone("2550-2151");
	}

	/**
	 * Method tearDownAfterClass.
	
	 * @throws Exception */
	@After
	public void tearDownAfterClass() throws Exception {
		empresaDao.exclui("54545454");
	}

	/**
	 * Method CT01UC01FB_cadastra_empresa_com_sucesso.
	 */
	@Test
	public void CT01UC01FB_cadastra_empresa_com_sucesso(){
		assertEquals(1, empresaDao.adiciona(empresa));
	}
	
	/**
	 * Method CT02UC01F_cadastra_com_cnpj_ja_cadastrado.
	 */
	@Test(expected=RuntimeException.class)
	public void CT02UC01F_cadastra_com_cnpj_ja_cadastrado(){
		empresaDao.adiciona(empresa);
		empresaDao.adiciona(empresa);
	}
	
	/**
	 * Method CT01UC02FB_exclui_empresa_cnpj_existente.
	 */
	@Test
	public void CT01UC02FB_exclui_empresa_cnpj_existente(){
		empresaDao.adiciona(empresa);
		empresaDao.exclui("54545454");
	}
	
	/**
	 * Method CT01_set_cnpj.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void CT01_set_cnpj(){
		empresa.setCnpj("");
	}
	
	/**
	 * Method CT01_cnpj_invalido.
	 */
	@Test
	public void CT01_cnpj_invalido(){
		assertEquals(false, empresa.isValido("00000000000000"));
		assertEquals(false, empresa.isValido("11111111111111"));
		assertEquals(false, empresa.isValido("22222222222222"));
		assertEquals(false, empresa.isValido("33333333333333"));
		assertEquals(false, empresa.isValido("44444444444444"));
		assertEquals(false, empresa.isValido("55555555555555"));
		assertEquals(false, empresa.isValido("66666666666666"));
		assertEquals(false, empresa.isValido("77777777777777"));
		assertEquals(false, empresa.isValido("88888888888888"));
		assertEquals(false, empresa.isValido("99999999999999"));
		assertEquals(false, empresa.isValido("46446456546564124"));
		assertEquals(false, empresa.isValido("15448"));
	}
	
	/**
	 * Method CT02_cnpj_valido.
	 */
	@Test
	public void CT02_cnpj_valido() {
		assertEquals(true, empresa.isValido("71621191000157"));
	} 

	/**
	 * Method CT01_set_nome_da_empresa.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void CT01_set_nome_da_empresa(){
		empresa.setNomeDaEmpresa("");
	}
	
	/**
	 * Method CT01_set_nome_fantasia_da_empresa.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void CT01_set_nome_fantasia_da_empresa(){
		empresa.setNomeFantasia("");
	}
	
	/**
	 * Method CT01_set_endereco.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void CT01_set_endereco(){
		empresa.setEndereco("");
	}
	
	/**
	 * Method CT01_set_telefone.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void CT01_set_telefone(){
		empresa.setTelefone("");
	}
	
	/**
	 * Method CT01_equals_objeto_nulo.
	 */
	@Test
	public void CT01_equals_objeto_nulo(){
		assertEquals(false, empresa.equals(null));
	}
	
	/**
	 * Method CT01_equals_objeto_nao_nulo.
	 */
	@Test
	public void CT01_equals_objeto_nao_nulo(){
		assertEquals(true, empresa.equals(empresa));
	}
	
	/**
	 * Method CT01_equals_objeto_diferente_da_clasee.
	 */
	@Test
	public void CT01_equals_objeto_diferente_da_clasee(){
		assertEquals(false, empresa.equals(new Object()));
	}
}