package com.naldo.springboot.backend.chat.models.services;

import com.naldo.springboot.backend.chat.models.dao.ChatRepository;
import com.naldo.springboot.backend.chat.models.documents.Mensaje;
import com.naldo.springboot.backend.chat.models.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Override
    public List<Mensaje> obtenerUltimos10Mensajes() {
        return chatRepository.findFirst10ByOrderByFechaDesc();
    }

    @Override
    public Mensaje guardar(Mensaje mensaje) {
        return chatRepository.save(mensaje);
    }
}
