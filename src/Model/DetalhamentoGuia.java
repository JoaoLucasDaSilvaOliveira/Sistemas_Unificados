package Model;

import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import Model.ENUMS.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public abstract class DetalhamentoGuia extends Guia{
    @JsonCreator
    public DetalhamentoGuia(
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
           @JsonProperty("StPg") StatusPagamento StPg){
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, type, StPg);
        this.observacoes = observacoes;
        this.codComposicao = codComposicao;
        this.denominacaoDaComposicao = denominacaoDaComposicao;
        this.valorPrincipal = valorPrincipal;
        this.multa = multa;
        this.juros = juros;
    }

    @JsonProperty("observacoes")
    private final String observacoes;
    @JsonProperty("codcomposicao")
    private final int codComposicao;
    @JsonProperty("denominacaodacomposicao")
    private final String denominacaoDaComposicao;
    @JsonProperty("valorprincipal")
    private final double valorPrincipal;
    @JsonProperty("multa")
    private final double multa;
    @JsonProperty("juros")
    private final double juros;
}
