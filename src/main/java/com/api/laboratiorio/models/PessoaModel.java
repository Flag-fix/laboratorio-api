package com.api.laboratiorio.models;

import com.api.laboratiorio.enums.SexoEnum;
import com.api.laboratiorio.enums.TipoSanguineoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
public class PessoaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false,unique=true)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(columnDefinition = "DATE")
    private Date data_nasc;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Column
    private String mae;

    @Column
    private String pai;

    @Column(nullable = false)
    private String email;

    @Column
    private String cep;

    @Column
    private String endereco;

    @Column
    private int numero;

    @Column
    private String bairro;

    @ManyToOne
    @JoinColumn(name="cidade_id")
    private CidadeModel cidade;

    @Column
    private String telefone_fixo;

    @Column
    private String celular;

    @Column(nullable = false)
    private double altura;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSanguineoEnum tipo_sanguineo;

}
