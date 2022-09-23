package com.inadaptados.escuela.cliente;

import java.sql.Connection;
import java.util.Scanner;

import com.inadaptados.escuela.facade.UsuariosFacade;

public class PantallaProfesores {
	
	
	private UsuariosFacade usuariosFacade;
	private Object conn;
	
	public PantallaProfesores(Connection conn) {
		
		
		this.conn = conn;
		

	}

	public void run(Scanner in) {
		String comando = "";
			
		do {
			System.out.println("Elija una opcion");
			System.out.println("1- Listar ");
			System.out.println("2- Crear ");
			System.out.println("3- Borrar ");

			comando = in.nextLine();
				
			if(comando.equalsIgnoreCase("1")) {
			
					
				} else if(comando.equalsIgnoreCase("2")) {
					
					 			
				} else if(comando.equalsIgnoreCase("3")) {
					
					
				} else if(comando.equalsIgnoreCase("Salir")) {
					System.out.println("Besis");
					
				} else {
					System.out.println("Comando desconocido");
					
				}
			} while (!comando.equalsIgnoreCase("Salir"));
				
		} 

}
