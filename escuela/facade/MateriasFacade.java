package com.inadaptados.escuela.facade;

import java.sql.Connection;
import java.util.List;

import com.inadaptados.escuela.dao.DataAccessException;
import com.inadaptados.escuela.dao.MateriaDAO;
import com.inadaptados.escuela.entities.Materia;

public class MateriasFacade {
	
	private Connection conn;
	
	public MateriasFacade(Connection _conn) {
		this.conn = _conn;
		
	}
	
	/**
	 * Obtiene todas las materias presentes en la base de datos
	 * @return Listado de materias
	 * @throws FacadeException cuando hay un error de acceso a datos
	 */
	
	
	public List<Materia> listMaterias() throws FacadeException {
		MateriaDAO materiaDAO = new MateriaDAO(conn);
		
		try {
			return materiaDAO.getAllMaterias();
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}
	}

	/**
	 * Crea una materia si no existe en la DB o la actualiza si ya existe 
	 * @param Materia a crear o actualizar
	 * @return Materia tal como quedo en la DB
	 */
	public Materia createUpdateMateria(Materia materia) throws FacadeException {
		MateriaDAO materiaDAO = new MateriaDAO(conn);
	
		Materia dbMateria; 
		
		try {
		
			dbMateria = materiaDAO.findMateria(materia.getNombre());
			if (dbMateria == null) {
				materiaDAO.createMateria(materia);
		
			} else {
				materiaDAO.updateMateria(materia);
		} 
		
		dbMateria = materiaDAO.findMateria(materia.getNombre());	
		
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}
	
		return dbMateria;
		
	}
	
	/**
	*Elimina la materia de la base de datos
	*@return no retorna nada
	*@throws cuando hay un error de acceso a la base de datos
	*/
	
	public void deleteMateria(Materia nombre) throws FacadeException {
		
		MateriaDAO materiaDAO = new MateriaDAO(conn);
				
		
		try {
			materiaDAO.deleteMateria(nombre);
						
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new FacadeException("Error de acceso a datos. Consulte al administrador");
		}
		
	}
}
