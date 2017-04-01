package br.sceweb.modelo;

import java.util.InputMismatchException;
/**
 * mantem as informações das empresas cadastras para oferecer convenio
 * @author esadv6
 *
 * @version $Revision: 1.0 $
 */
public class Empresa {
	/**
	 * Field cnpj.
	 */
	String cnpj;
	/**
	 * Field nomeDaEmpresa.
	 */
	String nomeDaEmpresa;
	/**
	 * Field nomeFantasia.
	 */
	String nomeFantasia;
	/**
	 * Field endereco.
	 */
	String endereco;
	/**
	 * Field telefone.
	 */
	String telefone;
	
	/*
	 * Obtem cnpj
	 */
	/**
	 * Method getCnpj.
	 * @return String
	 */
	public String getCnpj() {
		return cnpj;
	}
	/*
	 * atribui o cnpj vefica se o cnpj é valido
	 */
	/**
	 * Method setCnpj.
	 * @param cnpj String
	 */
	public void setCnpj(String cnpj)  {
		if (isValido(cnpj)){
			this.cnpj = cnpj;
		}
		else
			throw new IllegalArgumentException("CNPJ inválido!");
	}
	
	/**
	 * Method getNomeDaEmpresa.
	 * @return String
	 */
	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}
	/**
	 * Method setNomeDaEmpresa.
	 * @param nomeDaEmpresa String
	 */
	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		if (nomeDaEmpresa.equals("")){
			this.nomeDaEmpresa = nomeDaEmpresa;
			throw new IllegalArgumentException("nome da empresa inválido!");
		}
		else
			this.nomeDaEmpresa = nomeDaEmpresa;
		
	}
	/**
	 * Method getNomeFantasia.
	 * @return String
	 */
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	/**
	 * Method setNomeFantasia.
	 * @param nomeFantasia String
	 */
	public void setNomeFantasia(String nomeFantasia) {
		if (nomeFantasia.equals("")){
			this.nomeFantasia = nomeFantasia;
			throw new IllegalArgumentException("nome fantasia inválido!");
		}
		else
			this.nomeFantasia = nomeFantasia;
		
	}
	
	/**
	 * Method getEndereco.
	 * @return String
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * Method setEndereco.
	 * @param endereco String
	 */
	public void setEndereco(String endereco) {
		if (endereco.equals("")){
			this.endereco = endereco;
			throw new IllegalArgumentException("endereco invalido");
		}
		else
			this.endereco = endereco;
		
	}

	
	/**
	 * Method getTelefone.
	 * @return String
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * Method setTelefone.
	 * @param telefone String
	 */
	public void setTelefone(String telefone) {
		if (telefone.equals("")){
			this.telefone = telefone;
			throw new IllegalArgumentException("telefone invalido");
		}
		else
			this.telefone = telefone;
		
	}
	
	/*
	 * valida o cnpj
	 */
	/**
	 * Method isValido.
	 * @param cnpj String
	 * @return boolean
	 */
	public boolean isValido(String cnpj) {
		char dig13, dig14; 
		int sm, i, r, num, peso;
		if (cnpj.equals("00000000000000") || 
				cnpj.equals("11111111111111") || 
				cnpj.equals("22222222222222") || 
				cnpj.equals("33333333333333") || 
				cnpj.equals("44444444444444") || 
				cnpj.equals("55555555555555") ||
				cnpj.equals("66666666666666") || 
				cnpj.equals("77777777777777") || 
				cnpj.equals("88888888888888") || 
				cnpj.equals("99999999999999") || 
				(cnpj.length() != 14)) {
			return(false); 
		}
		// "try" - protege o código para eventuais erros de conversao de tipo (int) 
		try { // Calculo do 1o. Digito Verificador 
			sm = 0; 
			peso = 2; 
			for (i=11; i>=0; i--) { 
				// converte o i-ésimo caractere do CNPJ em um número: 
				// por exemplo, transforma o caractere '0' no inteiro 0 
				// (48 eh a posição de '0' na tabela ASCII) 
				num = (int)(cnpj.charAt(i) - 48); 
				sm = sm + (num * peso); 
				peso = peso + 1; if (peso == 10) 
					peso = 2; 
			} 
			r = sm % 11; 
			if ((r == 0) || (r == 1)) 
				dig13 = '0'; 
			else 
				dig13 = (char)((11-r) + 48);
		
		// Calculo do 2o. Digito Verificador 
		sm = 0; peso = 2; 
		for (i=12; i>=0; i--) { 
			num = (int)(cnpj.charAt(i)- 48); 
			sm = sm + (num * peso); peso = peso + 1; 
			if (peso == 10) peso = 2; 
			} 
		r = sm % 11; 
		if ((r == 0) || (r == 1)) dig14 = '0';
		else dig14 = (char)((11-r) + 48); 
		// Verifica se os dígitos calculados conferem com os dígitos informados. 
		if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) 
			return(true); else return(false);
		}
		
		catch (InputMismatchException erro) {
			erro.printStackTrace();
	        return(false);
	    }
	}
	
	
	/**
	 * Method equals.
	 * @param obj Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null) {
				return false;
			}
		} else if (!cnpj.equals(other.cnpj)) {
			return false;
		}
		if (endereco == null) {
			if (other.endereco != null) {
				return false;
			}
		} else if (!endereco.equals(other.endereco)) {
			return false;
		}
		if (nomeDaEmpresa == null) {
			if (other.nomeDaEmpresa != null) {
				return false;
			}
		} else if (!nomeDaEmpresa.equals(other.nomeDaEmpresa)) {
			return false;
		}
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null) {
				return false;
			}
		} else if (!nomeFantasia.equals(other.nomeFantasia)) {
			return false;
		}
		if (telefone == null) {
			if (other.telefone != null) {
				return false;
			}
		} else if (!telefone.equals(other.telefone)) {
			return false;
		}
		return true;
	}

}
