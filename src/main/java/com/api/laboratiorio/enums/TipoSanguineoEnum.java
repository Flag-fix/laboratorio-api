package com.api.laboratiorio.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoSanguineoEnum {
    A_Positivo("A+"),
    A_Negativo("A-"),
    B_Positivo("B+"),
    B_Negativo("B-"),
    AB_Positivo("AB+"),
    AB_Negativo("AB-"),
    O_Positivo("O+"),
    O_Negativo("O-");

    private String tipo_sanguineo;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    static TipoSanguineoEnum findValue(@JsonProperty("tipo_sanguineo") String tipo_sanguineo) {
        return Arrays.stream(TipoSanguineoEnum.values()).filter(pt -> pt.tipo_sanguineo.equals(tipo_sanguineo)).findFirst().get();
    }

}
