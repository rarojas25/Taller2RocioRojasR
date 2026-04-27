package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class GestorArchivos {
	private static final String RUTA_REGISTROS = "Registros.txt";
	private static final String RUTA_HABITATS = "Habitats.txt";
	private static final String RUTA_POKEDEX = "Pokedex.txt";
	private static final String RUTA_GIMNASIOS = "Gimnasios.txt";
	private static final String RUTA_ALTO_MANDO = "Alto Mando.txt";
	
	public LinkedList<String> leerHabitats(){
		LinkedList<String> habitats = new LinkedList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(RUTA_HABITATS))){
			String linea;
			while((linea = br.readLine()) != null) {
				linea = linea.trim();
				if(!linea.isEmpty()) {
					habitats.add(linea);
				}
			}
		}catch(IOException e) {
			System.out.println("Error sl leer Habitats.txt: "+ e.getMessage());
		}
		return habitats;
	}
	public ArrayList<Pokemon> leerPokedex(){
		ArrayList<Pokemon> pokedex = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(RUTA_POKEDEX))){
			String linea;
			while((linea = br.readLine()) != null) {
				linea = linea.trim();
				if(linea.isEmpty())continue;
				String[] partes = linea.split(";");
				if(partes.length < 10)continue;
				
				String nombre = partes[0].trim();
				String habitat = partes[1].trim();
				double porcentaje = Double.parseDouble(partes[2].trim());
				int vida = Integer.parseInt(partes[3].trim());
				int ataque = Integer.parseInt(partes[4].trim());
				int defensa = Integer.parseInt(partes[5].trim());
				int ataqueEspecial = Integer.parseInt(partes[6].trim());
				int defensaEspecial = Integer.parseInt(partes[7].trim());
				int velocidad = Integer.parseInt(partes[8].trim());
				String tipo = partes[9].trim();
				
				pokedex.add(new Pokemon(nombre, habitat, porcentaje, vida, ataque, defensa,ataqueEspecial, defensaEspecial, velocidad, tipo));
				
			}
		}catch(IOException e) {
			System.out.println("Error al leer Pokedex.txt: " + e.getMessage());
		}
		return pokedex;
	}
	public ArrayList<Gimnasio> leerGimnasios(){
		ArrayList<Gimnasio> gimnasios = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(RUTA_GIMNASIOS))){
			String linea;
			while((linea = br.readLine())!= null) {
				linea = linea.trim();
				if(linea.isEmpty()) continue;
				String[] partes = linea.split(";");
				if(partes.length < 5)continue;
				
				int numero = Integer.parseInt(partes[0].trim());
				String lider = partes[1].trim();
				String estado = partes[2].trim();
				int cant = Integer.parseInt(partes[3].trim());
				
				ArrayList<String> pokemones = new ArrayList<>();
				for(int i = 4; i < 4 + cant && i < partes.length; i++) {
					pokemones.add(partes[i].trim());
				}
				gimnasios.add(new Gimnasio(numero, lider, estado, cant, pokemones));
				}
			}catch(IOException e) {
				System.out.println("Error al leer Gimnasios.txt: " + e.getMessage());
		}
		return gimnasios;
	}
	
	public ArrayList<MiembroAltoMando> leerAltoMando(){
		ArrayList<MiembroAltoMando> altoMando = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(RUTA_ALTO_MANDO))){
			String linea;
			while((linea = br.readLine())!= null) {
				linea = linea.trim();
				
				if(linea.isEmpty())continue;
				String[]partes = linea.split(";");
				if(partes.length < 8)continue;
		
				int numero = Integer.parseInt(partes[0].trim());
				String nombre = partes[1].trim();
				
				ArrayList<String> pokemones = new ArrayList<>();
				for(int i = 2; i < partes.length; i++) {
					pokemones.add(partes[i].trim());
				}
				altoMando.add(new MiembroAltoMando(numero, nombre, pokemones));
				}
		}catch(IOException e) {
			System.out.println("Error al leer Alto Mando.txt: " +  e.getMessage());
		}
		return altoMando;
	}
	public Jugador cargarPartida(ArrayList<Pokemon> pokedex, ArrayList<Gimnasio> gimnasios) {
		Jugador jugador = null;
		try(BufferedReader br = new BufferedReader(new FileReader(RUTA_REGISTROS))){
			String primeraLinea = br.readLine();
			
			if(primeraLinea == null || primeraLinea.trim().isEmpty()) return null;
			
			String[] datosJugador = primeraLinea.trim().split(";");
			if(datosJugador.length < 1)return null;
			
			String apodo = datosJugador[0].trim();
			
			if(apodo.isEmpty() || apodo.equalsIgnoreCase("Jugador"))return null;
			
			jugador = new Jugador(apodo);
			
			if(datosJugador.length > 1) {
				String ultimoLider = datosJugador[1].trim();
				if(!ultimoLider.equalsIgnoreCase("none")) {
					int medallas = 0;
					for(Gimnasio g : gimnasios) {
						g.setEstado("Derrotado");
						medallas++;
						
						if(g.getLider().equalsIgnoreCase(ultimoLider)) break;
					}
					jugador.setMedallas(medallas);
				}
			}
			String linea;
			while((linea = br.readLine()) != null) {
				linea = linea.trim();
				if(linea.isEmpty()) continue;
				String[] partesPoke = linea.split(";");
				if(partesPoke.length < 2) continue;
				
				String nombrePoke = partesPoke[0].trim();
				String estadoPoke = partesPoke[1].trim();
				
				Pokemon encontrado = buscarEnPokedex(pokedex, nombrePoke);
				if(encontrado != null) {
					Pokemon copia = new Pokemon(encontrado);
					copia.setEstado(estadoPoke);
					jugador.agregarPokemon(copia);
					
				}
			}
		}catch(IOException e) {
			System.out.println("No se encontro partida guardada");
			return null;
		}
		return jugador;
	}
	public void guardarPartida(Jugador jugador, ArrayList<Gimnasio> gimnasios) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_REGISTROS))){
			String ultimoLider = "none";
			
			for(Gimnasio g : gimnasios) {
					if(g.estaDerrotado()) {
						ultimoLider = g.getLider();
					}
			}
			bw.write(jugador.getApodo() + ";" + ultimoLider);
			bw.newLine();
			
			for(Pokemon p : jugador.getPokemones()) {
				bw.write(p.getNombre() + ";" + p.getEstado());
				bw.newLine();
			}
		}catch(IOException e) {
			System.out.println("Error al guardar la partida: " + e.getMessage());
		}
	}
	
	public Pokemon buscarEnPokedex(ArrayList<Pokemon> pokedex, String nombre) {
		for(Pokemon p : pokedex) {
			if(p.getNombre().equalsIgnoreCase(nombre))return p;
		}
		return null;
	}
}
