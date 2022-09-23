package com.inadaptados.escuela.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inadaptados.escuela.entities.Alumno;
import com.inadaptados.escuela.entities.Usuario;

public class AlumnoDAO extends UsuarioDAO{
	
	private Connection conn;
	
	public AlumnoDAO(Connection _conn) {
		super(_conn);
		this.conn = _conn;
	}
	
	
	public List<Alumno> getAllAlumnos() throws DataAccessException {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT nombre, a.email, promedio FROM alumno a INNER JOIN usuario u ON a.email = u.email");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Alumno entrada = new Alumno();
				entrada.setNombre(rs.getString("nombre"));
				entrada.setEmail(rs.getString("email"));
				entrada.setPromedio(rs.getInt("promedio"));
				
				alumnos.add(entrada);
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
		
		return alumnos;
	}

	public Alumno findAlumno(String email) throws DataAccessException {
		Alumno alumno = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT nombre, alumno.email, promedio FROM alumno inner join usuario ON alumno.email = usuario.email WHERE nombre = ?");
			ps.setString(1, email);
		
			rs = ps.executeQuery();
			
			if (rs.next()) {
				alumno = new Alumno();
				alumno.setNombre(rs.getString("nombre"));
				alumno.setEmail(rs.getString("email"));
				alumno.setPromedio(rs.getFloat("promedio"));
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
		return alumno;
	}
	
	public List<Alumno> findAlumnosByMateria() throws DataAccessException {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	
		try {
			ps = conn.prepareStatement("SELECT alumno FROM materia_alumno WHERE materia = ?");
			ps.setString(1, ((Usuario) alumnos).getNombre());
						
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Alumno entrada = new Alumno();
				entrada.setNombre(rs.getString("alumno"));
						
				alumnos.add(entrada);
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
		return alumnos;
		
	}

	public void updateAlumno(Alumno alumno) throws DataAccessException {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE alumno SET promedio = ? WHERE email = ?");
			ps.setFloat(2, alumno.getPromedio());
			ps.setString(2, alumno.getEmail());
			
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
	
	public void deleteAlumno(String email) throws DataAccessException {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM alumno WHERE email = ?");
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

	public void createAlumno(Alumno alumno) throws DataAccessException {
		PreparedStatement ps = null;
		
		try {
		
			ps = conn.prepareStatement("INSERT INTO alumno (email, promedio) values (?, ?)");
			ps.setString(1, alumno.getEmail());
			ps.setFloat(2, alumno.getPromedio());
		
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
