package com.br.demo.repository;

import com.br.demo.dto.response.ProdutoResponseDTO;
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
    public Optional<Produto> findById(Long id){
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }


    public Produto save(Produto produto){
        produto.setId(nextId++);
        produtos.add(produto);
        return produto;
    }
    public Produto update(Produto produto){
        return produtos.stream()
                .filter(Produto -> produto.getId().equals(produto.getId()))
                .findFirst()
                .map(Produto ->{
                    produto.setNome(produto.getNome());
                    produto.setPreco(produto.getPreco());
                    produto.setNumeroSerie(produto.getNumeroSerie());
                    return produto;
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deleteById(Long id){
        produtos.removeIf(produto -> produto.getId().equals(id));
    }



}
