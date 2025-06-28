package DAO;

import Model.Usuario;
import Utils.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;

public class UsuarioDAO implements OperacoesDAO<Usuario, Integer, String> {

    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_NAME = "usuarios.json";
    private static final Path PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    public UsuarioDAO() {

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
    public boolean create(Usuario usuario) {
        List<Usuario> usuarios = searchAll(); // Lista atual de empresa
        usuarios.add(usuario); // Adiciona a nova empresa;

        return saveList(usuarios); // Salva a lista atualizada no arquivo
    }

    @Override
    public List<Usuario> searchAll() {
        if (FileUtils.fileNotFound(PATH)) {
            System.out.println("Arquivo não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        }

        try {
            if (FileUtils.fileIsEmpty(PATH)) { // <-- Verifica se o arquivo está vazio
                return new ArrayList<>();
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar tamanho do arquivo: " + e.getMessage());
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            return mapper.readValue(reader, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Usuario searchByKey(Integer searching) {
        // Recupera a lista de todos os usuários
        List<Usuario> usuarios = searchAll();

        // Procura o usuário cujo ID corresponda à chave fornecida
        return usuarios.stream()
                .filter(usuario -> Objects.equals(usuario.getID(), searching))
                .findFirst()
                .orElse(null); // Retorna null caso nenhum usuário seja encontrado
    }


    @Override
    public Usuario searchByValue(String searching) {
        List<Usuario> users = searchAll();
        if (!users.isEmpty()) {
            for (Usuario usuario : users) {
                if (usuario.getSenha().equals(searching) || usuario.getEmail().equals(searching) || usuario.getName().equals(searching)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(Usuario deleting) {
        List<Usuario> usuarios = searchAll();
        usuarios.remove(deleting);

        return saveList(usuarios);
    }

    @Override
    public boolean update(Usuario updating) {
        List<Usuario> usuarios = searchAll();

        for (Usuario u : usuarios) {
            if (u.getID() == updating.getID()) {
                u.setName(updating.getName());
                u.setSenha(updating.getSenha());
                u.setEmail(updating.getEmail());
                u.setEmpresas(updating.getEmpresas());
                return saveList(usuarios);
            }
        }
        return false;
    }

    @Override
    public boolean saveList(List<Usuario> savingList) {
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
