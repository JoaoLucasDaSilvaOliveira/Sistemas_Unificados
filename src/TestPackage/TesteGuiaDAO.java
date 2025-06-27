package TestPackage;

import DAO.GuiaDAO;
import Model.Guia;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TesteGuiaDAO {
    public static void main(String[] args) throws IOException {

        GuiaDAO dao = new GuiaDAO();

        ArrayList<Guia> guias = (ArrayList<Guia>) dao.searchByValue(YearMonth.parse("07/25", DateTimeFormatter.ofPattern("MM/yy")));
        for (Guia guia : guias) {
            System.out.println(guia);
        }
    }
}
