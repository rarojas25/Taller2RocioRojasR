package logica;

import java.util.ArrayList;

public class MiembroAltoMando {
	private int numero;
	private String nombre;
	private ArrayList<String> nombresPokemon;
	public MiembroAltoMando(int numero, String nombre, ArrayList<String> nombresPokemon) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.nombresPokemon = nombresPokemon;
	}
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
