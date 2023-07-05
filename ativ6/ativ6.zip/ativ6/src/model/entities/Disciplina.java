package model.entities;

import java.util.Objects;

public class Disciplina {
	private Integer id;
	private String nomedisciplina;
	private int cargahoraria;

	public Disciplina() {
	}

	public Disciplina(Integer id, String nomedisciplina, int cargahoraria) {
		this.id = id;
		this.nomedisciplina = nomedisciplina;
		this.cargahoraria = cargahoraria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomedisciplina() {
		return nomedisciplina;
	}

	public void setNomedisciplina(String nomedisciplina) {
		this.nomedisciplina = nomedisciplina;
	}

	public int getCargahoraria() {
		return cargahoraria;
	}

	public void setCargahoraria(int cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cargahoraria, id, nomedisciplina);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return cargahoraria == other.cargahoraria && Objects.equals(id, other.id)
				&& Objects.equals(nomedisciplina, other.nomedisciplina);
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nomedisciplina=" + nomedisciplina + ", cargahoraria=" + cargahoraria + "]";
	}

}
