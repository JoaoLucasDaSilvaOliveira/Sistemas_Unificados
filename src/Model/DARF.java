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
 * Classe que representa um Documento de Arrecadação de Receitas Federais (DARF).
 * Estende a classe DetalhamentoGuia para herdar atributos e comportamentos comuns.
 */
public class DARF extends DetalhamentoGuia{
    /**
     * Construtor para criar uma nova instância de DARF.
     *
     * @param id                      Identificador único do DARF
     * @param CNPJ_Empresa            CNPJ da empresa associada
     * @param identificador           UUID único do documento
     * @param dataVencimento          Data de vencimento do DARF
     * @param valorTotal              Valor total do documento
     * @param competencia             Mês e ano de competência
     * @param link                    Link para pagamento
     * @param observacoes             Observações adicionais
     * @param codComposicao           Código da composição
     * @param denominacaoDaComposicao Nome da composição
     * @param valorPrincipal          Valor principal
     * @param multa                   Valor da multa
     * @param juros                   Valor dos juros
     * @param StPg                    Status do pagamento
     */
    @JsonCreator
    public DARF(
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
     * Gera um novo DARF para a competência e tipo especificados.
     *
     * @param comp Competência (mês e ano) do DARF
     * @param type Tipo da guia
     * @return DARF gerado
     * @throws Exception Se houver erro na geração do DARF
     */
    public static DARF generate (YearMonth comp, GuiaTypes type) throws Exception{
        GuiaController g = new GuiaController();
        return (DARF) g.create(comp, type);
    }

    /**
     * Retorna o tipo do documento como "DARF".
     *
     * @return String contendo "DARF"
     */
    @Override
    public String getTipo() {
        return "DARF";
    }

    /**
     * Retorna uma representação em String do DARF.
     *
     * @return String com os detalhes do DARF
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
