package com.inadaptados.escuela.cliente;

import java.sql.Connection;
import java.util.Scanner;

import com.inadaptados.escuela.facade.FacadeException;
import com.inadaptados.escuela.facade.UsuariosFacade;

public class PantallaAlumnos {
	
	
	private Connection conn;
	private UsuariosFacade usuariosFacade;
	
	
	public PantallaAlumnos(Connection _conn) {
		this.conn = _conn;
		
	}

	public void run(Scanner in) {
		String comando = "";
		
		do {
			System.out.println("Elija una opcion");
			System.out.println("1- Listar alumnos ");
			System.out.println("2- Crear nuevo alumno");
			System.out.println("3- Borrar alumno");

			comando = in.nextLine();
			
			if(comando.equalsIgnoreCase("1")) {
				
				try {
					usuariosFacade.listAlumno();
				} catch (FacadeException e) {
				
					e.printStackTrace();
				}	
				
			} else if(comando.equalsIgnoreCase("2")) {
				
				
				 			
			} else if(comando.equalsIgnoreCase("3")) {
				
				
			} else if(comando.equalsIgnoreCase("Salir")) {
				System.out.println("Besis");
				
			} else {
				System.out.println("Comando desconocido");
				
			}
		} while (!comando.equalsIgnoreCase("Salir"));
			
	} 
	
	public void crearAlumno(Scanner in) {
		System.out.println("Ingrese el nombre del alumno");
		
		
		
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
