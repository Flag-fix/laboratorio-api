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
public enum SexoEnum {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String sexo;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    static SexoEnum findValue(@JsonProperty("sexo") String sexo) {
        return Arrays.stream(SexoEnum.values()).filter(pt -> pt.sexo.equals(sexo)).findFirst().get();
    }

}
