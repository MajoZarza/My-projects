package com.inadaptados.escuela.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inadaptados.escuela.entities.Profesor;

public class ProfesorDAO extends UsuarioDAO {

	private Connection conn;

	public ProfesorDAO(Connection _conn) {
		super(_conn);
		this.conn = _conn;
	}

	public List<Profesor> getAllProfesores() throws DataAccessException{
		
		List<Profesor> profesores = new ArrayList<Profesor>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT nombre FROM profesor");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Profesor entrada = new Profesor();
				entrada.setNombre(rs.getString("nombre"));
								
				profesores.add(entrada);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return profesores;
	}
	
	public Profesor findProfesor(String nombre) throws DataAccessException{
		Profesor profesor = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT nombre, salario FROM profesor INNER JOIN usuario WHERE usuario.nombre = ?");
			ps.setString(1, nombre);
			float salario = 0;
			ps.setFloat(2, salario);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				profesor = new Profesor();
				profesor.setNombre(rs.getString("nombre"));
				profesor.setSalario(rs.getFloat("salario"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
			
		} finally {
			
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return profesor;
	}
	
	public List<Profesor> findProfesorByMaterias(Profesor profesor) throws DataAccessException {
		List<Profesor> profesores = new ArrayList<Profesor>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			ps = conn.prepareStatement("SELECT materia, profesor FROM materia_profesor WHERE materia = ?");
			ps.setString(1, profesor.getEmail());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Profesor entrada = new Profesor();
				entrada.setNombre(rs.getString("profesor"));
				profesores.add(entrada);
			}
		
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
			
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return profesores;
	}

	public void createProfesor(Profesor profesor) throws DataAccessException {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("INSERT INTO profesor (email, salario) values (?, ?)");
			ps.setString(1, profesor.getEmail());
			ps.setFloat(2, profesor.getSalario());
		
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
	
	public void deleteProfesor(String email) throws DataAccessException {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM profesor WHERE email = ?");
			ps.setString(1, email);
			
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
	
	public void updateProfesor(Profesor profesor) throws DataAccessException {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE profesor SET salario = ? WHERE email = ?");
			ps.setFloat(1, profesor.getSalario());
					
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