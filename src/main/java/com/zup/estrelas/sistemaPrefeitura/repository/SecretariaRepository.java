package com.zup.estrelas.sistemaPrefeitura.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;

@Repository
public interface SecretariaRepository extends CrudRepository<SecretariaEntity, Long> {
	
	Optional<SecretariaEntity> findByArea(String area);

	boolean existsByNome(String nome);

	boolean existsByArea(String area);

	
}
