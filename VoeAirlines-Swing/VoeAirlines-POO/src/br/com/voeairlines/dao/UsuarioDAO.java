package br.com.voeairlines.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.voeairlines.model.Usuario;

public class UsuarioDAO {
	
	public static Connection fazConexao() throws SQLException {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/db_login", "root", "98486882r");
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}
	}
		
	public void cadastra(Usuario usuario) {

		String sql = "INSERT INTO dados_login(usuario,senha) VALUES(?,?)";

		try {
			PreparedStatement preparador = fazConexao().prepareStatement(sql);
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());

			preparador.execute();
			preparador.close();

			System.out.println("Dados salvos com sucesso");

		} catch (SQLException e) {
			System.out.println("Não foi possível salvar os dados" + e.getMessage());

		}
		
	}
	
}

