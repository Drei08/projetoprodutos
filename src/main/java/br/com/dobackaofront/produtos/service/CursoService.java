package br.com.dobackaofront.produtos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.dobackaofront.produtos.entities.Cursos;
import br.com.dobackaofront.produtos.repository.CursoRepository;

@Service	
public class CursoService {
	
	@Autowired
	private final CursoRepository cursoRepository;
	

	public CursoService(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
		}
		
	public Cursos salvarCurso(Cursos curso) {
		return cursoRepository.save(curso);	
		}
	
	public Optional<Cursos>buscarCursoId(Long id) {
		return cursoRepository.findById(id);
	}

	public List<Cursos> listaTodosCursos() {
		return cursoRepository.findAll();	
	}
	
	public void excluirCurso(Long id) {
		cursoRepository.deleteById(id);
	}
			
}
	


