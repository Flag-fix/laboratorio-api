package com.api.laboratiorio.services;

import com.api.laboratiorio.dtos.PessoaDto;
import com.api.laboratiorio.models.PessoaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaService {

    Page<PessoaModel> findAll(Pageable pageable);
    Optional<PessoaModel> findById(Long id);
    List<PessoaModel> saveListPessoas(ArrayList<PessoaModel> pessoaDto);
    PessoaModel save(PessoaModel pessoa);
    PessoaModel findByCpf(String cpf);
}
