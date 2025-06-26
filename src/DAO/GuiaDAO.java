package DAO;

import java.util.List;

public class GuiaDAO implements OperacoesDAO{
    @Override
    public boolean create(Object creating) {
        return false;
    }

    @Override
    public List searchAll() {
        return List.of();
    }

    @Override
    public Object searchByKey(Object searching) {
        return null;
    }

    @Override
    public Object searchByValue(Object searching) {
        return null;
    }

    @Override
    public boolean delete(Object deleting) {
        return false;
    }

    @Override
    public boolean update(Object updating) {
        return false;
    }

    @Override
    public boolean saveList(List savingList) {
        return false;
    }
    /*
        quando for implementar algo que receba as informações do json de guias e for retornar o obj guia
        fazer assim para data de vencimento:
        LocalDate dataFodase = LocalDate.parse(dataQveioDoJson, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        e assim para data de competencia:
        YearMonth competenciaFodase = LocalDate.parse(OutraDataQveioDoJson, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    */
    //a criação de objeto vai ter q ser aqui, chama o new Guia() aqui;
    //na hora de fazer a criação vai ter que verificar qual o tipo da guia, infelizmente!
    //IDEIA: switch (tipo) {
    //            case "FGTS":
    //                guia = new ...();
    //                break;
    //            case "INSS":
    //                guia = new ...();
    //                break;
    //            ...
    //        }
}
