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

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FGTS.class, name = "FGTS"),
        @JsonSubTypes.Type(value = DAE.class, name = "DAE"),
        @JsonSubTypes.Type(value = DARF.class, name = "DARF")
})
public abstract class Guia {

    //o id vai ter que vir pelas classes filhas, por isso a atribuição direta sem validação com o bloco static
    //como essa classe é apenas para atribuição, não haverá a necessidade de uma GuiaController!
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
    @JsonProperty("id")
    private final int Id;
    @JsonProperty("CNPJ_Empresa")
    private final String CNPJ_Empresa;
    @JsonProperty("identificador")
    private final UUID identificador;
    @JsonProperty("dataVencimento")
    @JsonFormat(pattern = "dd/MM/yy")
    private final LocalDate dataVencimento;
    @JsonProperty("valorTotal")
    private final double valorTotal;
    @JsonProperty("competencia")
    @JsonFormat(pattern = "MM/yy")
    private final YearMonth competencia;
    @JsonProperty("link")
    private final LinkPagamento link;
    @JsonProperty("StPg")
    private final StatusPagamento StPg;

    public int getId() {
        return Id;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public String getCNPJ_Empresa() {
        return CNPJ_Empresa;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public YearMonth getCompetencia() {
        return competencia;
    }

    public LinkPagamento getLink() {
        return link;
    }

    public abstract String getTipo();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Guia guia)) return false;
        return Id == guia.Id && Double.compare(valorTotal, guia.valorTotal) == 0 && Objects.equals(CNPJ_Empresa, guia.CNPJ_Empresa) && Objects.equals(identificador, guia.identificador) && Objects.equals(dataVencimento, guia.dataVencimento) && Objects.equals(competencia, guia.competencia) && link == guia.link;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link);
    }

    public static List<Guia> listarGuias (String CNPJ) throws IOException {
        return new GuiaDAO().searchByValue(CNPJ);
    }

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
