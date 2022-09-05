package com.generention.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.generention.farmacia.model.Produto;
import com.generention.farmacia.repository.ProdutoRepository;



public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/{nomeProduto}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nomeProduto){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping
	public ResponseEntity<List<Produto>> showAllProducts(Produto produtoModel){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
	}
	@PostMapping
	public ResponseEntity<Produto> postProducts(@Valid @RequestBody Produto produtoModel){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
	}
	@PutMapping
	private ResponseEntity <Produto> putProduto(@Valid @RequestBody Produto produtoModel){
		return produtoRepository.findById(produtoModel.getId())
				.map(resposta -> ResponseEntity.ok(produtoRepository.save(produtoModel)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		java.util.Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);
	}
}

