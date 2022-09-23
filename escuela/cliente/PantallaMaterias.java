package com.inadaptados.escuela.cliente;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.inadaptados.escuela.entities.Materia;
import com.inadaptados.escuela.facade.FacadeException;
import com.inadaptados.escuela.facade.MateriasFacade;

public class PantallaMaterias {


	private Connection conn;
	public PantallaMaterias(Connection _conn) {
		this.conn = _conn;
		
	} 
	
	private MateriasFacade materiasFacade = new MateriasFacade(conn);
	
	
	public void run(Scanner in) {
		String comando = "";
		
		do {
			System.out.println("Elija una opcion");
			System.out.println("1- Listar materias ");
			System.out.println("2- Crear o actualizar una materia ");
			System.out.println("3- Borrar materia");

			comando = in.nextLine();
			
			if(comando.equalsIgnoreCase("1")) {
				MateriasFacade materiasFacade = new MateriasFacade(conn);	
				
				try {
					
					 
					List<Materia> materias  = materiasFacade.listMaterias();
										
					System.out.println("Materia: " + materias);
								
				} catch (FacadeException e) {
					e.printStackTrace();
				}
				
			} else if(comando.equalsIgnoreCase("2")) {
				
				MateriasFacade materiasFacade;
				Materia materia;
					try {
					materiasFacade.createUpdateMateria(materia);
				} catch (FacadeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 			
			} else if(comando.equalsIgnoreCase("3")) {
				Materia nombre = null;
				try {
					MateriasFacade materiasFacade = null;
					materiasFacade.deleteMateria(nombre);
				} catch (FacadeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(comando.equalsIgnoreCase("Salir")) {
				System.out.println("Besis");
				
			} else {
				System.out.println("Comando desconocido");
				
			}
		} while (!comando.equalsIgnoreCase("Salir"));
			
	} 
	
	public void crearMateria(Scanner in) {
		System.out.println("Ingrese el nombre de la materia");
		
		
		
	}

	public MateriasFacade getMateriasFacade() {
		return materiasFacade;
	}

	public void setMateriasFacade(MateriasFacade materiasFacade) {
		this.materiasFacade = materiasFacade;
	}
	
}
