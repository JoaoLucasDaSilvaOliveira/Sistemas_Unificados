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
import java.util.List;

/**
 * Classe responsável pela leitura e consulta de objetos {@link Guia} em arquivo JSON.
 * O arquivo é armazenado em {@code data/guias.json}.
 *
 * Essa DAO não implementa uma interface genérica, pois possui buscas específicas por competência e CNPJ.
 */
public class GuiaDAO {

    /** Caminho do diretório onde o arquivo de guias está localizado. */
    private static final String DIRECTORY_PATH = "data";

    /** Nome do arquivo JSON. */
    private static final String FILE_NAME = "guias.json";

    /** Caminho completo do arquivo de guias. */
    private static final Path PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    /**
     * Construtor que garante a existência do arquivo {@code guias.json}.
     *
     * @throws IOException se o arquivo não for encontrado.
     */
    public GuiaDAO() throws IOException {
        if (!Files.exists(PATH)) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }
    }

    /**
     * Lê todos os objetos {@link Guia} armazenados no arquivo.
     *
     * @return lista de guias encontradas ou lista vazia caso ocorra erro ou arquivo esteja vazio/inexistente.
     */
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

            return mapper.readValue(
                    PATH.toFile(),
                    new TypeReference<List<Guia>>() {}
            );

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Busca todas as guias de uma determinada competência.
     *
     * @param searching competência no formato {@link YearMonth}.
     * @return lista de guias daquela competência ou lista vazia.
     */
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

    /**
     * Busca todas as guias associadas a um CNPJ específico.
     *
     * @param searching CNPJ da empresa.
     * @return lista de guias encontradas ou vazia.
     */
    public List<Guia> searchByValue(String searching) {
        List<Guia> guias = searchAll();
        List<Guia> guiasDaEmpresa = new ArrayList<>();
        for (Guia guia : guias) {
            if (guia.getCNPJ_Empresa().equals(searching)) {
                guiasDaEmpresa.add(guia);
            }
        }
        return guiasDaEmpresa;
    }

    /**
     * Método de atualização ainda não implementado.
     *
     * @param updating objeto a ser atualizado.
     * @return sempre retorna {@code false}.
     */
    public boolean update(Object updating) {
        return false;
    }

    /*
     * NOTA:
     * Para converter datas de String para objetos:
     *
     * LocalDate data = LocalDate.parse(dataDoJson, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
     * YearMonth competencia = YearMonth.parse(dataDoJson, DateTimeFormatter.ofPattern("MM/yyyy"));
     */
}
