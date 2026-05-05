package logica;

import java.util.ArrayList;
/*
 * Clase que representa a un miembro del Alto Mando con su nombre
 * y los 6 Pokemon que utiliza en el combate final.
 */
public class MiembroAltoMando {
	private int numero;
	private String nombre;
	private ArrayList<String> nombresPokemon;
	
	/*
	 * Constructor del miembro del Alto Mando.
	 */
	public MiembroAltoMando(int numero, String nombre, ArrayList<String> nombresPokemon) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.nombresPokemon = nombresPokemon;
	}
	/*
	 * Getters.
	 */
	public int getNumero() {
		return numero;
	}
	public String getNombre() {
		return nombre;
	}
	public ArrayList<String> getNombresPokemon() {
		return nombresPokemon;
	}
}
