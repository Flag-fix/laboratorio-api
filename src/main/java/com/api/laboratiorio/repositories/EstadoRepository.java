package com.api.laboratiorio.repositories;

import com.api.laboratiorio.models.CidadeModel;
import com.api.laboratiorio.models.EstadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {

    boolean existsBySigla(String sigla);

    EstadoModel findBySigla(String sigla);

    EstadoModel findBySiglaContainingIgnoreCase(String sigla);
}
