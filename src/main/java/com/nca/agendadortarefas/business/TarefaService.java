package com.nca.agendadortarefas.business;

import com.nca.agendadortarefas.business.converters.TarefaConverter;
import com.nca.agendadortarefas.business.dto.TarefaDTO;
import com.nca.agendadortarefas.infrastructure.enums.StatusNotificacao;
import com.nca.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.nca.agendadortarefas.infrastructure.repositories.TarefaRepository;
import com.nca.agendadortarefas.infrastructure.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaService(TarefaRepository tarefaRepository, TarefaConverter tarefaConverter, JwtUtil jwtUtil) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaConverter = tarefaConverter;
        this.jwtUtil = jwtUtil;
    }

    public TarefaDTO salvar(TarefaDTO tarefaDTO, String token) {

        var email = jwtUtil.extractUsername(token.substring(7));
        tarefaDTO.setEmailUsuario(email);

        var tarefa = tarefaConverter.paraTarefaEntity(tarefaDTO);
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setStatus(StatusNotificacao.PENDENTE);
        var tarefaSalva = tarefaRepository.save(tarefa);
        return tarefaConverter.paraTarefaDTO(tarefaSalva);
    }

    public List<TarefaDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        var tarefas = tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal);
        return tarefaConverter.paraListaTarefaDTO(tarefas);
    }

    public List<TarefaDTO> buscarTarefasAgendadasPorEmail(String token) {
        var email = jwtUtil.extractUsername(token.substring(7));
        var tarefas = tarefaRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefaDTO(tarefas);
    }

    public void deletarPorId(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id: " + id, ex.getCause());
        }
    }

    public TarefaDTO alterarStatus(StatusNotificacao statusNotificacao, String id) {
        try {
            var tarefa = tarefaRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada com o id: " + id));
            tarefa.setStatus(statusNotificacao);
            var tarefaAtualizada = tarefaRepository.save(tarefa);
            return tarefaConverter.paraTarefaDTO(tarefaAtualizada);
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa com id: " + id, ex.getCause());
        }
    }

    public TarefaDTO alterarTarefa(TarefaDTO tarefaDTO, String id) {
        try {
            var tarefa = tarefaRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada com o id: " + id));
            tarefa = tarefaConverter.updateTarefa(tarefaDTO, tarefa);
            var tarefaAtualizada = tarefaRepository.save(tarefa);
            return tarefaConverter.paraTarefaDTO(tarefaAtualizada);
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Erro ao alterar tarefa com id: " + id, ex.getCause());
        }
    }
}
