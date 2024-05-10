package br.com.descomplica.projeto.diferencialapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.descomplica.projeto.diferencialapi.entity.Visita;
import br.com.descomplica.projeto.diferencialapi.repository.VisitaRepository;

@Service
public class VisitaService {
	@Autowired
	VisitaRepository VisitaRepository;
	
	public List<Visita> getAll(){
		return VisitaRepository.findAll();
	}
	
	public Visita getById(Integer id) {
		return VisitaRepository.findById(id).orElse(null) ;
	}
	
	public Visita saveVisita(Visita Visita) {
		return VisitaRepository.save(Visita);
	}

	public Boolean deleteVisita(Integer id) {
		Visita Visita = VisitaRepository.findById(id).orElse(null);
		if(Visita != null) {
			VisitaRepository.delete(Visita);
			return true;
		}else {
			return false;
		}
	}
}
