package com.ProjetoAvaliaFormativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoAvaliaFormativa.entites.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{

}
