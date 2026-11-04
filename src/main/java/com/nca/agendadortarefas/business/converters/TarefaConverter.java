package com.nca.agendadortarefas.business.converters;

import com.nca.agendadortarefas.business.dto.TarefaDTO;
import com.nca.agendadortarefas.infrastructure.entities.TarefaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<TarefaEntity> paraListaTarefaEntity(List<TarefaDTO> tarefasDTO) {
        return tarefasDTO.stream()
                .map(this::paraTarefaEntity)
                .toList();
    }

    public List<TarefaDTO> paraListaTarefaDTO(List<TarefaEntity> tarefas) {
        return tarefas.stream()
                .map(this::paraTarefaDTO)
                .toList();
    }

    public TarefaEntity updateTarefa(TarefaDTO tarefaDTO, TarefaEntity tarefa) {
        return TarefaEntity.builder()
                .id(tarefa.getId())
                .nomeTarefa(tarefaDTO.getNomeTarefa() != null ? tarefaDTO.getNomeTarefa() : tarefa.getNomeTarefa())
                .descricao(tarefaDTO.getDescricao() != null ? tarefaDTO.getDescricao() : tarefa.getDescricao())
                .dataEvento(tarefaDTO.getDataEvento() != null ? tarefaDTO.getDataEvento() : tarefa.getDataEvento())
                .emailUsuario(tarefaDTO.getEmailUsuario() != null ? tarefaDTO.getEmailUsuario() : tarefa.getEmailUsuario())
                .dataCriacao(tarefa.getDataCriacao())
                .dataAlteracao(LocalDateTime.now())
                .status(tarefa.getStatus())
                .build();
    }
}
