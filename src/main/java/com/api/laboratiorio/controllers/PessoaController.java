package com.api.laboratiorio.controllers;

import com.api.laboratiorio.dtos.PessoaDto;
import com.api.laboratiorio.models.PessoaModel;
import com.api.laboratiorio.services.CidadeService;
import com.api.laboratiorio.services.EstadoService;
import com.api.laboratiorio.services.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.api.util.Utils.verificaPersEstCidCriaObjPessoa;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController {

    final PessoaService pessoaService;
    final CidadeService cidadeService;
    final EstadoService estadoService;


    @PostMapping
    public ResponseEntity<Object> savePessoa(@RequestBody List<@Valid PessoaDto> pessoaDto) {
        ArrayList<PessoaModel> pessoaModel = verificaPersEstCidCriaObjPessoa(pessoaDto,estadoService,cidadeService);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.saveListPessoas(pessoaModel));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaModel>> getAllPessoas(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
                    Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value = "id") Long id) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        return pessoaModelOptional.<ResponseEntity<Object>>map(
                        pessoaModel -> ResponseEntity.status(HttpStatus.OK).body(pessoaModel))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa Não encontrada."));
    }

    @GetMapping("/imc")
    public ResponseEntity<Object> getImcPessoaIdade() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAllPessoasIMC());
    }

    @GetMapping("/obesidade")
    public ResponseEntity<Object> getQtdObesidadeSexo() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findPorcSexo());
    }

    @GetMapping("/tipo-sanguineo")
    public ResponseEntity<Object> getMediaTipSang() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findMedTipSang());
    }

    @GetMapping("/doadores")
    public ResponseEntity<Object> getDoadoresSangue() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findDoadores());
    }

}
