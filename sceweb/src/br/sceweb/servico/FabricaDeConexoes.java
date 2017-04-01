package br.sceweb.servico;

import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

/**
 * @author Lab103
 * @version $Revision: 1.0 $
 */
public class FabricaDeConexoes {
	/**
	 * Field logger.
	 */
	Logger logger = Logger.getLogger(FabricaDeConexoes.class);
	/**
	 * Method getConnection.
	
	 * @return Connection */
	public Connection getConnection(){
		String url = "jdbc:mysql://localhost/sceweb";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return (Connection) DriverManager.getConnection(url,"root","alunofatec");
		}
		catch (CommunicationsException e){
			logger.info("Exceção de comunicacao com o DB causa: " + e.getMessage());
			
			throw new RuntimeException(e); 
		}
		catch (Exception e){
			logger.info("Exceção geral causa: " + e.getMessage());
			
			throw new RuntimeException(e); 
		}
	}
}
