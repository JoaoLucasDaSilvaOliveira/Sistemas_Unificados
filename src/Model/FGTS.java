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

/**
 * Representa uma guia do tipo FGTS (Fundo de Garantia do Tempo de Serviço).
 * Herda os atributos e comportamentos da classe abstrata {@link Guia}.
 * Contém informações adicionais como os funcionários relacionados e os valores consignados.
 */
public class FGTS extends Guia {

    /**
     * Construtor utilizado principalmente pela camada DAO para reconstrução de objetos via JSON.
     *
     * @param id                    Identificador interno da guia.
     * @param CNPJ_Empresa          CNPJ da empresa associada.
     * @param identificador         Identificador UUID único da guia.
     * @param dataVencimento        Data de vencimento da guia.
     * @param valorTotal            Valor total da guia.
     * @param competencia           Competência da guia (mês e ano de referência).
     * @param link                  Link de pagamento da guia.
     * @param eConsignado           Mapa contendo CPF e o valor consignado para cada funcionário.
     * @param funcionariosDaGuia    Mapa contendo CPF e nome dos funcionários incluídos na guia.
     * @param valoresPorFuncionario Mapa contendo nome do funcionário e o respectivo valor da guia.
     * @param status                Status de pagamento da guia.
     */
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

    /**
     * Gera uma nova instância de {@code FGTS} a partir da competência e tipo de guia informados,
     * utilizando a lógica da {@link GuiaController}.
     *
     * @param comp Competência (mês/ano) da guia a ser gerada.
     * @param type Tipo de guia (deve ser {@code FGTS}).
     * @return Uma nova instância de {@code FGTS}.
     * @throws Exception Caso ocorra erro na criação da guia.
     */
    public static FGTS generate(YearMonth comp, GuiaTypes type) throws Exception {
        GuiaController g = new GuiaController();
        return (FGTS) g.create(comp, type);
    }

    /**
     * Mapa que associa o nome dos funcionários ao valor total que lhes cabe na guia.
     */
    @JsonProperty("valoresPorFuncionario")
    private final Map<String, Double> valoresPorFuncionario;

    /**
     * Mapa que associa o CPF dos funcionários aos seus respectivos nomes.
     */
    @JsonProperty("funcionariosDaGuia")
    private final Map<String, String> funcionariosDaGuia;

    /**
     * Mapa que associa o CPF dos funcionários ao valor consignado a ser pago.
     */
    @JsonProperty("eConsignado")
    private final Map<String, Double> eConsignado;

    /**
     * Retorna o tipo da guia como uma string.
     *
     * @return Uma string "FGTS".
     */
    @Override
    public String getTipo() {
        return "FGTS";
    }

    /**
     * Retorna uma representação textual da guia FGTS, incluindo os dados herdados e os específicos desta classe.
     *
     * @return Representação em texto da guia.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nValores por funcionário: " + valoresPorFuncionario +
                "\nFuncionários da guia: " + funcionariosDaGuia +
                "\neConsignado: " + eConsignado;
    }
}
