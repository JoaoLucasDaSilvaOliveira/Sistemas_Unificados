package Model;

import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,      // Usa o nome da subclasse
        include = JsonTypeInfo.As.PROPERTY, // Adiciona uma propriedade no JSON
        property = "@guiatipo"                // Nome da propriedade no JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DARF.class, name = "DetalhamentoGuia"),
        @JsonSubTypes.Type(value = FGTS.class, name = "FGTS")
})
public abstract class Guia {

    //o id vai ter que vir pelas classes filhas, por isso a atribuição direta sem validação com o bloco static
    //como essa classe é apenas para atribuição, não haverá a necessidade de uma GuiaController!

    @JsonCreator
    public Guia(
           @JsonProperty("id") int id,
           @JsonProperty("cnpj_empresa") String CNPJ_Empresa,
           @JsonProperty("identificador") UUID identificador,
           @JsonProperty("datavencimento") LocalDate dataVencimento,
           @JsonProperty("valortotal") double valorTotal,
           @JsonProperty("competencia") YearMonth competencia,
           @JsonProperty("link") LinkPagamento link,
           @JsonProperty("type") GuiaTypes type) {
        Id = ++id;
        this.CNPJ_Empresa = CNPJ_Empresa;
        this.identificador = identificador;
        this.dataVencimento = dataVencimento;
        this.valorTotal = valorTotal;
        this.competencia = competencia;
        this.link = link;
        this.tipo = type;
    }
    @JsonProperty("id")
    private final int Id;
    @JsonProperty("cnpj_empresa")
    private final String CNPJ_Empresa;
    @JsonProperty("identificador")
    private final UUID identificador;
    @JsonProperty("datavencimento")
    private final LocalDate dataVencimento;
    @JsonProperty("valortotal")
    private final double valorTotal;
    @JsonProperty("competencia")
    private final YearMonth competencia;
    @JsonProperty("link")
    private final LinkPagamento link;
    @JsonProperty("tipo")
    private final GuiaTypes tipo;

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

    public GuiaTypes getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Guia guia)) return false;
        return Id == guia.Id && Double.compare(valorTotal, guia.valorTotal) == 0 && Objects.equals(CNPJ_Empresa, guia.CNPJ_Empresa) && Objects.equals(identificador, guia.identificador) && Objects.equals(dataVencimento, guia.dataVencimento) && Objects.equals(competencia, guia.competencia) && link == guia.link && tipo == guia.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, tipo);
    }

    @Override
    public String toString() {
        return "Guia{" +
                "Id=" + Id +
                ", CNPJ_Empresa='" + CNPJ_Empresa + '\'' +
                ", identificador=" + identificador +
                ", dataVencimento=" + dataVencimento +
                ", valorTotal=" + valorTotal +
                ", competencia=" + competencia +
                ", link=" + link +
                ", tipo=" + tipo +
                '}';
    }
}
