package com.curso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.curso.model.Curso;

public interface CursosDao extends JpaRepository<Curso, String> {
	
	@Query("Select c from Curso c where c.precio >= ?1 and c.precio <= ?2")
	List<Curso> recuperarPorRangoDePrecio(Double precioMin, Double precioMax);
	
}
