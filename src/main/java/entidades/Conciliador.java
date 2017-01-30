package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Conciliador" database table.
 * 
 */
@Entity
@Table(name="\"Conciliador\"")
@NamedQuery(name="Conciliador.findAll", query="SELECT c FROM Conciliador c")
public class Conciliador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_conciliador")
	private Integer idConciliador;

	private String apellidos;

	private String correo;

	private String egresado;

	private Integer experiencia;

	private String foto;

	private String nombres;

	private String telefono;

	@Column(name="tipo_id")
	private String tipoId;

	//bi-directional many-to-one association to Reparto
	@OneToMany(mappedBy="conciliador")
	private List<Reparto> repartos;

	public Conciliador() {
	}

	public Integer getIdConciliador() {
		return this.idConciliador;
	}

	public void setIdConciliador(Integer idConciliador) {
		this.idConciliador = idConciliador;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEgresado() {
		return this.egresado;
	}

	public void setEgresado(String egresado) {
		this.egresado = egresado;
	}

	public Integer getExperiencia() {
		return this.experiencia;
	}

	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoId() {
		return this.tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public List<Reparto> getRepartos() {
		return this.repartos;
	}

	public void setRepartos(List<Reparto> repartos) {
		this.repartos = repartos;
	}

	public Reparto addReparto(Reparto reparto) {
		getRepartos().add(reparto);
		reparto.setConciliador(this);

		return reparto;
	}

	public Reparto removeReparto(Reparto reparto) {
		getRepartos().remove(reparto);
		reparto.setConciliador(null);

		return reparto;
	}

}