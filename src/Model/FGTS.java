package Model;

import java.time.YearMonth;
import java.util.*;

public class FGTS extends Guia{

    static {
        //a ideia é pegar TODOS os tipos de guias e avaliar o identificador, não pode ter o mesmo identificador
        //este bloco gera e valida o identificador
        GuiaDAO g = new GuiaDAO();
        List<Guia> guias = g.searchAll();
        boolean validate = true;
        while (validate){
            UUID temp = UUID.randomUUID();
            if (guias.isEmpty()){
                FGTS.UUIDTemp = temp;
            } else{
                for (Guia guia : guias){
                    if (guia.getIdentificador().equals(temp)){
                        break;
                    }
                }
                validate = false;
            }
        }
        //este bloco gera e valida o id
        GuiaFGTSDAO fgts = new GuiaFGTSDAO();
        List<FGTS> guiasFGTS = new fgts.searchAll();
        if (guiasFGTS.isEmpty()) {
            FGTS.currentID= 0;
        } else {
            for (FGTS temp : guiasFGTS) {
                FGTS.currentID = temp.getId();
            }
        }
    }

    public static FGTS createGuide (YearMonth competencia){

    }
    //TODO:
    /*createGuide("04/25"); -> DAO retorne a guia que tenha o campo competencia igual a 04/25
     throw Exception para tratar depois
    */
    //Nome, Valores
    private final Map<String, Double> valoresPorFuncionario;

    //Nome, CPF
    private final Map<String, String> funcionariosDaGuia;

    //CPF, Valor da guia
    private final Map<String, Double> eConsignado;

    private static UUID UUIDTemp;

    private static int currentID;
}
