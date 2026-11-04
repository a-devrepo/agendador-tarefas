package com.nca.agendadortarefas.controllers;

import com.nca.agendadortarefas.business.TarefaService;
import com.nca.agendadortarefas.business.dto.TarefaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> salvar(@RequestBody TarefaDTO tarefaDTO,
                                            @RequestHeader("Authorization") String token) {
        var tarefaSalva = tarefaService.salvar(tarefaDTO, token);
        return ResponseEntity.ok(tarefaSalva);
    }
}
