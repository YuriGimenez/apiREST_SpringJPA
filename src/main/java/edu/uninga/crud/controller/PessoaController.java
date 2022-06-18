package edu.uninga.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uninga.crud.model.Pessoa;
import edu.uninga.crud.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public Pessoa adicionar (@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@DeleteMapping("/{id}")
	public void apagarPessoa (@PathVariable long id) {
		pessoaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void updatePessoa (@PathVariable long id,
	@RequestBody Pessoa pessoa) {
		var p = pessoaRepository.findById(id);
		var pAlterada = p.get();
		pAlterada.setNome (pessoa.getNome());
		pAlterada.setCpf(pessoa.getCpf());
		pAlterada.setTelefone(pessoa.getTelefone());
		pessoaRepository.save(pAlterada);
		
	}
}
