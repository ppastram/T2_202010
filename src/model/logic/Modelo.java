package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	 * Lista enlazada de tipo Comparendos
	 */
	private LinkedStack<Comparendo> datos;
	
	/**
	 * Constructor del modelo del mundo
	 */
	public Modelo()
	{	
		Gson gson = new Gson();
		BufferedReader br = null;
		datos = new LinkedStack<>();
		
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
			    datos.push(actual);
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
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.getSize();
	}
	
	/**
	 * Metodo que retorna un string con la informacion basica del comparendo de acuerdo con la posicion
	 * @param pPosicion Posicion del objeto
	 * @return Retorna cadena de string con la informacion baica del comparendo
	 */
	public String darDatosPila(int pPosicion)
	{
		String informacion = datos.seeItem(pPosicion).getObjective() + ", \n" + datos.seeItem(pPosicion).getFecha_hora() + ", \n" + datos.seeItem(pPosicion).getClase_vehi() + ", \n" + 
                             datos.seeItem(pPosicion).getTipo_servi() + ", \n" + datos.seeItem(pPosicion).getInfraccion() + ", \n" + datos.seeItem(pPosicion).getDes_infrac() + ", \n" + 
                             datos.seeItem(pPosicion).getLocalidad();
        return informacion;
	}
}
