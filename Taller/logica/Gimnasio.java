package logica;

import java.util.ArrayList;

public class Gimnasio {
	private int numero;
	private String lider;
	private String estado;
	private int cantPokemons;
	private ArrayList<String> nombresPokemon;
	
	public Gimnasio(int numero, String lider, String estado, int cantPokemons, ArrayList<String> nombresPokemon) {
		super();
		this.numero = numero;
		this.lider = lider;
		this.estado = estado;
		this.cantPokemons = cantPokemons;
		this.nombresPokemon = nombresPokemon;
	}
	public boolean estaDerrotado() {
		return estado.equals("Derrotado");
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getNumero() {
		return numero;
	}
	public String getLider() {
		return lider;
	}
	public int getCantPokemons() {
		return cantPokemons;
	}
	public ArrayList<String> getNombresPokemon() {
		return nombresPokemon;
	}
	

}
