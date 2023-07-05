package model.entities;

import java.util.Objects;

public class Curso {
	private Integer idcurso;
	private String nomecurso;

	public Curso() {

	}

	public Curso(Integer idcurso, String nomecurso) {
		this.idcurso = idcurso;
		this.nomecurso = nomecurso;
	}

	public Integer getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public String getNomecurso() {
		return nomecurso;
	}

	public void setNomecurso(String nomecurso) {
		this.nomecurso = nomecurso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idcurso, nomecurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(idcurso, other.idcurso) && Objects.equals(nomecurso, other.nomecurso);
	}

	@Override
	public String toString() {
		return "Curso [idcurso=" + idcurso + ", nomecurso=" + nomecurso + "]";
	}

}
