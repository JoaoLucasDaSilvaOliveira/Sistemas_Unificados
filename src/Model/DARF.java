package Model;

import Control.GuiaController;
import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public class DARF extends DetalhamentoGuia{

    public DARF(int id, String CNPJ_Empresa, UUID identificador, LocalDate dataVencimento, double valorTotal, YearMonth competencia, LinkPagamento link, GuiaTypes type, String observacoes, int codComposicao, String denominacaoDaComposicao, double valorPrincipal, double multa, double juros) {
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, type, observacoes, codComposicao, denominacaoDaComposicao, valorPrincipal, multa, juros);
    }
    public static DARF generate (YearMonth comp, GuiaTypes type) throws Exception{
        GuiaController g = new GuiaController();
        return (DARF) g.create(comp, type);
    }
}
