package model.entities;

import java.util.Date;
import java.util.Objects;

public class Aluno {
	private Integer idaluno;
	private String nome;
	private String sexo;
	private Date dt_nasc;
	private float nota;

	public Aluno() {
	}

	public Aluno(Integer idaluno, String nome, String sexo, Date dt_nasc, float nota) {
		this.idaluno = idaluno;
		this.nome = nome;
		this.sexo = sexo;
		this.dt_nasc = dt_nasc;
		this.nota = nota;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDt_nasc() {
		return dt_nasc;
	}

	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}

	public Integer getIdaluno() {
		return idaluno;
	}

	public void setIdaluno(Integer idaluno) {
		this.idaluno = idaluno;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dt_nasc, idaluno, nome, nota, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(dt_nasc, other.dt_nasc) && Objects.equals(idaluno, other.idaluno)
				&& Objects.equals(nome, other.nome) && Float.floatToIntBits(nota) == Float.floatToIntBits(other.nota)
				&& Objects.equals(sexo, other.sexo);
	}

	@Override
	public String toString() {
		return "Aluno [idaluno=" + idaluno + ", nome=" + nome + ", sexo=" + sexo + ", dt_nasc=" + dt_nasc + ", nota="
				+ nota + "]";
	}

}
