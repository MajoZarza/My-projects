package com.inadaptados.escuela.facade;

import java.sql.Connection;
import java.util.List;

import com.inadaptados.escuela.dao.AlumnoDAO;
import com.inadaptados.escuela.dao.DataAccessException;
import com.inadaptados.escuela.dao.ProfesorDAO;
import com.inadaptados.escuela.entities.Alumno;
import com.inadaptados.escuela.entities.Profesor;

public class UsuariosFacade {

	private Connection conn;
		
	public UsuariosFacade(Connection _conn) {
			this.conn = _conn;
			
	} 
	
	/**
	 * Lista los alumnos de la BD
	 * @return lista de alumnos
	 * @throws FacadeException cuando hay un error a acceso a datos.
	 */
	
	public List<Alumno> listAlumno() throws FacadeException {
		
		AlumnoDAO alumnoDAO = new AlumnoDAO(conn);
		
		try {
			return alumnoDAO.getAllAlumnos();
			
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}
	}
	
	/**
	* Lista los profesores de la BD
	*@return listado de profesores
	*@throws FacadeExcepcion cuando hay un error de acceso a datos.
	
	*/
	
	public List<Profesor> listProfesor() throws FacadeException {
		
		ProfesorDAO profesorDAO = new ProfesorDAO(conn);
		
		try {
			
			return profesorDAO.getAllProfesores();
			
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}
	}

	/**
	 * Elimina a un profesor de la base de datos
	 * @param profesor
	 * @throws FacadeException por error de acceso a la base de datos
	 */
		
	public void deleteProfesor (String email) throws FacadeException {
		
		ProfesorDAO profesorDAO = new ProfesorDAO(conn);
		
		try {
			profesorDAO.deleteProfesor(email);
					
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}

	}

	/**
	 * Elimina a un alumno de la base de datos
	 * @param alumno
	 * @throws FacadeException por error de acceso a la base de datos
	 */
		
	public void deleteAlumno (String email) throws FacadeException {
		
		AlumnoDAO alumnoDAO = new AlumnoDAO(conn);
		
		try {
			alumnoDAO.deleteAlumno(email);
						
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}

	}
	
	/**
	 * Crea un profesor si no existe en la DB o lo actualiza si ya existe 
	 * @param Profesor a crear o actualizar
	 * @return Profesor tal como quedo en la DB
	 */
	public Profesor createUpdateProfesor(Profesor profesor) throws FacadeException {
		ProfesorDAO profesorDAO = new ProfesorDAO(conn);
	
		Profesor dbProfesor; 
		
		try {
			dbProfesor = profesorDAO.findProfesor(profesor.getEmail());
			if (dbProfesor == null) {
				profesorDAO.createProfesor(profesor);
			} else {
				profesorDAO.updateProfesor(profesor);
			} 
		
			dbProfesor = profesorDAO.findProfesor(profesor.getEmail());	
		
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}
	
		return dbProfesor;
		
	}
	
	/**
	 * Crea un alumno si no existe en la DB o lo actualiza si ya existe 
	 * @param ALumno a crear o actualizar
	 * @return ALumno tal como quedo en la DB
	 */
	public Alumno createUpdateAlumno(Alumno alumno) throws FacadeException {
		AlumnoDAO alumnoDAO = new AlumnoDAO(conn);
	
		Alumno dbAlumno; 
		
		try {
		
			dbAlumno = alumnoDAO.findAlumno(alumno.getEmail());
			if (dbAlumno == null) {
				alumnoDAO.createAlumno(alumno);
			} else {
				alumnoDAO.updateAlumno(alumno);
			} 
		
		dbAlumno = alumnoDAO.findAlumno(alumno.getEmail());	
	
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}
	
		return dbAlumno;
		
	}
	
	
	
	
}
