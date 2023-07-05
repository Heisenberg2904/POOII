package model.dao;

import db.DB;

public class FactoryDAO {
	public static CursoDAO createCursoDAO() {
		return new CursoDAOImp(DB.getConexao());
	}

	public static AlunoDAO createAlunoDAO() {
		return new AlunoDAOImp(DB.getConexao());
	}

	public static DisciplinaDAO createDisciplinaDAO() {
		return new DisciplinaDAOImp(DB.getConexao());
	}
}
