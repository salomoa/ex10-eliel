package com.tarefas.demo.controllers;

import com.tarefas.demo.model.TarefaModel;
import com.tarefas.demo.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/tarefas", "/tarefas/"})
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
        public ResponseEntity<List<TarefaModel> > findAll() {
        List<TarefaModel> tarefaModels = tarefaRepository.findAll();
        return ResponseEntity.ok(tarefaRepository.findAll());
        }

    @PostMapping
    public ResponseEntity<TarefaModel> criarTarefa(@RequestBody TarefaModel tarefaModel) {
        TarefaModel tarefa = tarefaRepository.save(tarefaModel);
        URI uri  = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefaModel.getId())
                .toUri();
        return ResponseEntity.created(uri).build(tarefa);
    }

    @DeleteMapping
    public ResponseEntity<TarefaModel> deletarTarefa(@RequestParam Long id){
        tarefaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Optional<TarefaModel> buscarId(@PathVariable Long id){ return tarefaRepository.findById(id); }


    @PutMapping("/{id}")
    public ResponseEntity <TarefaModel> atualizar(@PathVariable Long id, @RequestBody TarefaModel tarefaModel){
        TarefaModel tarefa = tarefaRepository.atualizar(id, tarefaModel);
        URI uri = ServletUriComponentsBuilder.fromRequestUri()
                .path("/{id}")
                .buildAndExpand(TarefaModel.getId())
                .toUri();
        return ResponseEntity.created(uri).build(tarefa);
    }















}
