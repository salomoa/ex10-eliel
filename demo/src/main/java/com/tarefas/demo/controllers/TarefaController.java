package com.tarefas.demo.controllers;

import com.tarefas.demo.model.TarefaModel;
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
        public ResponseEntity<List<TarefaModel> > buscarTodasAsTarefas() {
        List<TarefaModel> tarefaModels = tarefaServices.buscarTodasAsTarefas();
        return ResponseEntity.ok(tarefaServices.buscarTodasAsTarefas());
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
        tarefaServices.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Optional<TarefaModel> buscarTarefaPorId(@PathVariable Long id){ return tarefaServices.buscarTarefaId(id); }


    @PutMapping("/{id}")
    public ResponseEntity <TarefaModel> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaModel tarefaModel){
        TarefaModel tarefa = tarefaServices.atualizarTarefas(id, tarefaModel);
        return ResponseEntity.ok().build();
    }















}
