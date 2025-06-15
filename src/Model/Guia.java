package Model;

import DAO.EmpresaDAO;
import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class Guia {

    //o id vai ter que vir pelas classes filhas, por isso a atribuição direta sem validação com o bloco static
    //como essa classe é apenas para atribuição, não haverá a necessidade de uma GuiaController!
    public Guia(int id, String CNPJ_Empresa, UUID identificador, LocalDate dataVencimento, double valorTotal, YearMonth competencia, LinkPagamento link, GuiaTypes type) {
        Id = ++id;
        this.CNPJ_Empresa = CNPJ_Empresa;
        this.identificador = identificador;
        this.dataVencimento = dataVencimento;
        this.valorTotal = valorTotal;
        this.competencia = competencia;
        this.link = link;
        this.tipo = type;
    }

    private final int Id;
    private final String CNPJ_Empresa;
    private final UUID identificador;
    private final LocalDate dataVencimento;
    private final double valorTotal;
    private final YearMonth competencia;
    private final LinkPagamento link;
    private final GuiaTypes tipo;

    public int getId() {
        return Id;
    }

    public UUID getIdentificador() {
        return identificador;
    }

}
