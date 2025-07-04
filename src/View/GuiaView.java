package View;

import DAO.GuiaDAO;
import Model.*;
import Model.EXEPTIONS.NoSuchArgument;
import Model.EXEPTIONS.WrongPass;

import java.io.IOException;
import java.util.List;

public class GuiaView{
    public static void consultaDeGuias (String sectionBefore, Usuario usuario, Empresa empresa) {
        Login.showLoginInfos(usuario.getName(), usuario.getEmail(), empresa.getName(), empresa.getCNPJ());
        System.out.printf("%s -> CONSULTA DE GUIAS\n\n", sectionBefore);
        try {
            List<Guia> guias = Guia.listarGuias(empresa.getCNPJ());
            if (guias.isEmpty()){
                System.out.println("Não há guias cadastradas para essa empresa!");
            } else {
                for (Guia g : guias){
                    if (g instanceof FGTS){
                        FGTS guiaFGTS = (FGTS) g;
                        System.out.println(g);
                    } else if (g instanceof DAE){
                        DAE guiaDAE = (DAE) g;
                        System.out.println(g);
                    } else if (g instanceof DARF){
                        DARF guiaDAE = (DARF) g;
                        System.out.println(g);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
