package DAO;

import Model.Empresa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class EmpresaDAO implements OperacoesDAO<Empresa, String, String> {

    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_NAME = "empresas.json";
    private static final String FILE_PATH = DIRECTORY_PATH + File.separator + FILE_NAME;

    private final Gson gson = new Gson();

    public EmpresaDAO() {
        File directory = new File(DIRECTORY_PATH);
        File file = new File(FILE_PATH);

        try {
            // Cria o diretório caso não exista
            if (!directory.exists()) {
                boolean dirCreated = directory.mkdirs();
                if (dirCreated) {
                    System.out.println("Diretório criado em: " + directory.getAbsolutePath());
                } else {
                    System.err.println("Falha ao criar o diretório.");
                }
            }

            // Cria o arquivo JSON caso não exista
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("Arquivo criado em: " + file.getAbsolutePath());
                } else {
                    System.err.println("Falha ao criar o arquivo.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar ou criar o arquivo/diretório: " + e.getMessage());
        }
    }

    @Override
        //TODO: Implementar a lógica de login com JSON - Criar uma branch nova para implementação
    public boolean create(Empresa empresa) {
        List<Empresa> empresas = searchAll(); // Lista atual de empresa
        empresas.add(empresa); // Adiciona a nova empresa;

        return saveList(empresas); // Salva a lista atualizada no arquivo
    }

    @Override
    public List<Empresa> searchAll() {
        File file = new File(FILE_PATH);

        // Verifica se o arquivo existe antes de tentar ler
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Empresa>>() {}.getType();
            List<Empresa> empresas = gson.fromJson(reader, listType);

            return empresas != null ? empresas : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Empresa searchByKey(String searching) {
        //deve retornar um Obj Empresa procurado por sua key
        return null;
    }

    @Override
    public Empresa searchByValue(String searching) {
        //deve retornar um Obj Empresa procurado por seu value
        return null;
    }

    @Override
    public boolean delete(Empresa deleting) {
        //deve deletar uma empresa do arqv JSON
        return false;
    }

    @Override
    public boolean update(Empresa updating) {
        //deve poder mudar o nome da empresa no arqv JSON
        return false;
    }

    @Override
    public boolean saveList(List<Empresa> savingList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(savingList, writer); // Serializa a lista como JSON e salva no arquivo
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista: " + e.getMessage());
            return false;
        }
    }
}
