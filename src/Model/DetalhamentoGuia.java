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
    @Override
    public String toString() {
        return super.toString()+
                "\nObservacoes: " + observacoes + '\'' +
                "\nCódigo da composição: " + codComposicao +
                "\nDenominação da composição: " + denominacaoDaComposicao + '\'' +
                "\nValor principal: " + valorPrincipal +
                "\nMulta: " + multa +
                "\nJuros: " + juros;
    }

    @JsonCreator
    public DetalhamentoGuia(
           @JsonProperty("id") int id,
           @JsonProperty("CNPJ_Empresa") String CNPJ_Empresa,
           @JsonProperty("identificador") UUID identificador,
           @JsonProperty("dataVencimento") LocalDate dataVencimento,
           @JsonProperty("valorTotal") double valorTotal,
           @JsonProperty("competencia") YearMonth competencia,
           @JsonProperty("link") LinkPagamento link,
           @JsonProperty("observacoes") String observacoes,
           @JsonProperty("codComposicao") int codComposicao,
           @JsonProperty("denominacaoDaComposicao") String denominacaoDaComposicao,
           @JsonProperty("valorPrincipal") double valorPrincipal,
           @JsonProperty("multa") double multa,
           @JsonProperty("juros") double juros,
           @JsonProperty("StPg") StatusPagamento StPg){
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, StPg);
        this.observacoes = observacoes;
        this.codComposicao = codComposicao;
        this.denominacaoDaComposicao = denominacaoDaComposicao;
        this.valorPrincipal = valorPrincipal;
        this.multa = multa;
        this.juros = juros;
    }

    @JsonProperty("observacoes")
    private final String observacoes;
    @JsonProperty("codComposicao")
    private final int codComposicao;
    @JsonProperty("denominacaoDaComposicao")
    private final String denominacaoDaComposicao;
    @JsonProperty("valorPrincipal")
    private final double valorPrincipal;
    @JsonProperty("multa")
    private final double multa;
    @JsonProperty("juros")
    private final double juros;
}
