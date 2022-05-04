package com.naldo.springboot.backend.chat.controllers;
import com.naldo.springboot.backend.chat.models.documents.Mensaje;
import com.naldo.springboot.backend.chat.models.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    private String[] colores = {"red","green","blue","magenta","purple","orange"};

    @Autowired
    ChatService chatService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());
        if (mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("nuevo usuario");

        }else{
            chatService.guardar(mensaje);
        }
        //mensaje.setTexto("Recibido por el broker: "+mensaje.getTexto());
        return mensaje;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username){
        return   username.concat(" está escribiendo ...");
    }

    @MessageMapping("/historial")
    public void historial(String clienteId){
        webSocket.convertAndSend("/chat/historial/"+clienteId,chatService.obtenerUltimos10Mensajes());
    }
}
