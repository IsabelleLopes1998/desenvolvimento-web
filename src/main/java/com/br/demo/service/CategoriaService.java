package com.br.demo.service;

import com.br.demo.dto.CategoriaDTO;
import com.br.demo.dto.request.ProdutoRequestDTO;
import com.br.demo.dto.response.ProdutoResponseDTO;
import com.br.demo.model.Categoria;
import com.br.demo.model.Produto;
import com.br.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> listarCategorias(){
        return categoriaRepository.findAll().stream()
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao()))
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO){
        Categoria novaCategoria = new Categoria(categoriaDTO.getId(), categoriaDTO.getNome(), categoriaDTO.getDescricao());
        Categoria categoriaSalva = categoriaRepository.save(novaCategoria);
        return new CategoriaDTO(categoriaSalva.getId(), categoriaSalva.getNome(), categoriaSalva.getDescricao());
    }

    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoriaExistente.setId(categoriaDTO.getId());
        categoriaExistente.setNome(categoriaDTO.getNome());
        categoriaExistente.setDescricao(categoriaDTO.getDescricao());

        Categoria categoriaAtualizada = categoriaRepository.update(categoriaExistente);

        return new CategoriaDTO(categoriaAtualizada.getId(), categoriaAtualizada.getNome(), categoriaDTO.getDescricao());
    }

    public void excluirCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}
