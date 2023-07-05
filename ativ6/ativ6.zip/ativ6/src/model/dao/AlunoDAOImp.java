package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Aluno;

public class AlunoDAOImp implements AlunoDAO {
	private Connection conexao;

	public AlunoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void Inserir(Aluno obj) {
		try {
			PreparedStatement st = conexao.prepareStatement(
					"INSERT INTO aluno (nome, sexo, dt_nasc, nota) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getSexo());
			st.setDate(3, new java.sql.Date(obj.getDt_nasc().getTime()));
			st.setDouble(4, obj.getNota());

			int linhas = st.executeUpdate();

			if (linhas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					obj.setIdaluno(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Atualizar(Aluno obj) {
		try {
			PreparedStatement st = conexao
					.prepareStatement("UPDATE aluno SET nome = ?, sexo = ?, dt_nasc = ?, nota = ? WHERE alunoid = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getSexo());
			st.setDate(3, new java.sql.Date(obj.getDt_nasc().getTime()));
			st.setDouble(4, obj.getNota());
			st.setInt(5, obj.getIdaluno());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletar(Integer id) {
		try {
			PreparedStatement st = conexao.prepareStatement("DELETE FROM aluno WHERE alunoid = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Aluno> GetAll() {
		List<Aluno> alunos = new ArrayList<>();

		try {
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM aluno");

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setIdaluno(rs.getInt("alunoid"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSexo(rs.getString("sexo"));
				aluno.setDt_nasc(rs.getDate("dt_nasc"));
				aluno.setNota(rs.getFloat("nota"));
				alunos.add(aluno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alunos;
	}
}


	@Override
	public Aluno Getbyid(Integer id) {
		Aluno aluno = null;

		try {
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM aluno WHERE alunoid = ?");

			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				aluno = new Aluno();
				aluno.setIdaluno(rs.getInt("alunoid"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSexo(rs.getString("sexo"));
				aluno.setDt_nasc(rs.getDate("dt_nasc"));
				aluno.setNota(rs.getFloat("nota"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aluno;
	}

	