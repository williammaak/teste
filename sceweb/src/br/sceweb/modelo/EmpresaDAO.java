package br.sceweb.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.sceweb.servico.FabricaDeConexoes;

/**
 * @author Lab103
 * @version $Revision: 1.0 $
 */
public class EmpresaDAO {
	
	/**
	 * Field logger.
	 */
	Logger logger = Logger.getLogger(EmpresaDAO.class);
	
	/**
	 * Method adiciona.
	 * @param empresa Empresa
	
	 * @return int */
	public int adiciona(Empresa empresa){
		PreparedStatement ps;
		int codigoRetorno=0;
		try (Connection conn = new FabricaDeConexoes().getConnection()){
			ps = (PreparedStatement) conn.prepareStatement(
					"insert into empresa (cnpj, nomeDaEmpresa, nomeFantasia, endereco, telefone) values(?,?,?,?,?)");
			ps.setString(1,empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeFantasia());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			codigoRetorno = ps.executeUpdate();
			logger.info("codigo de retorno do metodo adiciona empresa = " + codigoRetorno);

			ps.close();
			
		} catch (SQLException e){
				throw new RuntimeException(e);
			}
		return codigoRetorno;
	}
	
	/**
	 * Method exclui.
	 * @param cnpj String
	
	 * @return int */
	public int exclui (String cnpj) {
		java.sql.PreparedStatement ps;
		int codigoretorno = 0;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps= conn.prepareStatement ("delete from empresa where cnpj = ?");
			ps.setString(1, cnpj);
			codigoretorno = ps.executeUpdate();
			}
		catch (SQLException e){
			throw new RuntimeException(e);
		}
	return codigoretorno;
	
	}
	
	/**
	 * Method consultaEmpresa.
	 * @param cnpj String
	
	 * @return Empresa */
	public Empresa consultaEmpresa(String cnpj) {
		
		//Baby Step
//		Empresa empresa = null;
//		
//		empresa = new Empresa();
//		empresa.setCnpj("60430951000122");
//		empresa.setNomeDaEmpresa("Casas Bahia S/A");
//		empresa.setNomeFantasia("Casa Bahia");
//		empresa.setEndereco("Rua Taquari, 222");
//		empresa.setTelefone("12121212");
//		
//		return empresa;
		
		//Código refatorado
		Empresa empresa = null;
		java.sql.PreparedStatement ps;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn.prepareStatement("select * from empresa where cnpj = ?");
			ps.setString(1, cnpj);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				empresa = new Empresa();
				empresa.setCnpj(resultSet.getString("cnpj"));
				empresa.setNomeDaEmpresa(resultSet.getString("nomeDaEmpresa"));
				empresa.setNomeFantasia(resultSet.getString("nomeFantasia"));
				empresa.setEndereco(resultSet.getString("endereco"));
				empresa.setTelefone(resultSet.getString("telefone"));
			}
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return empresa;
	}
}


