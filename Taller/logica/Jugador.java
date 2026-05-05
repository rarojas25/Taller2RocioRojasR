package logica;

import java.util.ArrayList;
/*
 * Clase que representa al jugador con su equipo con su apodo, medallas obtenidas
 * y la lista completa de Pokemon capturados(equipo + PC).
 */
public class Jugador {
	private String apodo;
	private int medallas;
	private ArrayList<Pokemon> pokemones;
	/*
	 * Constructor para crear un jugador nuevo sin Pokemon ni medallas.
	 */
	public Jugador(String apodo) {
		super();
		this.apodo = apodo;
		this.medallas = 0;
		this.pokemones = new ArrayList<>();
	}
	/*
	 * Retorna los primeros 6 Pokemon de la lista como equipo de batalla.
	 * Si el jugador tiene menos de 6, retorna todos.
	 */
	public ArrayList<Pokemon> getEquipo(){
		ArrayList<Pokemon> equipo = new ArrayList<>();
		for(int i = 0; i < pokemones.size() && i < 6; i++) {
			equipo.add(pokemones.get(i));
		}
		return equipo;
	}
	/*
	 * Verifica si el jugador tiene al menos un Pokemon vivo en su equipo(primeros 6).
	 */
	public boolean tieneEquipoDisponible() {
		for(Pokemon p : getEquipo()) {
			if(p.estaVivo()) 
				return true;
		}
		return false;
	}
	/*
	 * Verifica si el jugador ya posee un Pokemon con el nombre indicado.
	 * Evita capturas duplicadas.
	 */
	public boolean tienePokemon(String nombre) {
		for(Pokemon p: pokemones) {
			if(p.getNombre().equalsIgnoreCase(nombre)) 
				return true;
		}
		return false;
	}
	/*
	 * Agrega un Pokemon capturando al final de la lista del jugador.
	 */
	public void agregarPokemon(Pokemon pokemon) {
		pokemones.add(pokemon);
	}
	/*
	 * Cura todos los Pokemon debilitados del jugador, restaurando su estado a "Vivo".
	 */
	public void curarTodos() {
		for(Pokemon p : pokemones) {
			p.setEstado("Vivo");
		}
	}
	/*
	 * Getters y Setters.
	 */
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
