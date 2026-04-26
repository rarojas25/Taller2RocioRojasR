package logica;

public class Pokemon {
	private String nombre;
	private String habitad;
	private double porcentajeAparicion;
	private int vida;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo;
	private String estado;
	
	
	public Pokemon(String nombre, String habitad, double porcentajeAparicion, int vida, int ataque, int defensa,
			int ataqueEspecial, int defensaEspecial, int velocidad, String tipo, String estado) {
		super();
		this.nombre = nombre;
		this.habitad = habitad;
		this.porcentajeAparicion = porcentajeAparicion;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipo = tipo;
		this.estado = "Vivo";
	}


	public Pokemon(Pokemon otro) {
		super();
		this.nombre = otro.nombre;
		this.habitad = otro.habitad;
		this.porcentajeAparicion = otro.porcentajeAparicion;
		this.vida = otro.vida;
		this.ataque = otro.ataque;
		this.defensa = otro.defensa;
		this.ataqueEspecial = otro.ataqueEspecial;
		this.defensaEspecial = otro.defensaEspecial;
		this.velocidad = otro.velocidad;
		this.tipo = otro.tipo;
		this.estado = otro.estado;
	}

	public int getTotalStats() {
		return vida + ataque + defensa + ataqueEspecial + defensaEspecial + velocidad;
	}
	public boolean estaVivo() {
		return estado.equals("Vivo");
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getHabitad() {
		return habitad;
	}

	public double getPorcentajeAparicion() {
		return porcentajeAparicion;
	}

	public String getTipo() {
		return tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return nombre + "|" + tipo + "|Stats totales: " + getTotalStats();
	}

}
	