package io.spring.batch.domain;

import java.io.Serializable;

public class Administrateur implements Serializable {

	private static final long serialVersionUID = 655803048359078301L;

	private String login;
	private String password;
	private String codeBqeRIB;
	private String codeGuichetRIB;
	private String numCpteRIB;
	private String cleRIB;
	private String domiciliationRIB;
	private Integer numeroEmetteur;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodeBqeRIB() {
		return this.codeBqeRIB;
	}

	public void setCodeBqeRIB(String codeBqeRIB) {
		this.codeBqeRIB = codeBqeRIB;
	}

	public String getCodeGuichetRIB() {
		return this.codeGuichetRIB;
	}

	public void setCodeGuichetRIB(String codeGuichetRIB) {
		this.codeGuichetRIB = codeGuichetRIB;
	}

	public String getNumCpteRIB() {
		return this.numCpteRIB;
	}

	public void setNumCpteRIB(String numCpteRIB) {
		this.numCpteRIB = numCpteRIB;
	}

	public String getCleRIB() {
		return this.cleRIB;
	}

	public void setCleRIB(String cleRIB) {
		this.cleRIB = cleRIB;
	}

	public String getDomiciliationRIB() {
		return this.domiciliationRIB;
	}

	public void setDomiciliationRIB(String domiciliationRIB) {
		this.domiciliationRIB = domiciliationRIB;
	}

	public Integer getNumeroEmetteur() {
		return this.numeroEmetteur;
	}

	public void setNumeroEmetteur(Integer numeroEmetteur) {
		this.numeroEmetteur = numeroEmetteur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Administrateur other = (Administrateur) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	/*	@Override
	public String toString() {
		return "Administrateur \n [password=" + password + "]";
	}
	*/
	@Override
	public String toString() {
		return "Administrateur \n ["
				+ "login=" + login + ", password=" + password + ", codeBqeRIB=" + codeBqeRIB
				+ ", codeGuichetRIB=" + codeGuichetRIB + ", numCpteRIB=" + numCpteRIB + ", cleRIB=" + cleRIB
				+ ", domiciliationRIB=" + domiciliationRIB + ", numeroEmetteur=" + numeroEmetteur + "]";
	}

}
