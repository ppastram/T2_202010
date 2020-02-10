package controller;

import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller 
{

	/**
	 * Instancia del Modelo
	 */
	private Modelo modelo;

	/**
	 * Instancia de la Vista
	 */
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	/**
	 * Metodo run
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		
		while( !fin )
		{
			view.printMenu();
     		String opcion = lector.next();
			
			switch(opcion)
			{
			   case "Opcion1":
				   int tamanio1 = modelo.darTamanoPila();
				   int tamanio2 = modelo.darTamanoCola();
				   view.printMessage("El numero total de comparendos es para la pila: " + tamanio1 + " \n");    // Imprime el tamanio de la pila
				   view.printMessage("El numero total de comparendos es para la cola: " + tamanio2 + " \n");    // Imprime el tamanio de la cola
				   view.printMessage("El primer comparendo de la pila es: "); 
				   view.printMessage(modelo.darDatosPila(0) + " \n");                             // Imprime el elemento en la cabeza de la pila
				   view.printMessage("El primer comparendo de la cola es: "); 
				   view.printMessage(modelo.darDatosCola(0) + " \n");
				   break;
				   
			   case "Opcion2": 
				   view.printMessage("Ingrese la infraccion a consultar en la cola: ");
				   String entrada = lector.next();
				   view.printMessage(modelo.consultarMasComparendos(entrada) + " \n");
				   break;
				   
			   case "Cerrar":
				   view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				   lector.close();
				   fin = true;
				   break;

		       default: 
				   view.printMessage("--------- \n Opcion Invalida !! \n---------");
				   break;
		    }
		}
	}	
}
