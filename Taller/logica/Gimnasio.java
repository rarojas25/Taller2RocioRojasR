package logica;

import java.util.ArrayList;
/*
 * Clase que representa un Gimnasio Pokemon con su lider, estado actual
 * y la lista de nombres de Pokemon que usa en combate.
 */
public class Gimnasio {
	private int numero;
	private String lider;
	private String estado;
	private int cantPokemons;
	private ArrayList<String> nombresPokemon;
	/*
	 * Constructor del Gimnasio con todos sus datos.
	 */
	public Gimnasio(int numero, String lider, String estado, int cantPokemons, ArrayList<String> nombresPokemon) {
		super();
		this.numero = numero;
		this.lider = lider;
		this.estado = estado;
		this.cantPokemons = cantPokemons;
		this.nombresPokemon = nombresPokemon;
	}
	/*
	 * Indica si este gimnasio ya fue derrrotado por el jugador.
	 */
	public boolean estaDerrotado() {
		return estado.equals("Derrotado");
	}
	/*
	 * Getters y Setters.
	 */
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
