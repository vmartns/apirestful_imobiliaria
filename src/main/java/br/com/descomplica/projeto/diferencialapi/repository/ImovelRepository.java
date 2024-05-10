package br.com.descomplica.projeto.diferencialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.diferencialapi.entity.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel,Integer> {

}
