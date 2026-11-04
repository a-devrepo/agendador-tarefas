package com.nca.agendadortarefas.infrastructure.repositories;

import com.nca.agendadortarefas.infrastructure.entities.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {
}
