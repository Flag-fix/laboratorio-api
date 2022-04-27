package com.api.util;

import com.api.laboratiorio.dtos.PessoaDto;
import com.api.laboratiorio.enums.TipoSanguineoEnum;
import com.api.laboratiorio.models.CidadeModel;
import com.api.laboratiorio.models.EstadoModel;
import com.api.laboratiorio.models.PessoaModel;
import com.api.laboratiorio.services.CidadeService;
import com.api.laboratiorio.services.EstadoService;
import org.springframework.beans.BeanUtils;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Utils {


    public static int calculaIdade(final Date aniversario) {
        final LocalDate dataAtual = LocalDate.now();
        final Period periodo = Period.between(convertToLocalDateViaInstant(aniversario), dataAtual);
        return periodo.getYears();
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Double calculaIMC(PessoaModel p) {
        var peso = p.getPeso() / Math.pow(p.getAltura(), 2);
        return peso;
    }

    public static ArrayList<PessoaModel> verificaFaixaEtariaImc(List<PessoaModel> pessoas) {
        var listaPessoas = new ArrayList<PessoaModel>();
        var varAuxiliarUnidade = 1;
        var varAuxDezena = 10;
        var varAuxiliarWhile = true;
        var imcMedio = 0.0;
        var qtdPorFaixa = 0;
        while (varAuxiliarWhile) {
            for (PessoaModel p : pessoas) {
                var idade = calculaIdade(p.getData_nasc());
                var pessoaNova = new PessoaModel();
                if (idade > varAuxiliarUnidade && idade < varAuxDezena) {
                    var IMC = calculaIMC(p);
                    imcMedio += Math.round(IMC);
                    qtdPorFaixa += 1;
                }
                if (pessoas.indexOf(p) + 1 == pessoas.toArray().length) {
                    pessoaNova.setFaixaEtaria(varAuxiliarUnidade + "-" + varAuxDezena);
                    var resultado = imcMedio / qtdPorFaixa;
                    pessoaNova.setImcMedio(resultado);
                    imcMedio = 0.0;
                    qtdPorFaixa = 0;
                    listaPessoas.add(pessoaNova);
                    varAuxDezena += 10;
                    varAuxiliarUnidade += 10;
                    if (varAuxDezena == 100) {
                        varAuxiliarWhile = false;
                    }
                }
            }
        }
        return listaPessoas;
    }

    public static ArrayList<PessoaModel> verificaPersEstCidCriaObjPessoa(List<@Valid PessoaDto> pessoaDto, EstadoService estadoService, CidadeService cidadeService) {
        var pessoaModel = new ArrayList<PessoaModel>();
        var cidadeModel = new ArrayList<CidadeModel>();
        for (PessoaDto item : pessoaDto) {
            var estado = estadoService.findBySigla(item.getEstado());
            var cidade = cidadeService.findByNomeCidade(item.getCidade());
            if (estado == null) {
                var estadoNovo = new EstadoModel();
                estadoNovo.setSigla(item.getEstado());
                estado = estadoService.save(estadoNovo);
            }
            if (cidade == null) {
                estado = estadoService.findBySigla(item.getEstado());
                var cidadeNova = new CidadeModel();
                cidadeNova.setNome(item.getCidade());
                cidadeNova.setEstado(estado);
                cidade = cidadeService.save(cidadeNova);
            }
            cidade.setEstado(estado);
            var pessoaNova = new PessoaModel();
            pessoaNova.setCidade(cidade);
            cidadeModel.add(cidade);
            pessoaModel.add(pessoaNova);
            BeanUtils.copyProperties(item, pessoaNova);
        }
        return pessoaModel;
    }


    public static ArrayList<PessoaModel> calculaQtdObesoSexo(List<PessoaModel> pessoas) {
        var listaPessoas = new ArrayList<PessoaModel>();
        var qtdObesoH = 0;
        var homem = 0.0;
        var mulher = 0.0;
        var qtdObesoM = 0;
        var pessoa = new PessoaModel();
        for (PessoaModel p : pessoas) {
            var IMC = calculaIMC(p);
            if (p.getSexo().toString().equals("MASCULINO")) {
                homem += 1;
                if (IMC > 30) {
                    qtdObesoH += 1;
                }
            } else {
                if (IMC > 30) {
                    qtdObesoM += 1;
                }
                mulher += 1;
            }
        }
        pessoa.setQtdObesoF(mulher*qtdObesoM/100);
        pessoa.setQtdObesoM(homem*qtdObesoH/100);
        listaPessoas.add(pessoa);
        return listaPessoas;
    }

    public static ArrayList<PessoaModel> calculaIdadeMediaTipoSang(List<PessoaModel> pessoas) {
        var listaPessoas = new ArrayList<PessoaModel>();

        var tipSang = 1;
        var tipoSanguineo = Arrays.asList(TipoSanguineoEnum.values());
        var valorTip = "";
        for (TipoSanguineoEnum tip : tipoSanguineo) {
            valorTip = tip.getTipo_sanguineo();
            var idade = 0;
            var qtdPessoas = 0;
            var pessoaNova = new PessoaModel();
            for (PessoaModel p : pessoas) {
                if (p.getTipo_sanguineo().getTipo_sanguineo().equals(valorTip)) {
                    idade += calculaIdade(p.getData_nasc());
                    qtdPessoas += 1;
                }
                if (pessoas.indexOf(p) + 1 == pessoas.toArray().length) {
                    var media= idade / qtdPessoas;
                    pessoaNova.setIdadeMedia((double) media);
                    pessoaNova.setSangue(valorTip);
                    listaPessoas.add(pessoaNova);
                    tipSang += 1;
                }
            }
        }
        return listaPessoas;
    }

    public static ArrayList<PessoaModel> verificaQtdDoadoresTipoSangue(List<PessoaModel> pessoas) {
        var listaPessoas = new ArrayList<PessoaModel>();
        var tipoSanguineo = Arrays.asList(TipoSanguineoEnum.values());
        var valorTipSang = "";
        for (TipoSanguineoEnum tip : tipoSanguineo) {
            valorTipSang = tip.getTipo_sanguineo();
            var idade = 0;
            var qtdPessoas = 0;
            var pessoaNova = new PessoaModel();
            for (PessoaModel p : pessoas) {
                idade = calculaIdade(p.getData_nasc());
                if (idade >= 16 && idade <= 69 && p.getPeso() > 50) {
                    var sanguePessoa = p.getTipo_sanguineo().getTipo_sanguineo();
                    if (sanguePessoa.equals(valorTipSang) || sanguePessoa == "AB+") {
                        qtdPessoas += 1;
                    }else{
                        if(getDoaApos(sanguePessoa))
                            qtdPessoas += 1;
                        else if(getDoaAandBandOneg(sanguePessoa))
                            qtdPessoas += 1;
                        else if(getDoaBpos(sanguePessoa))
                            qtdPessoas += 1;
                        else if(getDoaABneg(sanguePessoa))
                            qtdPessoas += 1;
                    }
                }
                if (pessoas.indexOf(p) + 1 == pessoas.toArray().length) {
                    pessoaNova.setPossiveisDoadores(qtdPessoas);
                    pessoaNova.setSangue(valorTipSang);
                    listaPessoas.add(pessoaNova);
                }
            }
        }
        return listaPessoas;
    }

    public static boolean getDoaApos(String sanguePessoa) {
        return sanguePessoa == "A-" || sanguePessoa == "O+" || sanguePessoa == "O-";
    }

    public static boolean getDoaAandBandOneg(String sanguePessoa) {
        return sanguePessoa == "O-";
    }

    public static boolean getDoaBpos(String sanguePessoa) {
        return sanguePessoa == "B-" || sanguePessoa == "O+" || sanguePessoa == "O-";
    }

    public static boolean getDoaABneg(String sanguePessoa) {
        return sanguePessoa == "A-" || sanguePessoa == "B-" || sanguePessoa == "O-";
    }

}
