package com.generention.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generention.farmacia.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);
}