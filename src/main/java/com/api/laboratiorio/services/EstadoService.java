package com.api.laboratiorio.services;

import com.api.laboratiorio.models.EstadoModel;

import java.util.ArrayList;
import java.util.List;

public interface EstadoService {

    EstadoModel findAll();

    EstadoModel findById(Integer id);

    EstadoModel save(EstadoModel pessoa);

    EstadoModel findBySigla(String sigla);

    List<EstadoModel> saveAll(ArrayList<EstadoModel> estado);

}
