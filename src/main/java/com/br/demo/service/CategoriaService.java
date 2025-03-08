package com.br.demo.service;

import com.br.demo.dto.request.CategoriaRequestDTO;
import com.br.demo.dto.response.CategoriaResponseDTO;
import com.br.demo.model.Categoria;
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

    public List<CategoriaResponseDTO> listarCategorias(){
        return categoriaRepository.findAll().stream()
                .map(categoria -> new CategoriaResponseDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao()))
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO categoriaRequestDTO){
        Categoria novaCategoria = new Categoria(null, categoriaRequestDTO.getNome(), categoriaRequestDTO.getDescricao());
        Categoria categoriaSalva = categoriaRepository.save(novaCategoria);
        return new CategoriaResponseDTO(categoriaSalva.getId(), categoriaSalva.getNome(), categoriaSalva.getDescricao());
    }

    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO categoriaRequestDTO){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoriaExistente.setNome(categoriaRequestDTO.getNome());
        categoriaExistente.setDescricao(categoriaRequestDTO.getDescricao());

        Categoria categoriaAtualizada = categoriaRepository.update(categoriaExistente);

        return new CategoriaResponseDTO(categoriaAtualizada.getId(), categoriaAtualizada.getNome(), categoriaAtualizada.getDescricao());
    }

    public void excluirCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}
