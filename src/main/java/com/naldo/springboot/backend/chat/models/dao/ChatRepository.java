package com.naldo.springboot.backend.chat.models.dao;

import com.naldo.springboot.backend.chat.models.documents.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ChatRepository extends MongoRepository<Mensaje,String> {
    public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
