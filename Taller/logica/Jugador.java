package logica;

import java.util.ArrayList;

public class Jugador {
	private String apodo;
	private int medallas;
	private ArrayList<Pokemon> pokemones;
	
	public Jugador(String apodo) {
		super();
		this.apodo = apodo;
		this.medallas = 0;
		this.pokemones = new ArrayList<>();
	}
	public ArrayList<Pokemon> getEquipo(){
		ArrayList<Pokemon> equipo = new ArrayList<>();
		for(int i = 0; i < pokemones.size() && i < 6; i++) {
			equipo.add(pokemones.get(i));
		}
		return equipo;
	}
	public boolean tieneEquipoDisponible() {
		for(Pokemon p : getEquipo()) {
			if(p.estaVivo()) 
				return true;
		}
		return false;
	}
	public boolean tienePokemon(String nombre) {
		for(Pokemon p: pokemones) {
			if(p.getNombre().equalsIgnoreCase(nombre)) 
				return true;
		}
		return false;
	}
	public void agregarPokemon(Pokemon pokemon) {
		pokemones.add(pokemon);
	}
	public void curarTodos() {
		for(Pokemon p : pokemones) {
			p.setEstado("Vivo");
		}
	}
	public String getApodo() {
		return apodo;
	}
	public int getMedallas() {
		return medallas;
	}
	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}
	public void setPokemones(ArrayList<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}
	public void setMedallas(int medallas) {
		this.medallas = medallas;
	}
	
	
}
