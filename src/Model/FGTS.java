package Model;

import Control.GuiaController;
import DAO.GuiaDAO;
import Model.ENUMS.GuiaTypes;
import Model.ENUMS.LinkPagamento;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class FGTS extends Guia{

    //não usar normalmente!!! uso apenas da classe DAO
    public FGTS(int id, String CNPJ_Empresa, UUID identificador, LocalDate dataVencimento, double valorTotal, YearMonth competencia, LinkPagamento link, GuiaTypes type, Map<String, Double> eConsignado, Map<String, String> funcionariosDaGuia, Map<String, Double> valoresPorFuncionario) {
        super(id, CNPJ_Empresa, identificador, dataVencimento, valorTotal, competencia, link, type);
        this.eConsignado = eConsignado;
        this.funcionariosDaGuia = funcionariosDaGuia;
        this.valoresPorFuncionario = valoresPorFuncionario;
    }
    public static FGTS generate (YearMonth comp, GuiaTypes type) throws Exception{
        GuiaController g = new GuiaController();
        return (FGTS) g.create(comp, type);
    }

    //TODO:
    /*createGuide("04/25"); -> DAO retorne a guia que tenha o campo competencia igual a 04/25
     throw Exception para tratar depois
    */
    //Nome, Valores
    private final Map<String, Double> valoresPorFuncionario;

    //CPF, Nome
    private final Map<String, String> funcionariosDaGuia;

    //CPF, Valor da guia
    private final Map<String, Double> eConsignado;
}
