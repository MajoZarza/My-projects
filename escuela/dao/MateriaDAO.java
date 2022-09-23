package com.inadaptados.escuela.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inadaptados.escuela.entities.Alumno;
import com.inadaptados.escuela.entities.Materia;
import com.inadaptados.escuela.entities.Profesor;

public class MateriaDAO {

	private Connection conn;
	
	public MateriaDAO(Connection _conn) {
		this.conn = _conn;
	}
	
	public List<Materia> getAllMaterias() throws DataAccessException{
		List<Materia> materias = new ArrayList<Materia>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT nombre, horario From materia");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Materia entrada = new Materia();
				entrada.setNombre(rs.getString("nombre"));
				entrada.setHorario(rs.getDate("horario"));
				
				materias.add(entrada);
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
		return materias;
	}
	
	public Materia findMateria(String nombre) throws DataAccessException{
		Materia materia = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT nombre, horario FROM materia WHERE nombre = ?");
			ps.setString(1, nombre);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				materia = new Materia();
				materia.setNombre(rs.getString("nombre"));
				materia.setHorario(rs.getDate("horario"));
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
		return materia;
	}
	
	public List<Materia> findMateriasByProfesor(Profesor profesor) throws DataAccessException {
		List<Materia> materias = new ArrayList<Materia>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			ps = conn.prepareStatement("SELECT materia, horario FROM escuela.materia INNER JOIN materia_profesor mp WHERE mp.profesor = ?");
			ps.setString(1, profesor.getEmail());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Materia entrada = new Materia();
				entrada.setNombre(rs.getString("nombre"));
				entrada.setHorario(rs.getDate("horario"));
				
				materias.add(entrada);
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
		return materias;
	}

	public void createMateria(Materia materia) throws DataAccessException {
		PreparedStatement ps = null;
		
		try {
		
			ps = conn.prepareStatement("INSERT INTO materia (nombre, horario) values (?, ?)");
			ps.setString(1, materia.getNombre());
			ps.setDate(2, new java.sql.Date(materia.getHorario().getTime()));
		
			ps.execute();
		
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
						
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
	}
	
	public void deleteMateria(Materia materia) throws DataAccessException {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM materia WHERE nombre = ?");
			ps.setString(1, materia.getNombre());
			
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
	
	public void updateMateria(Materia materia) throws DataAccessException {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE materia SET horario = ? WHERE nombre = ?");
			ps.setDate(1, new java.sql.Date(materia.getHorario().getTime()));
			ps.setString(2, materia.getNombre());
			
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

	public List<Materia> findMateriaByAlumno(Alumno alumno) throws DataAccessException{
		
		List<Materia> materias = new ArrayList<Materia>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			ps = conn.prepareStatement("SELECT materia, horario FROM escuela.materia INNER JOIN materia_alumno ma WHERE ma.alumno = ?");
			ps.setString(1, alumno.getEmail());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Materia entrada = new Materia();
				entrada.setNombre(rs.getString("nombre"));
				entrada.setHorario(rs.getDate("horario"));
				
				materias.add(entrada);
			}
		
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
			
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) ps.close();
			} catch (final SQLException e) {
					
				e.printStackTrace();
			}
			
		}
		return materias;
	}
}

