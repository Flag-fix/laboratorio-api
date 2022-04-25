package com.api.laboratiorio.repositories;

import com.api.laboratiorio.models.CidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {

    boolean existsByNome(String nome);

    List<CidadeModel> findByNome(String nome);

    CidadeModel findByNomeContainingIgnoreCase(String nome);
}
