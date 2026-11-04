package com.nca.agendadortarefas.business;

import com.nca.agendadortarefas.business.converters.TarefaConverter;
import com.nca.agendadortarefas.business.dto.TarefaDTO;
import com.nca.agendadortarefas.infrastructure.enums.StatusNotificacao;
import com.nca.agendadortarefas.infrastructure.repositories.TarefaRepository;
import com.nca.agendadortarefas.infrastructure.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        tarefaDTO.setStatus(StatusNotificacao.PENDENTE);
        tarefaDTO.setEmailUsuario(email);

        var tarefa = tarefaConverter.paraTarefaEntity(tarefaDTO);
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setStatus(StatusNotificacao.PENDENTE);
        var tarefaSalva = tarefaRepository.save(tarefa);
        return tarefaConverter.paraTarefaDTO(tarefaSalva);
    }
}
