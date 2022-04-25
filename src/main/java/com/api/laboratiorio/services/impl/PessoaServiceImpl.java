package com.api.laboratiorio.services.impl;

import com.api.laboratiorio.models.CidadeModel;
import com.api.laboratiorio.models.EstadoModel;
import com.api.laboratiorio.models.PessoaModel;
import com.api.laboratiorio.repositories.PessoaRepository;
import com.api.laboratiorio.services.PessoaService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaServiceImpl.class);

    final
    PessoaRepository pessoaRepository;
    final
    CidadeServiceImpl cidadeService;
    final
    EstadoServiceImpl estadoService;

    @Override
    public Page<PessoaModel> findAll(Pageable pageable) {
        LOGGER.info("Buscando Todas Pessoas");
        return pessoaRepository.findAll(pageable);
    }

    public Optional<PessoaModel> findById(Long id) {
        LOGGER.info("Buscando Pessoa pelo ID: {}", id);
        Optional<PessoaModel> pessoa = pessoaRepository.findById(id);
        if(isNull(pessoa)) {
            throw new RuntimeException("Pessoa não Encontrada");
        }
        return pessoa;
    }

    @Override
    public List<PessoaModel> saveListPessoas(ArrayList<PessoaModel> pessoaDto) {
        try {
            var retornoPessoas = new ArrayList<PessoaModel>();
            for (PessoaModel item : pessoaDto) {
                LOGGER.info("Buscando Pessoa");
                var pessoa = findByCpf(item.getCpf());
                if (pessoa == null) {
                    pessoa = pessoaRepository.save(item);
                    retornoPessoas.add(pessoa);
                }
            }
            return retornoPessoas;
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public PessoaModel save(PessoaModel pessoa) {
        LOGGER.info("Salvando Pessoa");
        return pessoaRepository.save(pessoa);
    }

    @Override
    public PessoaModel findByCpf(String cpf) {
        LOGGER.info("Buscando Pessoa pelo CPF: {}", cpf);
        return pessoaRepository.findByCpfContainingIgnoreCase(cpf);
    }
}
