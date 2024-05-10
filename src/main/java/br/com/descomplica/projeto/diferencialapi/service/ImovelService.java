package br.com.descomplica.projeto.diferencialapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.descomplica.projeto.diferencialapi.entity.Imovel;
import br.com.descomplica.projeto.diferencialapi.repository.ImovelRepository;

@Service
public class ImovelService {
	@Autowired
	ImovelRepository imovelRepository;
	
	public List<Imovel> getAll(){
		return imovelRepository.findAll();
	}
	
	public Imovel getById(Integer id) {
		return imovelRepository.findById(id).orElse(null) ;
	}
	
	public Imovel saveImovel(Imovel Imovel) {
		return imovelRepository.save(Imovel);
	}

	public Boolean deleteImovel(Integer id) {
		Imovel Imovel = imovelRepository.findById(id).orElse(null);
		if(Imovel != null) {
			imovelRepository.delete(Imovel);
			return true;
		}else {
			return false;
		}
	}
}
