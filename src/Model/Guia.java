package Model;

import DAO.GuiaDAO;
import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import Model.ENUMS.StatusPagamento;
import com.fasterxml.jackson.annotation.*;

import java.io.IOException;
import java.time.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe abstrata que representa uma guia de pagamento genérica.
 * Serve como superclasse para guias específicas como {@link FGTS}, {@link DAE} e {@link DARF}.
 * Contém informações comuns a todas as guias, como CNPJ, identificador, valor, vencimento, etc.
 *
 * É serializável com Jackson, suportando a identificação do tipo concreto via o campo {@code tipo}.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FGTS.class, name = "FGTS"),
        @JsonSubTypes.Type(value = DAE.class, name = "DAE"),
        @JsonSubTypes.Type(value = DARF.class, name = "DARF")
})
public abstract class Guia {

    /**
     * Construtor completo usado para criação ou reconstrução de uma instância de {@code Guia}.
     * Utilizado principalmente durante a desserialização com Jackson.
     *
     * @param id              Identificador interno único da guia.
     * @param CNPJ_Empresa    CNPJ da empresa associada à guia.
     * @param identificador   UUID único da guia.
     * @param dataVencimento  Data de vencimento da guia.
     * @param valorTotal      Valor total a ser pago da guia.
     * @param competencia     Competência (mês e ano de referência) da guia.
     * @param link            Link para pagamento.
     * @param StPg            Status do pagamento.
     */
    @JsonCreator
    public Guia(
            @JsonProperty("id") int id,
            @JsonProperty("CNPJ_Empresa") String CNPJ_Empresa,
            @JsonProperty("identificador") UUID identificador,
            @JsonProperty("dataVencimento") @JsonFormat(pattern = "dd/MM/yy") LocalDate dataVencimento,
            @JsonProperty("valorTotal") double valorTotal,
            @JsonProperty("competencia") YearMonth competencia,
            @JsonProperty("link") LinkPagamento link,
            @JsonProperty("StPg") StatusPagamento StPg) {
        Id = id;
        this.CNPJ_Empresa = CNPJ_Empresa;
        this.identificador = identificador;
        this.dataVencimento = dataVencimento;
        this.valorTotal = valorTotal;
        this.competencia = competencia;
        this.link = link;
        this.StPg = StPg;
    }

    /** Identificador interno da guia (geralmente do banco de dados). */
    @JsonProperty("id")
    private final int Id;

    /** CNPJ da empresa relacionada à guia. */
    @JsonProperty("CNPJ_Empresa")
    private final String CNPJ_Empresa;

    /** UUID único identificador da guia. */
    @JsonProperty("identificador")
    private final UUID identificador;

    /** Data de vencimento da guia. */
    @JsonProperty("dataVencimento")
    @JsonFormat(pattern = "dd/MM/yy")
    private final LocalDate dataVencimento;

    /** Valor total da guia. */
    @JsonProperty("valorTotal")
    private final double valorTotal;

    /** Competência (mês/ano) referente à guia. */
    @JsonProperty("competencia")
    @JsonFormat(pattern = "MM/yy")
    private final YearMonth competencia;

    /** Link de pagamento da guia. */
    @JsonProperty("link")
    private final LinkPagamento link;

    /** Status do pagamento da guia. */
    @JsonProperty("StPg")
    private final StatusPagamento StPg;

    /**
     * Retorna o ID da guia.
     * @return o identificador interno.
     */
    public int getId() {
        return Id;
    }

    /**
     * Retorna o UUID da guia.
     * @return o identificador UUID.
     */
    public UUID getIdentificador() {
        return identificador;
    }

    /**
     * Retorna o CNPJ da empresa associada.
     * @return o CNPJ da empresa.
     */
    public String getCNPJ_Empresa() {
        return CNPJ_Empresa;
    }

    /**
     * Retorna a data de vencimento da guia.
     * @return data de vencimento.
     */
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    /**
     * Retorna o valor total da guia.
     * @return valor total.
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Retorna a competência da guia.
     * @return competência no formato {@code YearMonth}.
     */
    public YearMonth getCompetencia() {
        return competencia;
    }

    /**
     * Retorna o link de pagamento.
     * @return {@code LinkPagamento} da guia.
     */
    public LinkPagamento getLink() {
        return link;
    }

    /**
     * Retorna o tipo específico da guia (ex: FGTS, DAE, DARF).
     * Método abstrato que deve ser implementado pelas subclasses.
     *
     * @return tipo da guia como {@code String}.
     */
    public abstract String getTipo();

    /**
     * Compara duas guias com base em seus atributos principais.
     * @param o objeto a ser comparado.
     * @return {@code true} se forem considerados iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Guia guia)) return false;
        return Id == guia.Id &&
                Double.compare(valorTotal, guia.valorTotal) == 0 &&
                Objects.equals(CNPJ_Empresa, guia.CNPJ_Empresa) &&
                Objects.equals(identificador, guia.identificador) &&
                Objects.equals(dataVencimento, guia.dataVencimento) &&
                Objects.equals(competencia, guia.competencia) &&
                link == guia.link;
    }

    /**
     * Gera um hashCode baseado nos atributos da guia.
     * @return valor hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(Id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link);
    }

    /**
     * Retorna uma lista de guias associadas a um determinado CNPJ, usando a {@link GuiaDAO}.
     *
     * @param CNPJ CNPJ da empresa.
     * @return Lista de objetos {@code Guia} relacionados.
     * @throws IOException se ocorrer erro ao acessar os dados.
     */
    public static List<Guia> listarGuias(String CNPJ) throws IOException {
        return new GuiaDAO().searchByValue(CNPJ);
    }

    /**
     * Retorna uma representação textual da guia, contendo todos os campos principais.
     * @return representação da guia em {@code String}.
     */
    @Override
    public String toString() {
        return
                "Id: " + Id +
                        "\nCNPJ: '" + CNPJ_Empresa + '\'' +
                        "\nIdentificador: " + identificador +
                        "\nData de vencimento: " + dataVencimento +
                        "\nValor total: " + valorTotal +
                        "\nCompetência: " + competencia +
                        "\nLink: " + link +
                        "\nTipo: " + getTipo() +
                        "\nSituação: " + StPg;
    }
}
