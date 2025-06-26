package Model;

import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;
import Model.ENUMS.StatusPagamento;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public class DetalhamentoGuia extends Guia{

    public DetalhamentoGuia(int id, String CNPJ_Empresa, UUID identificador, LocalDate dataVencimento, double valorTotal, YearMonth competencia, LinkPagamento link, GuiaTypes tipo, StatusPagamento stPg, String observacoes, int codComposicao, String denominacaoDaComposicao, double valorPrincipal, double multa, double juros) {
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, tipo, stPg);
        this.observacoes = observacoes;
        this.codComposicao = codComposicao;
        this.denominacaoDaComposicao = denominacaoDaComposicao;
        this.valorPrincipal = valorPrincipal;
        this.multa = multa;
        this.juros = juros;
    }

    private final String observacoes;
    private final int codComposicao;
    private final String denominacaoDaComposicao;
    private final double valorPrincipal;
    private final double multa;
    private final double juros;
}
