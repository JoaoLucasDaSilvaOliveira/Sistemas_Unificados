package Model;

import Control.GuiaController;
import DAO.GuiaDAO;
import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class FGTS extends Guia{

    //não usar normalmente!!! uso apenas da classe DAO
    @JsonCreator
    public FGTS(
               @JsonProperty("id") int id,
               @JsonProperty("cnpj_empresa") String CNPJ_Empresa,
               @JsonProperty("identificador") UUID identificador,
               @JsonProperty("datavencimento") LocalDate dataVencimento,
               @JsonProperty("valortotal") double valorTotal,
               @JsonProperty("competencia") YearMonth competencia,
               @JsonProperty("link") LinkPagamento link,
               @JsonProperty("type") GuiaTypes type,
               @JsonProperty("econsignado") Map<String, Double> eConsignado,
               @JsonProperty("funcionariosdaguia") Map<String, String> funcionariosDaGuia,
               @JsonProperty("valoresporfuncionario") Map<String, Double> valoresPorFuncionario) {
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, type);
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
    @JsonProperty("valoresporfuncionario")
    private final Map<String, Double> valoresPorFuncionario;

    //CPF, Nome
    @JsonProperty("funcionariosdaguia")
    private final Map<String, String> funcionariosDaGuia;

    //CPF, Valor da guia
    @JsonProperty("econsignado")
    private final Map<String, Double> eConsignado;
}
