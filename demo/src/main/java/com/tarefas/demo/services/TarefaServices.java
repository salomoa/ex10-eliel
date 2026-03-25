package com.tarefas.demo.services;

import com.tarefas.demo.model.TarefaModel;
import com.tarefas.demo.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TarefaServices {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<TarefaModel> buscarTodasAsTarefas() { return tarefaRepository.findAll() ;}

    public TarefaModel criarTarefa(TarefaModel tarefaModel) { return tarefaRepository.save(tarefaModel);}

    public Optional<TarefaModel> buscarTarefaId(Long id) { return tarefaRepository.findById(id); }

    public TarefaModel atualizarTarefas(Long id, TarefaModel tarefaModel) {
        TarefaModel model =  tarefaRepository.findById(id).get();
        model.setDescricao(tarefaModel.getDescricao());
        model.setDataVencimento(tarefaModel.getDataVencimento());
        model.setConcluida(tarefaModel.getConcluida());
        return tarefaRepository.save(model);
    }

    public void excluirTarefa(Long id) { tarefaRepository.deleteById(id); }
}
