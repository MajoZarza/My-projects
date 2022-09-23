package com.inadaptados.escuela.cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/escuela?" + "user=root&password=Password");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		
		
		String comando = "";
		System.out.println("Bienvenido a nuestro fabuloso programa.");
		
	
		PantallaMaterias pantallaMaterias = new PantallaMaterias(conn);
		PantallaAlumnos pantallaAlumnos = new PantallaAlumnos(conn);
		PantallaProfesores pantallaProfesores = new PantallaProfesores(conn);
						
		
		do {
			Scanner in = new Scanner(System.in);
			System.out.println("Escriba la <opcion del menu>");
			System.out.println("1- Materias ");
			System.out.println("2- Alumnos ");
			System.out.println("3- Profesores ");
			System.out.println("Salir ");
			
			comando = in.nextLine();
			
			if(comando.equalsIgnoreCase("1")) {
				
				pantallaMaterias.run(in);

			} else if(comando.equalsIgnoreCase("2")) {
				
				pantallaAlumnos.run(in);
				 			
			} else if(comando.equalsIgnoreCase("3")) {
				
				pantallaProfesores.run(in);
				
			} else if(comando.equalsIgnoreCase("Salir")) {
				System.out.println("Besitos");
				
			} else {
				System.out.println("Comando desconocido");
				
			}
		} while (!comando.equalsIgnoreCase("Salir"));
	}

}
