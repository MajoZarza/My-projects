
package com.inadaptados.escuela.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inadaptados.escuela.entities.Usuario;

public class UsuarioDAO {
	
	private Connection conn;
	
	public UsuarioDAO(Connection _conn) {
		this.conn = _conn;
	}
	
	
	protected void createUsuario(Usuario usuario) throws DataAccessException {
		PreparedStatement ps = null;
		
		try {
		
			ps = conn.prepareStatement("INSERT INTO usuario (nombre, email, password) VALUES (?, ?, ?)");
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
		
			ps.execute();
		
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		
		} finally {
						
			try {
				if (ps != null) ps.close();
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}
	}

	public List<Usuario> getAllUsuarios() throws DataAccessException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT nombre, email, password FROM usuario");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Usuario entrada = new Usuario();
				entrada.setNombre(rs.getString("nombre"));
				entrada.setEmail(rs.getString("email"));
				entrada.setPassword(rs.getString("password"));
				
				usuarios.add(entrada);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		
		} finally {
			try {
				if (rs != null) rs.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			
			} try {
				if (ps != null) ps.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuarios;
	}

	protected void deleteUsuario(Usuario usuario ) throws DataAccessException {
	
		PreparedStatement ps = null;
	
		try {
			ps = conn.prepareStatement("DELETE FROM usuario WHERE nombre = ?");
			ps.setString(1, usuario.getNombre());
		
			ps.execute();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	protected void updateUsuario(Usuario usuario) throws DataAccessException {
	
		PreparedStatement ps = null;
	
		try {
			ps = conn.prepareStatement("UPDATE usuario SET nombre = ?, password = ? WHERE email = ?");
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getEmail());
		
			ps.execute();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
}