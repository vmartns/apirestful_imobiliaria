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

import br.com.descomplica.projeto.diferencialapi.entity.Imovel;
import br.com.descomplica.projeto.diferencialapi.service.ImovelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/imovel")
public class ImovelController {
	@Autowired
	ImovelService ImovelService;

	@GetMapping
	@Operation(summary = "Listar todas os Imoveis", description = "Listagem de Imoveis")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Requisição Inválida"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso."),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado.") })
	public ResponseEntity<List<Imovel>> getAll() {
		List<Imovel> Imovels = ImovelService.getAll();
		if (!Imovels.isEmpty())
			return new ResponseEntity<>(Imovels, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Listar imovel por ID", description = "Listagem de Imóveis por ID")
	public ResponseEntity<Imovel> getById(@PathVariable Integer id) {
		Imovel Imovel = ImovelService.getById(id);
		if (Imovel != null)
			return new ResponseEntity<>(Imovel, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@Operation(summary = "Cadastrar Imóvel", description = "Cadastro de Imóveis")
	public ResponseEntity<Imovel> saveImovel(@RequestBody Imovel Imovel) {
		return new ResponseEntity<>(ImovelService.saveImovel(Imovel), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar campos de imovel", description = "Atualizar Informações de Imóvel")
	public ResponseEntity<Imovel> updateImovel(@PathVariable Integer id, @RequestBody Imovel imovel) {
		Imovel existingImovel = ImovelService.getById(id);
		if (existingImovel != null) {
			imovel.setId(id);
			Imovel updatedImovel = ImovelService.saveImovel(imovel);
			return new ResponseEntity<>(updatedImovel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Imóvel por ID", description = "Excluir Imóvel por ID")
	public ResponseEntity<Boolean> deleteImovel(@PathVariable Integer id) {
		if (ImovelService.deleteImovel(id))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
