package Model;

import Control.GuiaController;
import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import Model.ENUMS.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class FGTS extends Guia{

    //não usar normalmente!!! uso apenas da classe DAO
    @JsonCreator
    public FGTS(
            @JsonProperty("id") int id,
            @JsonProperty("CNPJ_Empresa") String CNPJ_Empresa,
            @JsonProperty("identificador") UUID identificador,
            @JsonProperty("dataVencimento") @JsonFormat(pattern = "dd/MM/yy") LocalDate dataVencimento,
            @JsonProperty("valorTotal") double valorTotal,
            @JsonProperty("competencia") YearMonth competencia,
            @JsonProperty("link") LinkPagamento link,
            @JsonProperty("eConsignado") Map<String, Double> eConsignado,
            @JsonProperty("funcionariosDaGuia") Map<String, String> funcionariosDaGuia,
            @JsonProperty("valoresPorFuncionario") Map<String, Double> valoresPorFuncionario,
            @JsonProperty("status") StatusPagamento status) {
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, status);
        this.eConsignado = eConsignado;
        this.funcionariosDaGuia = funcionariosDaGuia;
        this.valoresPorFuncionario = valoresPorFuncionario;
    }


    public static FGTS generate (YearMonth comp, GuiaTypes type) throws Exception{
        GuiaController g = new GuiaController();
        return (FGTS) g.create(comp, type);
    }

    //TODO:
    /*createGuide("04/25"); -> DAO retorne a guia que tenha o campo competencia igual a 04/25
     throw Exception para tratar depois
    */
    //Nome, Valores
    @JsonProperty("valoresPorFuncionario")
    private final Map<String, Double> valoresPorFuncionario;

    //CPF, Nome
    @JsonProperty("funcionariosDaGuia")
    private final Map<String, String> funcionariosDaGuia;

    //CPF, Valor da guia
    @JsonProperty("eConsignado")
    private final Map<String, Double> eConsignado;

    @Override
    public String getTipo() {
        return "FGTS";
    }
}
