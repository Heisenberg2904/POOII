package model.dao;

import java.util.List;

import model.entities.Aluno;

public interface AlunoDAO {
	void deletar(Integer id);
	
	void Inserir(Aluno obj);
	
	List<Aluno> GetAll();
	
	void Atualizar(Aluno obj);

	Aluno Getbyid(Integer id);


}