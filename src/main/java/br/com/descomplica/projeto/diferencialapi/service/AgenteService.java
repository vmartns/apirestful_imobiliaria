package br.com.descomplica.projeto.diferencialapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.descomplica.projeto.diferencialapi.entity.Agente;
import br.com.descomplica.projeto.diferencialapi.repository.AgenteRepository;


@Service
public class AgenteService {
	@Autowired
	AgenteRepository agenteRepository;
	
	public List<Agente> getAll(){
		return agenteRepository.findAll();
	}
	
	public Agente getById(Integer id) {
		return agenteRepository.findById(id).orElse(null) ;
	}
	
	public Agente saveAgente(Agente Agente) {
		return agenteRepository.save(Agente);
	}

	public Boolean deleteAgente(Integer id) {
		Agente Agente = agenteRepository.findById(id).orElse(null);
		if(Agente != null) {
			agenteRepository.delete(Agente);
			return true;
		}else {
			return false;
		}
	}
}
