package br.com.dobackaofront.produtos.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dobackaofront.produtos.entities.Cursos;
import br.com.dobackaofront.produtos.service.CursoService;


@ResponseBody
@RestController
@RequestMapping(value = "/api/cursos")
public class CursoController {
		
	@Autowired
	public final CursoService cursoService;
	

	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}
	
	@GetMapping
	public ResponseEntity<List<Cursos>> listarTodosCursos() {
		List<Cursos> cursos = cursoService.listaTodosCursos();
		return ResponseEntity.ok(cursos);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Cursos> buscarCursoPorId(@PathVariable Long id){
		Optional<Cursos> curso = cursoService.buscarCursoId(id);
		return curso.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Cursos> addCruso(@RequestBody Cursos curso){
		Cursos novoCurso = cursoService.salvarCurso(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cursos> atualizarCurso(@PathVariable Long id, @RequestBody Cursos cursoAtualizado){
		Optional<Cursos> curso = cursoService.buscarCursoId(id);
		
		if(curso.isPresent()) {
			cursoAtualizado.setId(id);
			Cursos cursoNovoSalvo = cursoService.salvarCurso(cursoAtualizado);
			return ResponseEntity.ok(cursoAtualizado);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
		@DeleteMapping("/{id}")
		public ResponseEntity<Cursos> excluirCurso(@PathVariable Long id){
		Optional<Cursos> curso = cursoService.buscarCursoId(id);
		
		if(curso.isPresent()) {
			cursoService.excluirCurso(id);
			return ResponseEntity.notFound().build();
			}
		else {
			return ResponseEntity.notFound().build();
		}							
	}
	
	
	
	
		
	
	
					

}
