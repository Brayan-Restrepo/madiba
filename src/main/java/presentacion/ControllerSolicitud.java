package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
public class ControllerSolicitud {

	//Permite validar que los datos solo se pinten la primera vez que se carga la pagina y no todas la veces
	private static boolean inicio = true;

	//Contiene la lista de solicitudes con datos quemados
	public static List<ModelSolicitud> listaSolicitud = new ArrayList<ModelSolicitud>();


	/**
	 * Constructor: arma la lista de solicitudes solo la primera vez
	 */
	public ControllerSolicitud(){

		if(ControllerSolicitud.inicio){
			ControllerSolicitud.inicio = false;

			//Arma la lista de conciliadores
			String[] convocante3 = {"Luis Alberto Rodriguez"};
			String[] convocado3 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					1,"24/12/2016",convocante3,convocado3,"87655543","Breve descripcion...","Liquidar","Solicitud","23122016006"));
			
			String[] convocante4 = {"Luis Alberto Rodriguez"};
			String[] convocado4 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					2,"17/12/2016",convocante4,convocado4,"","Breve descripcion...","Liquidar","Reparto","23122016005"));

			String[] convocante = {"Luis Alberto Rodriguez","Luis Alberto Alberto Rodriguez","Luis Alberto Rodriguez"};
			String[] convocado = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					3,"13/12/2016",convocante,convocado,"","Breve descripcion...","Radicar","Reparto","23122016004"));
			
			String[] convocante2 = {"Luis Alberto Rodriguez"};
			String[] convocado2 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					4,"08/12/2016",convocante2,convocado2,"2345673409","Breve descripcion...","Radicar","Solicitud","23122016003"));
						
			String[] convocante5 = {"Luis Alberto Rodriguez","Luis Alberto Rodriguez","Luis Alberto Rodriguez"};
			String[] convocado5 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					5,"03/12/2016",convocante5,convocado5,"","Breve descripcion...","Radicar","Reparto","23122016002"));
			
			String[] convocante6 = {"Luis Alberto Rodriguez"};
			String[] convocado6 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					6,"01/12/2016",convocante6,convocado6,"","Breve descripcion...","Audiencia","Reparto","23122016001"));
			
		}

	}

	public List<ModelSolicitud> getListaSolicitud() {
		return ControllerSolicitud.listaSolicitud;
	}

	/**
	 * Filtra las solicitudes por su estado
	 * @param estado
	 * @return
	 */
	public List<ModelSolicitud> solicitudesPorEstado(String estado){
		List<ModelSolicitud> listaPorEstado = new ArrayList<ModelSolicitud>();
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equals(estado)){
				listaPorEstado.add(ControllerSolicitud.listaSolicitud.get(i));
			}
		}
		return listaPorEstado;
	}

	/**
	 * Cambia el estado de la solicitud despues de ser gestionada
	 * @param evento
	 * @param id
	 */
	public void cambiarEstado(AjaxBehaviorEvent evento, int id){
		String[] estados = {"Liquidar","Radicar","Audiencia","Registrar","Finalizado"};
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getId() == id){
				String estadoActual = ControllerSolicitud.listaSolicitud.get(i).getEstado();
				String nuevoEstado = "";
				for(int j=0; j<estados.length; j++){
					if(estados[j].equals(estadoActual)){
						nuevoEstado = estados[j+1];
					}
				}
				ControllerSolicitud.listaSolicitud.get(i).setEstado(nuevoEstado);
				//Designa el conciliador y actualiza la lista
				if(estadoActual.equals("Radicar")){
					ControllerConciliador conciliador = new ControllerConciliador();
					conciliador.designarConciliador(ControllerSolicitud.listaSolicitud.get(i).getConciliador());
				}
			}
		}
	}

	//Define el color de la ficha segun su estado
	public String colorFicha(int id, String tipoComponente){
		String[][] matrizColores = {{"Liquidar","info"}, //Azul claro
							  {"Radicar","primary"}, //Azul rey
							  {"Audiencia","warning"}, //Naranja
							  {"Registrar","success"}, //Verde
							  {"Finalizado","default"}}; //Gris
		String color = "";
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getId() == id){
				String estadoActual = ControllerSolicitud.listaSolicitud.get(i).getEstado();
				for(int j=0; j<matrizColores.length; j++){
					if(matrizColores[j][0].equals(estadoActual)){
						color = tipoComponente+"-"+matrizColores[j][1];
					}
				}
			}
		}
		return color;
	}

	/**
	 * Retorna la solicitud siguiente del mismo estado
	 * de acuerdo al parametro id.
	 *
	 * @param id Es el id de la Ficha Actual
	 * @return La siguiente Solicitud con el mismo estado de id
	 */
	public ModelSolicitud siguienteFicha(int id){
		return ControllerSolicitud.listaSolicitud.get(id+1);
	}

	/**
	 * Busca si hay mas fichas con el mismo estado
	 * @param Se envia el estado por el cual se desea buscar
	 * @return Retorna falso si no hay mas, true si encontro otra
	 */
	public boolean masFichas(int id, String estado){
		boolean mas = false;
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=id; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equalsIgnoreCase(estado)){
				mas = true;
			}
		}
		return mas;
	}
	
	/**
	 * Busca la siguiente solicitud que halla con el mismo estado
	 * @param Se envia el id de la ficha actual
	 * @return Retorna la siguiente solicitud con el mismo estado
	 */
	public ModelSolicitud siguienteFichaEstado(int id, String estado){
		ModelSolicitud solicitud = null;
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=id; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equals(estado) && ControllerSolicitud.listaSolicitud.get(i).getId()!=id){
				solicitud = ControllerSolicitud.listaSolicitud.get(i);
				break;
			}	
		}
		return solicitud;
	}
}
