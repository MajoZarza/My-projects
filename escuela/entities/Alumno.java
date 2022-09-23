package com.inadaptados.escuela.entities;

import java.util.List;

public class Alumno extends Usuario {

	private float promedio;
	private List<Materia> materias;
	
	public float getPromedio() {
		return promedio;
	}
	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}
	public List<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
}
