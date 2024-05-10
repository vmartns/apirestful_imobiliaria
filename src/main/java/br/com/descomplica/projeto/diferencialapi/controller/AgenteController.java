package br.com.descomplica.projeto.diferencialapi.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.descomplica.projeto.diferencialapi.entity.Agente;
import br.com.descomplica.projeto.diferencialapi.service.AgenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/agente")
public class AgenteController {
	@Autowired
	AgenteService agenteService;

	@GetMapping
	@Operation(summary = "Listar todos os Agentes", description = "Listagem de Agentes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Requisição Inválida"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso."),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.") })
	public ResponseEntity<List<Agente>> getAll() {
		List<Agente> agentes = agenteService.getAll();
		if (!agentes.isEmpty())
			return new ResponseEntity<>(agentes, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Listar agentes por ID", description = "Listagem de Agentes por ID")
	public ResponseEntity<Agente> getById(@PathVariable Integer id) {
		Agente agente = agenteService.getById(id);
		if (agente != null)
			return new ResponseEntity<>(agente, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@Operation(summary = "Cadastrar Agentes", description = "Cadastro de Agentes")
	public ResponseEntity<Agente> saveAgente(@RequestBody Agente Agente) {
		return new ResponseEntity<>(agenteService.saveAgente(Agente), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar campos dos agentes", description = "Atualização de informações de Agentes")
	public ResponseEntity<Agente> updateAgente(@PathVariable Integer id, @RequestBody Agente agente) {
		Agente existingAgente = agenteService.getById(id);
		if (existingAgente != null) {
			agente.setId(id);
			Agente updatedAgente = agenteService.saveAgente(agente);
			return new ResponseEntity<>(updatedAgente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir agente por ID", description = "Excluir Agente")
	public ResponseEntity<Boolean> deleteAgente(@PathVariable Integer id) {
		if (agenteService.deleteAgente(id))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
