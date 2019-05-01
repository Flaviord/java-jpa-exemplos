package br.com.targettrust.persistencia.model;

public enum Perfil {
   S("STANDART"), E("ESPECIAL"),V("VIP");
   
   private String descricao;
   
   private Perfil(String descricao){
	   this.descricao = descricao;
   }
   
   public String toString(){
	   return this.descricao;
   }
}
