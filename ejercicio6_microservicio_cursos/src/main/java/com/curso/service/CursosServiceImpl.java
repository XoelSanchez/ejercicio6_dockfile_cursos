package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dao.CursosDao;
import com.curso.model.Curso;

@Service
public class CursosServiceImpl implements CursosService {
	
	@Autowired
	CursosDao dao;
	
	@Override
	public List<Curso> cursos() {
		return dao.findAll();
	}
	
	@Override
	public List<Curso> nuevoCurso(Curso curso) {
		dao.save(curso);
		return dao.findAll();
	}
	
	@Override
	public List<Curso> eliminarCurso(String codCurso) {
		dao.deleteById(codCurso);
		
		return dao.findAll();
	}
	
	@Override
	public void actualizarDuracionCurso(String codCurso, int duracion) {
		Curso curso = dao.findById(codCurso).orElse(null);
		
		if (curso != null) {
			curso.setDuracion(duracion);
			dao.save(curso);
		}
	}
	
	@Override
	public Curso buscarCurso(String codCurso) {
		return dao.findById(codCurso).orElse(null);
	}
	
	@Override
	public List<Curso> cursoPorRangoDePrecio(Double precioMin, Double precioMax) {
		return dao.recuperarPorRangoDePrecio(precioMin, precioMax);
	}
	
	
}
