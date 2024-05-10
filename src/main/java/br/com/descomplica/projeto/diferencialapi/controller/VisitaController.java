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

import br.com.descomplica.projeto.diferencialapi.entity.Visita;
import br.com.descomplica.projeto.diferencialapi.service.VisitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/visita")
public class VisitaController {
	@Autowired
	VisitaService VisitaService;

	@GetMapping
	@Operation(summary = "Listar todas as Visitas", description = "Listagem de Visitas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Requisição Inválida"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso."),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.") })
	public ResponseEntity<List<Visita>> getAll() {
		List<Visita> visitas = VisitaService.getAll();
		if (!visitas.isEmpty())
			return new ResponseEntity<>(visitas, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Listar Visita por ID", description = "Listagem de Visita por ID")
	public ResponseEntity<Visita> getById(@PathVariable Integer id) {
		Visita Visita = VisitaService.getById(id);
		if (Visita != null)
			return new ResponseEntity<>(Visita, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@Operation(summary = "Cadastrar Visita", description = "Cadastro de Visita")
	public ResponseEntity<Visita> saveVisita(@RequestBody Visita Visita) {
		return new ResponseEntity<>(VisitaService.saveVisita(Visita), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Visita", description = "Atualização de Visita")
	public ResponseEntity<Visita> updateVisita(@PathVariable Integer id, @RequestBody Visita visita) {
		Visita existingVisita = VisitaService.getById(id);
		if (existingVisita != null) {
			visita.setVisitaId(id);
			Visita updatedVisita = VisitaService.saveVisita(visita);
			return new ResponseEntity<>(updatedVisita, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Visita", description = "Deletar Visita")
	public ResponseEntity<Boolean> deleteVisita(@PathVariable Integer id) {
		if (VisitaService.deleteVisita(id))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
