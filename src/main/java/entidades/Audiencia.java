package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Audiencia" database table.
 * 
 */
@Entity
@Table(name="\"Audiencia\"")
@NamedQuery(name="Audiencia.findAll", query="SELECT a FROM Audiencia a")
public class Audiencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_audiencia")
	private Long idAudiencia;

	@Column(name="audiencia_num")
	private Integer audienciaNum;

	private String estado;

	//bi-directional many-to-one association to Agenda
	@OneToMany(mappedBy="audiencia",fetch=FetchType.EAGER)
	private List<Agenda> agendas;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="audiencia")
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;

	public Audiencia() {
	}

	public Long getIdAudiencia() {
		return this.idAudiencia;
	}

	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	public Integer getAudienciaNum() {
		return this.audienciaNum;
	}

	public void setAudienciaNum(Integer audienciaNum) {
		this.audienciaNum = audienciaNum;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public Agenda addAgenda(Agenda agenda) {
		getAgendas().add(agenda);
		agenda.setAudiencia(this);

		return agenda;
	}

	public Agenda removeAgenda(Agenda agenda) {
		getAgendas().remove(agenda);
		agenda.setAudiencia(null);

		return agenda;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setAudiencia(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setAudiencia(null);

		return asistencia;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}