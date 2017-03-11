package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ControllerDesarrolloAudiencia {
	
	public ControllerDesarrolloAudiencia(){
		
	}
	
	private Long id;
	
	public String SendId(String pagina, Long id){
		setId(id);
		return pagina+"?faces-redirect=true&includeViewParams=true";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
