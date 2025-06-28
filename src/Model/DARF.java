package Model;

import Control.GuiaController;
import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import Model.ENUMS.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public class DARF extends DetalhamentoGuia{
    @JsonCreator
    public DARF(
            @JsonProperty("id") int id,
            @JsonProperty("cnpj_empresa") String CNPJ_Empresa,
            @JsonProperty("identificador") UUID identificador,
            @JsonProperty("datavencimento") LocalDate dataVencimento,
            @JsonProperty("valortotal") double valorTotal,
            @JsonProperty("competencia") YearMonth competencia,
            @JsonProperty("link") LinkPagamento link,
            @JsonProperty("type") GuiaTypes type,
            @JsonProperty("observacoes") String observacoes,
            @JsonProperty("codcomposicao") int codComposicao,
            @JsonProperty("denominacaodacomposicao") String denominacaoDaComposicao,
            @JsonProperty("valorprincipal") double valorPrincipal,
            @JsonProperty("multa") double multa,
            @JsonProperty("juros") double juros,
            @JsonProperty("StPg") StatusPagamento StPg) {
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, type, observacoes, codComposicao, denominacaoDaComposicao, valorPrincipal, multa, juros, StPg);
    }

    public static DARF generate (YearMonth comp, GuiaTypes type) throws Exception{
        GuiaController g = new GuiaController();
        return (DARF) g.create(comp, type);
    }
}
