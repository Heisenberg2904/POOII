package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Disciplina;

public class DisciplinaDAOImp implements DisciplinaDAO {
	private Connection conexao;

	public DisciplinaDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Disciplina obj) {
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO disciplina (nome, cargah) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getNomedisciplina());
			ps.setInt(2, obj.getCargahoraria());

			int linhas = ps.executeUpdate();

			if (linhas > 0) {
				ResultSet Result = ps.getGeneratedKeys();
				if (Result.next()) {
					obj.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Disciplina obj) {
		try {
			PreparedStatement ps = conexao
					.prepareStatement("UPDATE disciplina SET nomedi = ?, cargah = ? WHERE id = ?");

			ps.setString(1, obj.getNomedisciplina());
			ps.setInt(2, obj.getCargahoraria());
			ps.setInt(3, obj.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletar(Integer id) {
		try {
			PreparedStatement ps = conexao.prepareStatement("DELETE FROM disciplina WHERE id = ?");

			ps.setInt(1, id);

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
	public Disciplina GetById(Integer id) {
		Disciplina disciplina = null;

		try {
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM disciplina WHERE id = ?");

			ps.setInt(1, id);

			ResultSet Result = st.executeQuery();

			if (rs.next()) {
				disciplina = new Disciplina();
				disciplina.setId(Result.getInt("id"));
				disciplina.setNomedisciplina(Result.getString("nome"));
				disciplina.setCargahoraria(Result.getInt("cargah"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return disciplina;
	}

	
