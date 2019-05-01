package br.com.targettrust.persistencia.model;

public enum Sexo {
	F("FEMININO"),	M("MASCULINO");
	
	private String descricao;
	
	private Sexo(String descricao){
		this.descricao = descricao;
	}
	
	@Override
	public String toString(){
		return descricao;
	}
}
