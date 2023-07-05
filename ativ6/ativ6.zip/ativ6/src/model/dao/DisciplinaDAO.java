package model.dao;

import java.util.List;

import model.entities.Disciplina;

public interface DisciplinaDAO {
	void inserir(Disciplina obj);

	void atualizar(Disciplina obj);

	void deletar(Integer id);

	Disciplina GetById(Integer id);

	List<Disciplina> GetAll();

}