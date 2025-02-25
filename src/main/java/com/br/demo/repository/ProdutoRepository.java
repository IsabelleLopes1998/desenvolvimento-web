package com.br.demo.repository;

import com.br.demo.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {
    private static List<Produto> produtos = new ArrayList<>();
    private Long nextId = 1L;

    public List<Produto> findAll(){
        return produtos;
    }
    public Optional<Produto> findByID(Long id){
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }


}
