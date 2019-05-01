package br.com.targettrust.persistencia.model;

import java.io.Serializable;

//UsuarioPK não é ENTITY e sim JavaBean

public class UsuarioPK implements Serializable {

	private String nome;
	private String rg;
	
	public UsuarioPK() {//contructor no-args
		super();
	}
	
	public UsuarioPK(String nome,String rg){
		setNome(nome);
		setRg(rg);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public int hashCode() {
		
		int hash = 0;		
		hash += nome.hashCode();
		hash += rg.hashCode();		
		return hash;
	}
	
	@Override
	public boolean equals(Object obj){
		UsuarioPK usuario = (UsuarioPK) obj;
		if(!usuario.getNome().equals(this.getNome())){
			return false;
		}
		
		if(!usuario.getRg().equals(this.getRg())){
			return false;
		}
		return true;
	}

	
	
}
