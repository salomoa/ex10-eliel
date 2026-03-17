package com.tarefas.demo.controllers;

import com.tarefas.demo.model.TarefaModel;
import com.tarefas.demo.repositories.TarefaRepository;
import com.tarefas.demo.services.TarefaServices;
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
    private TarefaServices tarefaServices;

    @GetMapping
        public ResponseEntity<List<TarefaModel> > findAll() {
        List<TarefaModel> tarefaModels = tarefaServices.findAll();
        return ResponseEntity.ok(tarefaServices.findAll());
        }

    @PostMapping
    public ResponseEntity<TarefaModel> criarTarefa(@RequestBody TarefaModel tarefaModel) {
        TarefaModel tarefa = tarefaServices.criarTarefa(tarefaModel);
        URI uri  = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefaModel.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping
    public ResponseEntity<TarefaModel> deletarTarefa(@RequestParam Long id){
        tarefaServices.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Optional<TarefaModel> buscarId(@PathVariable Long id){ return tarefaServices.buscarId(id); }


    @PutMapping("/{id}")
    public ResponseEntity <TarefaModel> atualizar(@PathVariable Long id, @RequestBody TarefaModel tarefaModel){
        TarefaModel tarefa = tarefaServices.atualizar(id, tarefaModel);
        URI uri;
        uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(tarefaModel.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }















}
