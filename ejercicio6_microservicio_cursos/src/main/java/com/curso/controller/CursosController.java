package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursosService;

@CrossOrigin("*")
@RestController
public class CursosController {
	
	@Value("${eureka.instance.instance-id}")
	String instancia;
	
	@Autowired
	CursosService service;
	
	@GetMapping(value="cursos", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursos() {
		System.out.println(instancia);
		return service.cursos();
	}
	
	@PostMapping(value="altaCurso", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> alta(@RequestBody Curso curso) {
		return service.nuevoCurso(curso);
	}
	
	@DeleteMapping(value="eliminarCurso/{codCurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> eliminar(@PathVariable("codCurso") String codCurso) {
		return service.eliminarCurso(codCurso);
	}
	
	@PutMapping(value="actualizarDuracion/{codCurso}/{duracion}")
	public void actualizarDuracion(@PathVariable("codCurso") String codCurso, @PathVariable("duracion") int duracion) {
		service.actualizarDuracionCurso(codCurso, duracion);
	}
	
	@GetMapping(value="curso/{codCurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCurso(@PathVariable("codCurso") String codCurso) {
		return service.buscarCurso(codCurso);
	}
	
	@GetMapping(value="cursosPorPrecio/{precioMin}/{precioMax}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosPorPrecio(@PathVariable("precioMin") Double precioMin, @PathVariable("precioMax") Double precioMax) {
		return service.cursoPorRangoDePrecio(precioMin, precioMax);
	}
	
	
}
