package logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
/*
 * Clase principal que controla el flujo del juego.
 * Gestiona todos los menus, carga de datos y accidentes disponibles del jugador.
 */
public class Juego {
	private Scanner teclado;
	private GestorArchivos gestor;
	private Combate combate;
	private Random random;
	private Jugador jugador;
	private LinkedList<String> habitats;
	private ArrayList<Pokemon> pokedex;
	private ArrayList<Gimnasio> gimnasios;
	private ArrayList<MiembroAltoMando> altoMando;
	/*
	 * Constructor del juego. Inicializa herramientas y carga todos los archivos de datos.
	 */
	public Juego() {
		teclado = new Scanner(System.in);
		gestor = new GestorArchivos();
		random = new Random();
		
		habitats = gestor.leerHabitats();
		pokedex = gestor.leerPokedex();
		gimnasios = gestor.leerGimnasios();
		altoMando = gestor.leerAltoMando();
		combate = new Combate(teclado, pokedex);
	}
	/*
	 * Inicia el juego mostrando el menu de bienvenida
	 * Permite continuar, nueva partida o salir.
	 */
	public void iniciar() {
		boolean corriendo = true;
		while(corriendo) {
			System.out.println("\n == Bienvenido/a al juego Pokemon ==");
			System.out.println("1) Continuar.");
			System.out.println("2) Nueva Partida.");
			System.out.println("3) Salir.");
			System.out.println("Ingrese Opcion: ");
			
			int opcion = leerEntero();
			switch(opcion) {
				case 1:
					menuContinuar();
					break;
				case 2:
					menuNuevaPartida();
					break;
				case 3:
					System.out.println("Nos vemos entrenador...");
					corriendo = false;
					break;
				default:
					System.out.println("Opcion invalida. Intente de nuevo.");
				}
			}
		teclado.close();
		}
	/*
	 * Carga la partida guardada en Registros,txt.
	 * Si existe y el valida, da la bienvenida y abre el menu del juego.
	 */
	private void menuContinuar() {
		//Se pasa gimnasios para que cargaPartida pueda restaurar los estados
		jugador = gestor.cargarPartida(pokedex, gimnasios);
		if(jugador == null) {
			System.out.println("No se encontro una partida guardada. Inicia una nueva partida.");
			return;
		}
		System.out.println("Bienvenido de vuelta " + jugador.getApodo() + "!!");
		menuJuego();
	}
	/*
	 * Crea una nueva partida solicitando el apodo del jugador.
	 * Reinicia los estados de todos los gimnasios antes de comenzar.
	 */
	private void menuNuevaPartida() {
		System.out.println("Ingrese Apodo: ");
		String apodo = teclado.nextLine().trim();
		jugador = new Jugador(apodo);
		//Reinicia gimnasios para que la nueva partida comience limpia
		for(Gimnasio g : gimnasios) {
			g.setEstado("Sin derrotar");
		}
		System.out.println("Bienvenido " + jugador.getApodo() + "!!");
		menuJuego();
	}
	/*
	 * Menu principal con todas las opciones disponibles para el jugador.
	 */
	private void menuJuego() {
		boolean enJuego = true;
		while(enJuego) {
			System.out.println("\n" + jugador.getApodo() + ", que deseas hacer?");
			System.out.println("");
			System.out.println("1) Revisar equipo.");
			System.out.println("2) Salir a capturar.");
			System.out.println("3) Acceso al PC (Cambiar Pokemon del equipo).");
			System.out.println("4) Retar un gimnasio.");
			System.out.println("5) Desafio al Alto Mando.");
			System.out.println("6) Curar Pokemon.");
			System.out.println("7) Guardar.");
			System.out.println("8) Guardar y Salir.");
			System.out.println("Ingrese Opcion: ");
			
			int opcion = leerEntero();
			switch(opcion) {
				case 1: 
					revisarEquipo(); 
					break;
				case 2:
					salirACapturar();
					break;
				case 3:
					accesoPC();
					break;
				case 4:
					retarGimnasio();
					break;
				case 5: 
					desafioAltoMando();
					break;
				case 6:
					curarPokemon();
					break;
				case 7:
					gestor.guardarPartida(jugador, gimnasios);
					System.out.println("Partida guardada!");
					break;
				case 8:
					gestor.guardarPartida(jugador, gimnasios);
					System.out.println("Nos vemos entrenador...");
					enJuego = false;
					break;
				default:
					System.out.println("Opcion invalida.");
			}
		}
	}
	/*Opcion 1 - Revisar Equipo
	 * Muestra el nombre, tipo, stats totales y estado de cada Pokemon del equipo activo.
	 * El equipo activo son los primeros 6 Pokemon de la lista del jugador.
	 */
	private void revisarEquipo() {
		ArrayList<Pokemon> equipo = jugador.getEquipo();
		if(equipo.isEmpty()) {
			System.out.println("No tienes ningun Pokemon en tu equipo.");
			return;
		}
		System.out.println("\nEquipo Actual: ");
		for(int i = 0; i < equipo.size(); i++) {
			Pokemon p = equipo.get(i);
			System.out.println((i+ 1) + ") " + p.getNombre() + " | " + p.getTipo() + " |Stats totales: " + p.getTotalStats() + " [" + p.getEstado() + "]");		
		}
	}
	/*Opcion 2- Salir a Capturar
	 * Muestra las zonas disponibles y genera un Pokemon aleatorio segun los 
	 * pocentajes de aparicion de esa zona. El jugador puede capturarlo o huir.
	 */
	private void salirACapturar() {
		boolean enZonas = true;
		while(enZonas) {
			System.out.println("\n Donde deseas ir a explorar?");
			System.out.println("\n Zonas disponibles: \n");
			
			int i = 1;
			for(String zona : habitats) {
				System.out.println(i + ") " + zona);
				i++;
			}
			System.out.println(i + ") Volver al menu.");
			System.out.println("Ingrese Zona: ");
			
			int eleccion = leerEntero();
			if(eleccion == habitats.size() + 1) {
				enZonas = false;
				continue;
			}
			if(eleccion < 1 || eleccion > habitats.size()) {
				System.out.println("Zona invalida.");
				continue;
			}
			//Obtener la zona elegida recorriendo la LinkedList
			
			ArrayList<Pokemon> posibles = new ArrayList<>();
			for(Pokemon p : pokedex) {
				if(p.getHabitat().equalsIgnoreCase(habitats.get(eleccion - 1))) {
					posibles.add(p);
				}
			}
			double aleatorio = random.nextDouble();
			double acumulado = 0.0;
			Pokemon aparecido = null;
			
			for(Pokemon p : posibles) {
				acumulado += p.getPorcentajeAparicion();
				if(aleatorio <= acumulado) {
					aparecido = p;
					break;
				}
			}
			if(aparecido != null) {
				System.out.println("\n ¡Un " + aparecido.getNombre() + " salvaje ha aparecido!" );
			if(jugador.tienePokemon(aparecido.getNombre())) {
				System.out.println("Ya tienes a " + aparecido.getNombre() + ". No puedes capturarlo de nuevo." );
			}else {
				jugador.agregarPokemon(new Pokemon(aparecido));
				System.out.println("¡" + aparecido.getNombre() + " capturado con exito!");
			}
			}else {
				System.out.println("No se encontro ningunn Pokemon en esta zona.");
			}
			System.out.println("\n Oh!! Ha aparecido un increible " + aparecido.getNombre() + "!!");
			System.out.println("\n Que deseas hacer?\n");
			System.out.println("1) Capturar");
			System.out.println("2) Huir");
			System.out.println("Ingrese Opcion: ");
			
			int accion = leerEntero();
			if(accion == 1) {
				if(jugador.tienePokemon(aparecido.getNombre())){
					System.out.println("Ya tienes un " + aparecido.getNombre() + "! No puedes capturarlo de nuevo.");
				}else {
					jugador.agregarPokemon(aparecido);
					System.out.println(aparecido.getNombre() + " capturado con exito!!");
					if(jugador.getPokemones().size() <= 6) {
						System.out.println(aparecido.getNombre() + " ha sido agregado a tu equipo!");
					}else {
						System.out.println(aparecido.getNombre() +" ha sido enviado al PC.");
					}
				}
			}else {
				System.out.println("Huiste del combate.");
			}
			enZonas = false;
		}
	}
	
	/*Opcion 3 - Acceso al PC
	 * Muestra todos los Pokemon del jugador numerados e indica cuales pertenecen
	 * al equipo activo (primeros 6) y cuales estan en el PC.
	 * Permite intercambiar dos Pokemon de la lista.
	 */
	private void accesoPC() {
		boolean enPC= true;
		while(enPC) {
			ArrayList<Pokemon> todos = jugador.getPokemones();
			if(todos.isEmpty()) {
				System.out.println("No tienes ningun Pokmeon.");
				return;
			}
			System.out.println("\n -- Acceso al PC --");
			System.out.println("(Los primeros 6 sin tu equipo activo)\n");
			for(int i = 0; i < todos.size(); i++) {
				String etiqueta = (i < 6) ? "[Equipo]" : " [PC]";
				System.out.println((i + 1) + " )" + todos.get(i).getNombre() + " | " + todos.get(i).getTipo() + " | " + todos.get(i).getEstado() + etiqueta); ;
			}
			System.err.println("\n 1) Cambiar Pokemon.");
			System.out.println("2) Salir");
			System.out.println("Ingrese Opcion: ");
			
			int opcion = leerEntero();
			if(opcion == 2) {
				enPC = false;
			}else if(opcion == 1){
				System.out.println("Ingrese el numero del primer Pokemon: ");
				int pos1 = leerEntero() - 1;
				System.out.println("Ingrese el numero del segundo Pokemon: ");
				int pos2 =  leerEntero() - 1;
				
				if(pos1 < 0 || pos1 >= todos.size() || pos2 < 0 || pos2 >= todos.size()) {
					System.out.println("Numeros invalidos.");
				}else if(pos1 == pos2) {
					System.out.println("Debes elegir dos Pokmeones distintos.");
				}else {
					Pokemon temp = todos.get(pos1);
					todos.set(pos1, todos.get(pos2));
					todos.set(pos2, temp);
					System.out.println("Pokemon intercambiados correctamente!");
				}
			}else {
				System.out.println("Opcion invalida.");
			}
		}
	}
	/*Opcion 4 - Retar Gimnasio
	 * Muestra todos los gimnasios con su estado y permite al jugador retar al siguiente 
	 * en orden. No se puede saltar lideres sin haber derrotado al anterior.
	 */
	private void retarGimnasio() {
		boolean enGimnasios = true;
		while(enGimnasios) {
			System.out.println("\n A cual lider deseas retar??");
			System.out.println();
			for(int i = 0; i < gimnasios.size(); i++) {
				Gimnasio g = gimnasios.get(i);
				System.out.println((i + 1) + ") " + g.getLider() + " - Estado: " + g.getEstado());
			}
			System.out.println((gimnasios.size() + 1) + ") Volver al menu.");
			System.out.println("Ingrese Opcion: ");
			
			int eleccion = leerEntero();
			if(eleccion == gimnasios.size() + 1) {
				enGimnasios = false;
				continue;
			}
			if(eleccion < 1 || eleccion > gimnasios.size()) {
				System.out.println("Opcion invalida.");
				continue;
			}
			Gimnasio elegido =  gimnasios.get(eleccion - 1);
			
			//No se puede saltar lideres
			if(eleccion > 1 && !gimnasios.get(eleccion - 2).estaDerrotado()) {
				System.out.println("Calmado Entrenador!!! No puedes retar a " + elegido.getLider() + " sin haber derrotado a los lideres anteriores!!");
				enGimnasios = false;
				continue;
			}
			if(elegido.estaDerrotado()) {
				System.out.println("Ya derrotate a "+ elegido.getLider() + "!");
				enGimnasios = false;
				continue;
			}
			if(!jugador.tieneEquipoDisponible()) {
				System.out.println("No tienes Pokemon disponibles. Cura a tu equipo primero.");
				enGimnasios = false;
				continue;
			}
			combate.retarGimnasio(jugador, elegido);
			enGimnasios = false;
		}
	}
	/*Opcion 5 - Desafio al Alto Mando
	 * Permite desafiar al Alto Mando si el jugador tiene las 8 medallas.
	 * Los combates son consecutivos sin acceso al menu entre ellos.
	 */
	private void desafioAltoMando() {
		if(jugador.getMedallas() < 8) {
			System.out.println("Necesitas derrotar los 8 gimnsios primero!");
			System.out.println("Medallas actuales: " + jugador.getMedallas() + "/8");
			return;
		}
		combate.retarAltoMando(jugador, altoMando);
	}
	/*Opcion 6 - Curar Pokemon
	 * Cura a todos los Pokemon debilitados del jugador, dejandolos listos para cambatir.
	 */
	private void curarPokemon() {
		jugador.curarTodos();
		System.out.println("Tu equipo se ha recuperado!");
	}
	/*Utilidad
	 * Lee un numero entero desde consola con manejo de errores.
	 * Evita que el programa crashee.
	 */
	private int leerEntero() {
		try {
			return Integer.parseInt(teclado.nextLine().trim());
		}catch(NumberFormatException e) {
			return -1;
		}
	}
}


