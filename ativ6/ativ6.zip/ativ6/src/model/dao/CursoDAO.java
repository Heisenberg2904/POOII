package model.dao;

import java.util.List;

import model.entities.Curso;

public interface CursoDAO {
	void inserir(Curso obj);

	void atualizar(Curso obj);

	void deletar(Integer id);

	Curso Getbyid(Integer id);

	List<Curso> GetAll();

}
