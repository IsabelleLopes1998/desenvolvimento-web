package com.br.demo.repository;

import com.br.demo.model.Categoria;
import com.br.demo.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository {
    private static List<Categoria> categorias = new ArrayList<>();
    private Long nextId = 1L;

    public List<Categoria> findAll(){
        return categorias;
    }

    public Optional<Categoria> findById(Long id){
        return categorias.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Categoria save(Categoria categoria){
        categoria.setId(nextId++);
        categorias.add(categoria);
        return categoria;
    }

    public Categoria update(Categoria categoria){
        return categorias.stream()
                .filter(Categoria -> categoria.getId().equals(categoria.getId()))
                .findFirst()
                .map(Categotia ->{
                    categoria.setId(categoria.getId());
                    categoria.setNome(categoria.getNome());
                    categoria.setDescricao(categoria.getDescricao());
                    return categoria;
                }).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public void deleteById(Long id){
        categorias.removeIf(categoria -> categoria.getId().equals(id));
    }
}
