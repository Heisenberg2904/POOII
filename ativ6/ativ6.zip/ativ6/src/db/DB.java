package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	private static Connection conexao;

	private static Properties getPropriedades() throws IOException {
		Properties propriedades = new Properties();
		FileInputStream file = new FileInputStream("./properties/db.properties");
		propriedades.load(file);
		return propriedades;

	}

	public static Connection getConexao() {
		String host;
		String user;
		String password;

		try {
			Properties p = DB.getPropriedades();
			if (conexao == null) {
				host = p.getProperty("host");
				user = p.getProperty("user");
				password = p.getProperty("password");
				conexao = DriverManager.getConnection(host, user, password);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return conexao;
	}

	public static void fechaConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
