package br.com.svbgabriel.comercial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.svbgabriel.comercial.model.Oportunidade;
import br.com.svbgabriel.comercial.repository.OportunidadeRepository;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

	@Autowired
	private OportunidadeRepository oportunidades;

	@GetMapping
	public List<Oportunidade> listar() {
		return oportunidades.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidades.findById(id);

		if (oportunidade.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(oportunidade.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adicionar(@RequestBody Oportunidade oportunidade) {
		return oportunidades.save(oportunidade);
	}
}
