package com.nca.agendadortarefas.business.converters;

import com.nca.agendadortarefas.business.dto.TarefaDTO;
import com.nca.agendadortarefas.infrastructure.entities.TarefaEntity;
import org.springframework.stereotype.Component;

@Component
public class TarefaConverter {

    public TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO) {
        return TarefaEntity.builder()
                .nomeTarefa(tarefaDTO.getNomeTarefa())
                .descricao(tarefaDTO.getDescricao())
                .dataEvento(tarefaDTO.getDataEvento())
                .emailUsuario(tarefaDTO.getEmailUsuario())
                .build();
    }

    public TarefaDTO paraTarefaDTO(TarefaEntity tarefa) {
        return TarefaDTO.builder()
                .id(tarefa.getId())
                .nomeTarefa(tarefa.getNomeTarefa())
                .descricao(tarefa.getDescricao())
                .dataEvento(tarefa.getDataEvento())
                .emailUsuario(tarefa.getEmailUsuario())
                .dataCriacao(tarefa.getDataCriacao())
                .status(tarefa.getStatus())
                .build();
    }
}
