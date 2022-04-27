package com.api.laboratiorio.repositories;

import com.api.laboratiorio.dtos.EstadoDTO;
import com.api.laboratiorio.models.EstadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {

    boolean existsBySigla(String sigla);

    EstadoModel findBySigla(String sigla);

    EstadoModel findBySiglaContainingIgnoreCase(String sigla);

    @Query("SELECT new com.api.laboratiorio.dtos.EstadoDTO(COUNT (p.nome), e.sigla) FROM PessoaModel p  JOIN CidadeModel c ON c.id = p.cidade JOIN EstadoModel e ON e.id = c.estado GROUP BY e.id")
    ArrayList<EstadoDTO> findByQtdPorEstado();
}
