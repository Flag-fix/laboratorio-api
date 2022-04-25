package com.api.laboratiorio.services.impl;

import com.api.laboratiorio.models.CidadeModel;
import com.api.laboratiorio.repositories.CidadeRepository;
import com.api.laboratiorio.services.CidadeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CidadeServiceImpl implements CidadeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CidadeServiceImpl.class);

    final
    CidadeRepository cidadeRepository;


    @Override
    public CidadeModel findAll() {
        return null;
    }

    @Override
    public CidadeModel findById(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public List<CidadeModel> saveAll(ArrayList<CidadeModel> cidade) {
        try {
            return cidadeRepository.saveAll(cidade);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public CidadeModel save(CidadeModel cidade) {
        return cidadeRepository.save(cidade);
    }

    @Override
    public CidadeModel findByNomeCidade(String nome) {
        return cidadeRepository.findByNomeContainingIgnoreCase(nome);
    }

}
