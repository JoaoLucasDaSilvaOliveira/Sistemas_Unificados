package DAO;

import Model.ENUMS.GuiaTypes;
import Model.Guia;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GuiaDAO {

    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_NAME = "guias.json";
    private static final Path PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    public GuiaDAO() throws IOException {
        if (!Files.exists(PATH)) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }
    }

    public List<Guia> searchAll() {
        if (Files.notExists(PATH)) {
            System.out.println("Arquivo não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        }

        try {
            if (Files.size(PATH) == 0) {
                return new ArrayList<>();
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            List<Guia> guias = mapper.readValue(
                    PATH.toFile(),
                    new TypeReference<List<Guia>>() {}
            );

            return guias;

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Guia> searchByValue(YearMonth searching) {
        List<Guia> guias = searchAll();
        List<Guia> guiasDaCompetencia = new ArrayList<>();
        for (Guia guia : guias) {
            if (guia.getCompetencia().equals(searching)) {
                guiasDaCompetencia.add(guia);
            }
        }
        return guiasDaCompetencia;
    }

    public List<Guia> searchByValue(String searching) {
        List<Guia> guias = searchAll();
        List<Guia> guiasDaCompetencia = new ArrayList<>();
        for (Guia guia : guias) {
            if (guia.getCNPJ_Empresa().equals(searching)) {
                guiasDaCompetencia.add(guia);
            }
        }
        //retorna uma lista vazia caso n ache nada
        return guiasDaCompetencia;
    }

    public boolean update(Object updating) {
        return false;
    }

    /*
        quando for implementar algo que receba as informações do json de guias e for retornar o obj guia
        fazer assim para data de vencimento:
        LocalDate dataFodase = LocalDate.parse(dataQveioDoJson, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        e assim para data de competencia:
        YearMonth competenciaFodase = LocalDate.parse(OutraDataQveioDoJson, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    */

}
