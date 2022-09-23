package com.inadaptados.escuela.entities;

import java.util.List;

public class Profesor extends Usuario {

	private float salario;
	private List<Materia> materias;
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public List<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	
}
