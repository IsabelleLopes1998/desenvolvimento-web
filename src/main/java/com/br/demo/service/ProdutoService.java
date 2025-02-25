package com.br.demo.service;


import com.br.demo.dto.request.ProdutoRequestDTO;
import com.br.demo.dto.response.ProdutoResponseDTO;
import com.br.demo.model.Produto;
import com.br.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;


    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseDTO> listarProdutos(){
        return produtoRepository.findAll().stream()
                .map(produto -> new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco()))
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO){
        Produto novoProduto = new Produto(null, produtoRequestDTO.getNome(), produtoRequestDTO.getPreco(), produtoRequestDTO.getNumeroSerie());
        Produto produtoSalvo = produtoRepository.save(novoProduto);
        return new ProdutoResponseDTO(produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getPreco());
    }

}
