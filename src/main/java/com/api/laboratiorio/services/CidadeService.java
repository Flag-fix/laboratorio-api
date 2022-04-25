package com.api.laboratiorio.services;

import com.api.laboratiorio.models.CidadeModel;
import com.api.laboratiorio.models.PessoaModel;

import java.util.ArrayList;
import java.util.List;

public interface CidadeService {

    CidadeModel findAll();

    CidadeModel findById(Integer id);

    List<CidadeModel> saveAll(ArrayList<CidadeModel> cidade);

    CidadeModel save(CidadeModel cidade);

    CidadeModel findByNomeCidade(String nome);
}
