package com.api.laboratiorio.repositories;

import com.api.laboratiorio.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
    boolean existsByCpf(String cpf);

    PessoaModel findByCpfContainingIgnoreCase(String cpf);
}
