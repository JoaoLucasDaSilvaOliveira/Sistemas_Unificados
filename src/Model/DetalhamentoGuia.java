package Model;

import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import Model.ENUMS.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

/**
 * Classe abstrata que estende {@link Guia} e adiciona informações detalhadas
 * sobre a composição do valor de uma guia, como valor principal, multa e juros.
 *
 * Utilizada por guias que possuem detalhamento mais específico no conteúdo.
 */
public abstract class DetalhamentoGuia extends Guia {

    /**
     * Construtor da classe {@code DetalhamentoGuia}, utilizado pela desserialização Jackson.
     *
     * @param id                      Identificador interno da guia.
     * @param CNPJ_Empresa            CNPJ da empresa relacionada.
     * @param identificador           UUID único da guia.
     * @param dataVencimento          Data de vencimento da guia.
     * @param valorTotal              Valor total da guia.
     * @param competencia             Competência da guia.
     * @param link                    Link para pagamento.
     * @param observacoes             Observações gerais da guia.
     * @param codComposicao           Código da composição da guia.
     * @param denominacaoDaComposicao Descrição textual da composição.
     * @param valorPrincipal          Valor base/principal da guia.
     * @param multa                   Valor da multa aplicada.
     * @param juros                   Valor de juros aplicados.
     * @param StPg                    Status de pagamento da guia.
     */
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
            @JsonProperty("StPg") StatusPagamento StPg) {
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, StPg);
        this.observacoes = observacoes;
        this.codComposicao = codComposicao;
        this.denominacaoDaComposicao = denominacaoDaComposicao;
        this.valorPrincipal = valorPrincipal;
        this.multa = multa;
        this.juros = juros;
    }

    /** Observações adicionais da guia. */
    @JsonProperty("observacoes")
    private final String observacoes;

    /** Código numérico da composição da guia. */
    @JsonProperty("codComposicao")
    private final int codComposicao;

    /** Descrição textual da composição da guia. */
    @JsonProperty("denominacaoDaComposicao")
    private final String denominacaoDaComposicao;

    /** Valor principal da guia, sem multas ou juros. */
    @JsonProperty("valorPrincipal")
    private final double valorPrincipal;

    /** Valor da multa aplicada à guia. */
    @JsonProperty("multa")
    private final double multa;

    /** Valor dos juros aplicados à guia. */
    @JsonProperty("juros")
    private final double juros;

    /**
     * Retorna uma representação textual detalhada da guia,
     * incluindo informações herdadas e campos específicos desta classe.
     *
     * @return string representando a guia com detalhamento completo.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nObservacoes: " + observacoes + '\'' +
                "\nCódigo da composição: " + codComposicao +
                "\nDenominação da composição: " + denominacaoDaComposicao + '\'' +
                "\nValor principal: " + valorPrincipal +
                "\nMulta: " + multa +
                "\nJuros: " + juros;
    }
}
