package com.api.laboratiorio.controllers;

import com.api.laboratiorio.services.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/estado")
@AllArgsConstructor
public class EstadoController {

    final EstadoService estadoService;


    @GetMapping
    public ResponseEntity<Object> getAllPessoasEstados() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.getAllPessoasEstados());
    }

}
