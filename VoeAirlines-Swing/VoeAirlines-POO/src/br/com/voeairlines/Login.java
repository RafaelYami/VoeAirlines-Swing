package br.com.voeairlines;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

import br.com.voeairlines.dao.UsuarioDAO;

public class Login extends JFrame implements ActionListener {

	private JFrame f = new JFrame("VoeAirlines - Voando alto com você!");
	private JLabel label = new JLabel();
	private JLabel lblUsuario = new JLabel("Usuário:");
	private JLabel lblSenha = new JLabel("Senha:");
	private JButton btnLogin = new JButton("Login");
	private JButton btnCadastro = new JButton("Cadastrar");
	private JTextField usuario = new JTextField();
	private JPasswordField senha = new JPasswordField();

	public Login() {
		label.setBounds(20, 150, 200, 50);
		lblUsuario.setBounds(100, 80, 80, 30);
		lblSenha.setBounds(100, 120, 80, 30);
		btnLogin.setBounds(80, 160, 80, 30);
		btnCadastro.setBounds(200, 160, 100, 30);
		usuario.setBounds(180, 80, 100, 30);
		senha.setBounds(180, 120, 100, 30);

		f.add(senha);
		f.add(lblUsuario);
		f.add(label);
		f.add(lblSenha);
		f.add(btnLogin);
		f.add(btnCadastro);
		f.add(usuario);
		f.setSize(400, 300);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.eventos();
		btnLogin.addActionListener(this);
		btnCadastro.addActionListener(this);

	}

	public void btnCadastroClick(ActionEvent ev) {
		CadastroUsuario.class.getClass();
	}

	public void btnLoginClick(ActionEvent ev) {
	}

	private void eventos() {
		btnLogin.addActionListener(this::btnLoginClick);
		btnCadastro.addActionListener(this::btnCadastroClick);
	}

	public void actionPerformed(ActionEvent a) {

		if(a.getSource() == btnLogin) {
		try {

			Connection con = UsuarioDAO.fazConexao();

			String sql = "select * from dados_login where usuario = ? and senha = ?";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, usuario.getText());
			stmt.setString(2, new String(senha.getPassword()));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				JOptionPane.showMessageDialog(null, "Login bem sucedido!");

			} else {

				JOptionPane.showMessageDialog(null, "Usuário/senha incorreto(s)!");

			}

			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		}else {
			CadastroUsuario Ca = new CadastroUsuario();
			Ca.setVisible(true);
		}
	}
}