package com.zup.estrelas.sistemaPrefeitura.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zup.estrelas.sistemaPrefeitura.entity.ProjetoEntity;

@Repository
public interface ProjetoRepository extends CrudRepository<ProjetoEntity, Long> {

}