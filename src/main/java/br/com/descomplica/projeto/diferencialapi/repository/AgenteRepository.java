package br.com.descomplica.projeto.diferencialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.diferencialapi.entity.Agente;

public interface AgenteRepository extends JpaRepository<Agente,Integer> {

}
