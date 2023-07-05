package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Curso;

public class CursoDAOImp implements CursoDAO {

	private Connection conexao;

	public CursoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}
	@Override
	public void deletar(Integer id) {
		try {
			PreparedStatement ps = conexao.prepareStatement("DELETE FROM curso WHERE idcurso = ?;");

			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inserir(Curso obj) {
		try {
			PreparedStatement ps = conexao.prepareStatement("INSERT INTO curso (nomecurso)" + "VALUES (?);",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getNomecurso());
			int linhas = ps.executeUpdate();

			if (linhas > 0) {
				ResultSet Result = ps.getGeneratedKeys();
				if (rs.next()) {
					obj.setIdcurso(Result.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void atualizar(Curso obj) {
		try {
			PreparedStatement ps = conexao.prepareStatement("UPDATE curso SET nomecurso = ? WHERE idcurso = ?;");

			ps.setString(1, obj.getNomecurso());
			ps.setInt(2, obj.getIdcurso());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Curso> GetAll() {
		List<Curso> cursos = new ArrayList<>();

		try {
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM curso;");

			ResultSet Result = ps.executeQuery();

			while (rs.next()) {
				Curso curso = new Curso();
				curso.setIdcurso(Result.getInt("idcurso"));
				curso.setNomecurso(Result.getString("nomecurso"));

				cursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cursos;
	}

}

	

	@Override
	public Curso Getbyid(Integer id) {
		Curso curso = null;

		try {
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM curso WHERE idcurso = ?;");

			st.setInt(1, id);

			ResultSet Result = st.executeQuery();

			if (rs.next()) {
				curso = new Curso();
				curso.setIdcurso(Result.getInt("idcurso"));
				curso.setNomecurso(Result.getString("nomecurso"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return curso;
	}

