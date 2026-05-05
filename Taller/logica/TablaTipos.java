package logica;
/*
 * Clase utilitaria que contiene la tabla de efectividad de tipos Pokemon.
 * Filas: tipo del Pokemon del jugador (atacante).
 * Columnas: tipo del Pokemon rival (defensor).
 * retorna un multiplicador: 2.0 (super efectivo), 1.0 (nomarl), 0.5 (poco eficaz), 0.0 (sin efecto).
 */
public class TablaTipos {
	//Nombres de los tipos en el mismo orden que la matriz
	public static final String[] TIPOS = {
			"Normal",//0
			"Fuego",//1
			"Agua",//2
			"Planta",//3
			"Electronico",//4
			"Hielo",//5
			"Lucha",//6
			"Veneno",//7
			"Tierra",//8
			"Volador",//9
			"Psiquico",//10
			"Bicho",//11
			"Roca",//12
			"Fantasma",//13
			"Dragon",//14
			"Acero",//15
			"Siniestro",//16
			"Hada",//17
			
	};
    // Matriz de efectividad [atacante][defensor]
    private static final double[][] EFECTIVIDAD = {
        // NOR  FUE  AGU  PLA  ELE  HIE  LUC  VEN  TIE  VOL  PSI  BIC  ROC  FAN  DRA  ACE  SIN  HAD
        {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 0.5, 1.0, 1.0 }, // NORMAL
        {  1.0, 0.5, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0 }, // FUEGO
        {  1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0 }, // AGUA
        {  1.0, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 0.5, 1.0, 1.0 }, // PLANTA
        {  1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0 }, // ELECTRICO
        {  1.0, 0.5, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0 }, // HIELO
        {  2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0, 2.0, 2.0, 0.5 }, // LUCHA
        {  1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.0, 1.0, 2.0 }, // VENENO
        {  1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0 }, // TIERRA
        {  1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 0.5, 1.0, 1.0 }, // VOLADOR
        {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0 }, // PSIQUICO
        {  1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 0.5, 2.0, 0.5 }, // BICHO
        {  1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0 }, // ROCA
        {  0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0 }, // FANTASMA
        {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.0 }, // DRAGON
        {  1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0 }, // ACERO
        {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5 }, // SINIESTRO
        {  1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 2.0, 1.0 }  // HADA
    };
    /*
     * Obtiene el multiplicador de efectividad al atacar.
     * Basado en el tipo del atacante (jugador) vs el tipo del defensor (rival)
     */
    public static double getEfectividad(String tipoAtacante, String tipoDefensor) {
    	int filaAtacante = getIndiceTipo(tipoAtacante);
    	int columnaDefensor = getIndiceTipo(tipoDefensor);
    	return EFECTIVIDAD[filaAtacante][columnaDefensor];
    }
    /*
     * Busca el indice de un tipo en el arreglo de tipos.
     * Si no se encuentra, retorna 0 (Normal) como tipo por defecto.
     */
    private static int getIndiceTipo(String tipo) {
    	for(int i = 0; i < TIPOS.length; i++) {
    		if(TIPOS[i].equalsIgnoreCase(tipo)) {
    			return i;
    		}
    	}
		return 0;//Normal por defecto
    }
    public static double obtenerEfectividad(String tipoAtacante, String tipoDefensor) {
    	int fila = -1;
    	int col = -1;
    	for(int i = 0; i < TIPOS.length; i++) {
    		if(TIPOS[i].equalsIgnoreCase(tipoAtacante)) fila = i;
    			if(TIPOS[i].equalsIgnoreCase(tipoDefensor)) col = i;
    		}
    	if(fila != -1 && col != -1) {
    		return EFECTIVIDAD[fila][col];
    	}
    	return 1.0;
    }
    
}

