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
import java.util.UUID;

public class DAE extends DetalhamentoGuia{

    @JsonCreator
    public DAE(
            @JsonProperty("id") int id,
            @JsonProperty("CNPJ_Empresa") String CNPJ_Empresa,
            @JsonProperty("identificador") UUID identificador,
            @JsonProperty("dataVencimento") @JsonFormat(pattern = "dd/MM/yy") LocalDate dataVencimento,
            @JsonProperty("valorTotal") double valorTotal,
            @JsonProperty("competencia") YearMonth competencia,
            @JsonProperty("link") LinkPagamento link,
            @JsonProperty("observacoes") String observacoes,
            @JsonProperty("codComposicao") int codComposicao,
            @JsonProperty("denominacaoDaComposicao") String denominacaoDaComposicao,
            @JsonProperty("valorPrincipal") double valorPrincipal,
            @JsonProperty("multa") double multa,
            @JsonProperty("juros") double juros,
            @JsonProperty("StPg") StatusPagamento StPg) {
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, observacoes, codComposicao, denominacaoDaComposicao, valorPrincipal, multa, juros, StPg);
    }

    public static DAE generate (YearMonth comp, GuiaTypes type) throws Exception{
        GuiaController g = new GuiaController();
        return (DAE) g.create(comp, type);
    }

    @Override
    public String getTipo() {
        return "DAE";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
