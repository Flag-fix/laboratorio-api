package com.api.laboratiorio.services.impl;

import com.api.laboratiorio.dtos.EstadoDTO;
import com.api.laboratiorio.models.EstadoModel;
import com.api.laboratiorio.repositories.EstadoRepository;
import com.api.laboratiorio.services.EstadoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EstadoServiceImpl.class);

    final
    EstadoRepository estadoRepository;

    @Override
    public EstadoModel findAll() {
        return null;
    }

    @Override
    public EstadoModel findById(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public EstadoModel save(EstadoModel estado) {
        return estadoRepository.save(estado);
    }

    @Override
    @Transactional
    public List<EstadoModel> saveAll(ArrayList<EstadoModel> estado) {
        try {
            return estadoRepository.saveAll(estado);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            throw e;
        }
    }

    @Override
    public List<EstadoDTO> getAllPessoasEstados() {
        LOGGER.info("Buscando quantidade de pessoas por estado");
        return estadoRepository.findByQtdPorEstado();
    }

    @Override
    public EstadoModel findBySigla(String sigla) {
        return estadoRepository.findBySiglaContainingIgnoreCase(sigla);
    }

}
