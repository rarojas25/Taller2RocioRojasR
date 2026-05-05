# Taller 2 POO - El camino para ser el mejor

## Autor
- Nombre: Rocio Azucena Rojas Robledo
- RUT: 21.694.049-0
- GitHub: [rarojas25](https://github.com/rarojas25/Taller2RocioRojasR)

## Descripción
Este programa es una simulación de las mecánicas base de Pokémon desarrollada en Java bajo el paradigma de Programación Orientada a Objetos. El sistema permite la exploración de diversos hábitats, la captura de criaturas mediante un sistema de *probabilidades acumuladas*, y la gestión de un equipo de 6 Pokémon (PC para el resto). 
El núcleo del proyecto incluye un motor de combate estratégico que utiliza una *matriz de efectividad de tipos* para calcular daños, además de un sistema de progresión que requiere derrotar a los 8 líderes de gimnasio para habilitar el desafío final contra el *Alto Mando*.

## Estructura del Código
El proyecto se organiza en el paquete logica con las siguientes clases y responsabilidades:
* *Main*: Punto de entrada que inicia la ejecución del programa.
* *Juego*: Clase motor; gestiona los menús, la lógica de exploración de zonas y la captura de Pokémon salvajes.
* *Jugador*: Administra los datos del usuario, su equipo activo, los Pokémon en el PC y el contador de medallas.
* *Pokemon*: Define los atributos base (vida, ataque, defensa, tipo, hábitat) y gestiona el estado de salud.
* *Combate*: Ejecuta la lógica de las batallas, integrando las estadísticas del Pokémon con la efectividad de tipos.
* *TablaTipos*: Contiene la matriz de 18x18 con los multiplicadores de daño según afinidad elemental.
* *GestorArchivos*: Administra la persistencia de datos, leyendo pokedex.txt y gimnasios.txt, y guardando el progreso en Registros.txt.
* *Gimnasio / MiembroAltoMando*: Clases que representan a los oponentes y sus respectivos equipos Pokémon.

## Instrucciones de Uso
1.  Asegurarse de que los archivos .txt de datos estén en la raíz del proyecto.
2.  Compilar y ejecutar la clase Main.java.
3.  Utilizar el menú principal para explorar zonas o desafiar gimnasios.
4.  Derrotar a los 8 líderes de gimnasio para desbloquear la liga del Alto Mando.


*

