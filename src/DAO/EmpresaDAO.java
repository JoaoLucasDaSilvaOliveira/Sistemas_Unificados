package DAO;

import Model.Empresa;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class EmpresaDAO implements OperacoesDAO<Empresa, String, String> {

    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_NAME = "empresas.json";
    private static final Path PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    public EmpresaDAO() {

        try {
            // Cria o diretório caso não exista
            if (!Files.exists(PATH.getParent())) {
                Files.createDirectory(PATH.getParent());
                System.out.println("Diretório criado em: " + PATH.getParent());
            }

            // Cria o arquivo JSON caso não exista
            if (!Files.exists(PATH)) {
                Files.createFile(PATH);
                System.out.println("Arquivo criado em: " + PATH);
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar ou criar o arquivo/diretório: " + e.getMessage());
        }
    }

    @Override
    public boolean create(Empresa empresa) {
        List<Empresa> empresas = searchAll(); // Lista atual de empresa
        empresas.add(empresa); // Adiciona a nova empresa;

        return saveList(empresas); // Salva a lista atualizada no arquivo
    }

    @Override
    public List<Empresa> searchAll() {
        if (!Files.exists(PATH)) {
            System.out.println("Arquivo não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        }

        try {
            if (Files.size(PATH) == 0) { // <-- Verifica se o arquivo está vazio
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

    @Override
    public Empresa searchByKey(String searching) {
        HashMap<String, Empresa> empresas = (HashMap<String, Empresa>) searchAll().stream().collect(Collectors.toMap(Empresa::getCNPJ, e -> e, (e1, e2) -> e1, HashMap::new));

        Empresa found = empresas.get(searching);
        return found;  // Retorna empresa ou null se não achar
    }


    @Override
    public Empresa searchByValue(String searching) {
        return searchAll().stream().filter(e -> e.getName().equalsIgnoreCase(searching)).findFirst().orElse(null);
    }

    @Override
    public boolean delete(Empresa deleting) {
        List<Empresa> empresas = searchAll(); // Lista atual de empresa
        empresas.remove(deleting); // Remove a empresa

        return saveList(empresas); // Salva a lista atualizada no arquivo
    }

    @Override
    public boolean update(Empresa updating) {
        List<Empresa> empresas = searchAll();

        for (Empresa e : empresas) {
            if (e.getCNPJ().equals(updating.getCNPJ())) {
                e.setName(updating.getName());
                return saveList(empresas);
            }
        }
        return false;
    }

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
