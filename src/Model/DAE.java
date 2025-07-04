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

/**
 * Classe que representa um Documento de Arrecadação Estadual (DAE).
 * Estende a classe DetalhamentoGuia para incluir funcionalidades específicas do DAE.
 */
public class DAE extends DetalhamentoGuia{

    /**
     * Construtor para criar um novo DAE.
     *
     * @param id                      Identificador único do DAE
     * @param CNPJ_Empresa            CNPJ da empresa relacionada ao DAE
     * @param identificador           UUID único do documento
     * @param dataVencimento          Data de vencimento do DAE
     * @param valorTotal              Valor total do DAE
     * @param competencia             Mês e ano de competência
     * @param link                    Link para pagamento
     * @param observacoes             Observações adicionais
     * @param codComposicao           Código da composição
     * @param denominacaoDaComposicao Nome da composição
     * @param valorPrincipal          Valor principal do DAE
     * @param multa                   Valor da multa
     * @param juros                   Valor dos juros
     * @param StPg                    Status do pagamento
     */
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

    /**
     * Gera um novo DAE para uma competência e tipo específicos.
     *
     * @param comp Competência (mês e ano) do DAE
     * @param type Tipo de guia
     * @return DAE gerado
     * @throws Exception Caso ocorra algum erro na geração do DAE
     */
    public static DAE generate (YearMonth comp, GuiaTypes type) throws Exception{
        GuiaController g = new GuiaController();
        return (DAE) g.create(comp, type);
    }

    /**
     * Retorna o tipo do documento como "DAE".
     *
     * @return String contendo "DAE"
     */
    @Override
    public String getTipo() {
        return "DAE";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
