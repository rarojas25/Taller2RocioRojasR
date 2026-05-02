package logica;

import java.util.ArrayList;
import java.util.Scanner;

public class Combate {
	private Scanner teclado;
	private ArrayList<Pokemon> pokedex;
		
	
	public Combate(Scanner teclado, ArrayList<Pokemon> pokedex) {
		super();
		this.teclado = teclado;
		this.pokedex = pokedex;
	}
	
	public boolean retarGimnasio(Jugador jugador, Gimnasio gimnasio) {
		System.out.println("/n Desafiando a " + gimnasio.getLider() + "!!");
		
		ArrayList<String> nombresRival = gimnasio.getNombresPokemon();
		
		for(int i = 0; i < nombresRival.size(); i++) {
			Pokemon rival = buscarPokemonRival(nombresRival.get(i));
			if(rival == null) continue;
			
			System.out.println(gimnasio.getLider() + " saca a " + rival.getNombre() + "!");
			
			Pokemon activo = elegirPokemonActivo(jugador);
			if(activo == null) {
				System.out.println("Te has quedado sin pokemones en tu equipo!");
				System.out.println("Volviendo al menu...");
				return false;
			}
			System.out.println(jugador.getApodo() +  " saca a " + activo.getNombre() + "!");
			
			boolean resultado = ejecutarCombate(jugador, activo, rival);
			if(!resultado) {
				if(!jugador.tieneEquipoDisponible()) {
					System.out.println("Te has quedado sin pokemones en tu equipo!");
					System.out.println("Volviendo al menu...");
					return false;
				}
			}else {
				System.out.println(rival.getNombre() + " fue derrotado!");
				if(i < nombresRival.size() - 1) {
					System.out.println(gimnasio.getLider() + " saca a su siguiente Pokemon!");
				}
			}
		}
		System.out.println("/nHas derrotado a " + gimnasio.getLider() + "! Has ganado la medalla!");
		gimnasio.setEstado("Derrotado");
		jugador.setMedallas(jugador.getMedallas() + 1);
		return true;
		}
	
		public boolean retarAltoMando(Jugador jugador, ArrayList<MiembroAltoMando> altoMando) {
			System.out.println("n/ -- DESAFIO AL ALTO MANDO --");
		
			for(MiembroAltoMando miembro : altoMando) {
				System.out.println("n/ Desafiando a " + miembro.getNombre() + "!!");
			
			for(String nombreRival : miembro.getNombresPokemon()) {
				Pokemon rival = buscarPokemonRival(nombreRival);
				if(rival == null)continue;
			
				System.out.println(miembro.getNombre() + " saca a " + rival.getNombre() + "!");
			
				Pokemon activo = elegirPokemonActivo(jugador);
				
				if(activo == null) {
					System.out.println("Te has quedado sin pokemones!");
					System.out.println("Volviendo al menu...");
					return false;
				}
				System.out.println(jugador.getApodo() + " saca a " + activo.getNombre() + "!");
			
				boolean gano = ejecutarCombate(jugador, activo, rival);
				if(!gano) {
					if(!jugador.tieneEquipoDisponible()) {
						System.out.println("Volviendo al menu...");
						return false;
					}
					} else {
						System.out.println(rival.getNombre() + " fue derrotado!");
					}
				}
				System.out.println("Has derrotado a " + miembro.getNombre() + "!");
			}
		
		System.out.println("n/FELICITACIONES! Has derrotado al Alto Mando! Eres el Campeon! ");
		
		return true;
		}
		
		private boolean ejecutarCombate(Jugador jugador, Pokemon activo, Pokemon rival) {
			while(true) {
				System.out.println("/n Que deseas hacer?");
				System.out.println("1) Atacar");
				System.out.println("2) Cambiar de pokemon");
				System.out.println("3) Rendirse");
				System.out.println("Ingrese Opcion: ");
				
				int opcion = leerEntero();
				if(opcion == 1) {
					boolean gano = calcularBatalla(activo, rival);
					if(gano) {
						return true;
					}else {
						activo.setEstado("Debilitado");
						System.out.println(activo.getNombre() + " ha sido debilitado...");
						if(!jugador.tieneEquipoDisponible()) {
							return false;
						}
						activo = elegirPokemonActivo(jugador);
						if(activo == null)return false;
						System.out.println(jugador.getApodo() + " saca a " + activo.getNombre() + "!");
					}
				}else if(opcion == 2) {
					Pokemon nuevo = seleccionarPokemonEquipo(jugador, activo);
					if(nuevo != null) {
						activo = nuevo;
						System.out.println(jugador.getApodo() + " cambia a " + activo.getNombre() + "!");
					}
				}else if(opcion == 3){
					System.out.println("Te has rendido, Volviendo al menu...");
					return false;
				}else {
					System.out.println("Opción invalida.");
				}
			}
		}
		private boolean calcularBatalla(Pokemon jugador, Pokemon rival) {
			double statsJugador = jugador.getTotalStats();
			double statsRival = rival.getTotalStats();
			
			System.out.println(jugador.getNombre() + "-->" + (int)statsJugador + "puntos");
			System.out.println(rival.getNombre() + "-->" + (int)statsRival + "puntos");
			
			double multiplicador = TablaTipos.getEfectividad(jugador.getTipo(), rival.getTipo());
			
			if(multiplicador == 2.0) {
				System.out.println(jugador.getNombre() + " es super efectivo contra " + rival.getNombre() + "!");
			}else if(multiplicador == 0.5) {
				System.out.println(jugador.getNombre() + " no es efectivo contra " + rival.getNombre() + "!");
			}else if(multiplicador == 0.0){
				System.out.println(jugador.getNombre() + " no tiene efecto contra "+ rival.getNombre() + "!");
			}
			statsJugador *= multiplicador;
			
			System.out.println("Nuevo puntaje: ");
			System.out.println(jugador.getNombre() + "-->" + (int)statsJugador + " puntos");
			System.out.println(rival.getNombre() + "-->" + (int)statsRival + " puntos");
			
			if(statsJugador >= statsRival){
				System.out.println("Ha ganado " + jugador.getNombre() + "!");
				return true;
			}else {
				System.out.println("Ha ganado " + rival.getNombre() + "!");
				return false;
		}
	}
	private Pokemon seleccionarPokemonEquipo(Jugador jugador, Pokemon actual) {
		ArrayList<Pokemon> equipo = jugador.getEquipo();
		System.out.println("Elige un Pokemon: ");
		int indice = 1;
		for(Pokemon p : equipo) {
			String disponible;
			if(p.estaVivo()){
				disponible = "";
				
			} else {
				disponible = " [Debilitado]";
			}
			System.out.println(indice + ") " + p.getNombre() + disponible);
			indice++;
		}
		int eleccion = leerEntero();
		if(eleccion < 1 || eleccion > equipo.size()) {
			System.out.println("Eleccion invalida.");
			return null;
		}
		Pokemon elegido = equipo.get(eleccion - 1);
		if(!elegido.estaVivo()) {
			System.out.println(elegido.getNombre() + " esta debilitado y no puede combatir.");
			return null;
		}
		if(elegido == actual) {
			System.out.println("Ese Pokemon ya esta en combate.");
			return null;
			}
		return elegido;
		}
		private Pokemon elegirPokemonActivo(Jugador jugador) {
			for(Pokemon p : jugador.getEquipo()) {
				if(p.estaVivo())return p;
			}
			return null;
		}
		private Pokemon buscarPokemonRival(String nombre) {
			for(Pokemon p : pokedex) {
				if(p.getNombre().equalsIgnoreCase(nombre)) {
					return new Pokemon(p);
				}
			}
			return new Pokemon(nombre, "none", 0, 45, 45, 40, 35, 35, 45, "Normal");
		}
		private int leerEntero() {
			try {
				return Integer.parseInt(teclado.nextLine().trim());
			}catch(NumberFormatException e) {
				return -1;
			}
		}
	}