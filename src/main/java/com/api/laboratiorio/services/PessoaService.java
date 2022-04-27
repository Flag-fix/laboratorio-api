package com.api.laboratiorio.services;

import com.api.laboratiorio.models.PessoaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PessoaService {

    Page<PessoaModel> findAll(Pageable pageable);
    Optional<PessoaModel> findById(Long id);
    List<PessoaModel> saveListPessoas(ArrayList<PessoaModel> pessoaDto);
    PessoaModel save(PessoaModel pessoa);
    PessoaModel findByCpf(String cpf);
    ArrayList<PessoaModel> findAllPessoasIMC();
    ArrayList<PessoaModel> findPorcSexo();
    ArrayList<PessoaModel> findMedTipSang();
    ArrayList<PessoaModel> findDoadores();
}
