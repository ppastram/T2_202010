package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.google.gson.Gson;

import Infracciones.Example;
import model.data_structures.*;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	/**
	 * Lista-pila de tipo Comparendos
	 */
	private LinkedStack<Comparendo> datos1;
	
	/**
	 * Lista-cola de tipo Comparendos
	 */
	private LinkedQueue<Comparendo> datos2;
	
	/**
	 * Constructor del modelo del mundo
	 */
	public Modelo()
	{	
		Gson gson = new Gson();
		BufferedReader br = null;
		datos1 = new LinkedStack<>();
		datos2 = new LinkedQueue<>();
		
		try
		{
			br = new BufferedReader(new FileReader("./data/comparendos_dei_2018_small.geojson"));
			Example result = gson.fromJson(br, Example.class);
	
			for(int  i = 0; i < result.getFeatures().size(); i ++)
			{
				int objective = result.getFeatures().get(i).getProperties().getOBJECTID();
				String fecha_hora = result.getFeatures().get(i).getProperties().getFECHAHORA();
				String medio_dete = result.getFeatures().get(i).getProperties().getMEDIODETE();
				String clase_vehi = result.getFeatures().get(i).getProperties().getCLASEVEHI();
				String tipo_servi = result.getFeatures().get(i).getProperties().getTIPOSERVI();
				String infraccion = result.getFeatures().get(i).getProperties().getINFRACCION();
				String des_infrac = result.getFeatures().get(i).getProperties().getDESINFRAC();
				String localidad = result.getFeatures().get(i).getProperties().getLOCALIDAD();
			    double cordenada1 = result.getFeatures().get(i).getGeometry().getCoordinates().get(0);
			    double cordenada2 = result.getFeatures().get(i).getGeometry().getCoordinates().get(1);
			    
			    Comparendo actual = new Comparendo(objective, fecha_hora, medio_dete, clase_vehi, tipo_servi, infraccion, des_infrac, localidad, cordenada1, cordenada2);
			    datos1.push(actual);
			    datos2.enqueue(actual);
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			if(br != null)
			{
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo de la pila
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoPila()
	{
		return datos1.getSize();
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo de la cola
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoCola()
	{
		return datos2.getSize();
	}
	
	/**
	 * Metodo que retorna un string con la informacion basica del comparendo de acuerdo con la posicion de la pila
	 * @param pPosicion Posicion del objeto en la pila
	 * @return Retorna cadena de string con la informacion baica del comparendo
	 */
	public String darDatosPila(int pPosicion)
	{
		String informacion = datos1.seeItem(pPosicion).getObjective() + ", \n" + datos1.seeItem(pPosicion).getFecha_hora() + ", \n" + datos1.seeItem(pPosicion).getClase_vehi() + ", \n" + 
                             datos1.seeItem(pPosicion).getTipo_servi() + ", \n" + datos1.seeItem(pPosicion).getInfraccion() + ", \n" + datos1.seeItem(pPosicion).getDes_infrac() + ", \n" + 
                             datos1.seeItem(pPosicion).getLocalidad();
        return informacion;
	}
	
	/**
	 * Metodo que retorna un string con la informacion basica del comparendo de acuerdo con la posicion de la cola
	 * @param pPosicion Posicion del objeto en la cola
	 * @return Retorna cadena de string con la informacion baica del comparendo
	 */
	public String darDatosCola(int pPosicion)
	{
		String informacion = datos2.seeItem(pPosicion).getObjective() + ", \n" + datos2.seeItem(pPosicion).getFecha_hora() + ", \n" + datos2.seeItem(pPosicion).getClase_vehi() + ", \n" + 
                             datos2.seeItem(pPosicion).getTipo_servi() + ", \n" + datos2.seeItem(pPosicion).getInfraccion() + ", \n" + datos2.seeItem(pPosicion).getDes_infrac() + ", \n" + 
                             datos2.seeItem(pPosicion).getLocalidad();
        return informacion;
	}
	
	public String consultarMasComparendos(String pInfraccion)
	{
//		LinkedQueue<Comparendo> nueva = new LinkedQueue<>();
		String info = "";
		
		Iterator<Comparendo> it = datos2.iterator();
		while(it.hasNext())
		{
			Comparendo elemento = it.next();
			if(elemento.getInfraccion().equals(pInfraccion))
			{
				
			}	
		}
		return info;
	}
}
