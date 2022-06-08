package util;

import java.sql.*;

public class Conexao {

	private static Connection conn;
	//valores que não podem ser alterados
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String servidor = "localhost";
	private static final String porta = "3306";
	private static final String banco = "BANCO_V1";
	private static final String usuario = "root";
	private static final String senha = "170812";
	private static final String url = "jdbc:mysql://" + servidor + ":"
													  + porta + "/" + banco + "";
	//construtor
	private Conexao() {}
	
	//método para testar conexão com BD
	
	public static Connection getConnection() {
		try 
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, usuario, senha);	
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao conectar ao Driver");
			return null;
		} catch (SQLException e) {
			System.out.println("Não foi possivel estabelecer conexao com o bd");
			return null;
		}	
	}
	
	public static boolean fecharConexao() {
		try {
			Conexao.getConnection().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
