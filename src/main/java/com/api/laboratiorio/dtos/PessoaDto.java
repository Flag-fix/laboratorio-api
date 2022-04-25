package com.api.laboratiorio.dtos;

import com.api.laboratiorio.enums.SexoEnum;
import com.api.laboratiorio.enums.TipoSanguineoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class PessoaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date data_nasc;

    @NotBlank
    private SexoEnum sexo;

    private String mae;

    private String pai;

    @NotBlank
    private String email;

    private String cep;

    private String endereco;

    private int numero;

    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    private String telefone_fixo;

    private String celular;

    @NotBlank
    private double altura;

    @NotBlank
    private double peso;

    @NotBlank
    private TipoSanguineoEnum tipo_sanguineo;


}
