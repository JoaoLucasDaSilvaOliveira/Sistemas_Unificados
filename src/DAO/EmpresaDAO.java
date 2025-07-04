package DAO;

import Model.Empresa;
import Utils.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementação de {@link OperacoesDAO} para manipulação de dados da entidade {@link Empresa}.
 * Os dados são persistidos em um arquivo JSON local, localizado no diretório {@code data/empresas.json}.
 */
public class EmpresaDAO implements OperacoesDAO<Empresa, String, Object> {

    /** Caminho do diretório onde o arquivo será salvo. */
    private static final String DIRECTORY_PATH = "data";

    /** Nome do arquivo JSON. */
    private static final String FILE_NAME = "empresas.json";

    /** Caminho completo para o arquivo JSON. */
    private static final Path PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    /**
     * Construtor que garante a existência do diretório e do arquivo onde os dados das empresas serão salvos.
     */
    public EmpresaDAO() {
        try {
            // Cria o diretório se não existir
            if (!Files.exists(PATH.getParent())) {
                Files.createDirectory(PATH.getParent());
                System.out.println("Diretório criado em: " + PATH.getParent());
            }

            // Cria o arquivo se não existir
            if (!Files.exists(PATH)) {
                Files.createFile(PATH);
                System.out.println("Arquivo criado em: " + PATH);
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar ou criar o arquivo/diretório: " + e.getMessage());
        }
    }

    /**
     * Salva uma nova empresa no arquivo.
     *
     * @param empresa a empresa a ser salva.
     * @return {@code true} se a operação foi bem-sucedida.
     */
    @Override
    public boolean create(Empresa empresa) {
        List<Empresa> empresas = searchAll();
        empresas.add(empresa);
        return saveList(empresas);
    }

    /**
     * Retorna todas as empresas cadastradas no arquivo.
     *
     * @return lista de empresas.
     */
    @Override
    public List<Empresa> searchAll() {
        if (FileUtils.fileNotFound(PATH)) {
            return new ArrayList<>();
        }

        try {
            if (FileUtils.fileIsEmpty(PATH)) {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar tamanho do arquivo: " + e.getMessage());
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            return mapper.readValue(reader, new TypeReference<List<Empresa>>() {});
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Busca uma empresa pelo seu CNPJ.
     *
     * @param searching CNPJ da empresa.
     * @return a empresa correspondente, ou {@code null} se não encontrada.
     */
    @Override
    public Empresa searchByKey(String searching) {
        HashMap<String, Empresa> empresas = (HashMap<String, Empresa>) searchAll().stream()
                .collect(Collectors.toMap(
                        Empresa::getCNPJ,
                        e -> e,
                        (e1, e2) -> e1,
                        HashMap::new
                ));

        return empresas.get(searching);
    }

    /**
     * Busca uma empresa por diferentes tipos de valores: nome, CNPJ ou código do dono.
     *
     * @param searching valor a ser buscado.
     * @return empresa encontrada ou {@code null} se não encontrada.
     */
    @Override
    public Empresa searchByValue(Object searching) {
        List<Empresa> empresas = searchAll();
        if (!empresas.isEmpty()) {
            for (Empresa empresa : empresas) {
                if (empresa.getName().equals(searching)
                        || empresa.getCNPJ().equals(searching)
                        || empresa.getCodDono() == (Integer) searching) {
                    return empresa;
                }
            }
        }
        return null;
    }

    /**
     * Remove uma empresa da lista e salva o novo estado no arquivo.
     *
     * @param deleting empresa a ser removida.
     * @return {@code true} se a operação foi bem-sucedida.
     */
    @Override
    public boolean delete(Empresa deleting) {
        List<Empresa> empresas = searchAll();
        empresas.remove(deleting);
        return saveList(empresas);
    }

    /**
     * Atualiza os dados de uma empresa existente com base no CNPJ.
     *
     * @param updating empresa com dados atualizados.
     * @return {@code true} se a operação foi bem-sucedida.
     */
    @Override
    public boolean update(Empresa updating) {
        List<Empresa> empresas = searchAll();

        for (Empresa e : empresas) {
            if (e.getCNPJ().equals(updating.getCNPJ())) {
                e.setName(updating.getName());
                e.setCodDono(updating.getCodDono());
                return saveList(empresas);
            }
        }
        return false;
    }

    /**
     * Salva a lista completa de empresas no arquivo.
     *
     * @param savingList lista a ser persistida.
     * @return {@code true} se a operação foi bem-sucedida.
     */
    @Override
    public boolean saveList(List<Empresa> savingList) {
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedWriter writer = Files.newBufferedWriter(PATH)) {
            mapper.writeValue(writer, savingList);
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista: " + e.getMessage());
            return false;
        }
    }
}
