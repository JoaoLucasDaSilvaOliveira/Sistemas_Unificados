package Model;

import DAO.EmpresaDAO;
import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import Model.ENUMS.StatusPagamento;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Guia {

    //o id vai ter que vir pelas classes filhas, por isso a atribuição direta sem validação com o bloco static
    //como essa classe é apenas para atribuição, não haverá a necessidade de uma GuiaController!


    public Guia(int id, String CNPJ_Empresa, UUID identificador, LocalDate dataVencimento, double valorTotal, YearMonth competencia, LinkPagamento link, GuiaTypes tipo, StatusPagamento stPg) {
        Id = id;
        this.CNPJ_Empresa = CNPJ_Empresa;
        this.identificador = identificador;
        this.dataVencimento = dataVencimento;
        this.valorTotal = valorTotal;
        this.competencia = competencia;
        this.link = link;
        this.tipo = tipo;
        StPg = stPg;
    }

    private final int Id;
    private final String CNPJ_Empresa;
    private final UUID identificador;
    private final LocalDate dataVencimento;
    private final double valorTotal;
    private final YearMonth competencia;
    private final LinkPagamento link;
    private final GuiaTypes tipo;
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
