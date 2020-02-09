package view;

import model.logic.Modelo;

public class View 
{
    /**
     * Metodo constructor
     */
    public View()
    {
    	
    }
    
	public void printMenu()
	{
		System.out.println("Ingrese la opcion que le interesa saber:");
		System.out.println("Opcion1: Mostrar la información básica del primer comparendo y del último comparendo en la lista y el total de comparendos en la lista.");
		System.out.println("Opcion2: Consultar la información básica de un comparendo dado su OBJECTID.");
		System.out.println("Si quiere finaliza ingrese la cadena: Cerrar");
	}

	public void printMessage(String mensaje) 
	{

		System.out.println(mensaje);
	}		
	
	public void printModelo(Modelo modelo)
	{
		System.out.println(modelo);
	}
}
