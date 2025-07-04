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

/**
 * DAO responsável pela persistência dos objetos {@link Usuario}.
 * Os dados são armazenados em arquivo JSON no caminho "data/usuarios.json".
 * Implementa as operações CRUD da interface {@link OperacoesDAO}.
 */
public class UsuarioDAO implements OperacoesDAO<Usuario, Integer, String> {

    /** Diretório onde o arquivo JSON está armazenado. */
    private static final String DIRECTORY_PATH = "data";

    /** Nome do arquivo JSON que contém os usuários. */
    private static final String FILE_NAME = "usuarios.json";

    /** Caminho completo do arquivo JSON. */
    private static final Path PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    /**
     * Construtor que cria o diretório e o arquivo JSON caso não existam.
     */
    public UsuarioDAO() {
        try {
            if (!Files.exists(PATH.getParent())) {
                Files.createDirectory(PATH.getParent());
                System.out.println("Diretório criado em: " + PATH.getParent());
            }
            if (!Files.exists(PATH)) {
                Files.createFile(PATH);
                System.out.println("Arquivo criado em: " + PATH);
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar ou criar o arquivo/diretório: " + e.getMessage());
        }
    }

    /**
     * Adiciona um novo {@link Usuario} ao arquivo.
     *
     * @param usuario usuário a ser criado.
     * @return {@code true} se a criação foi bem-sucedida, {@code false} caso contrário.
     */
    @Override
    public boolean create(Usuario usuario) {
        List<Usuario> usuarios = searchAll();
        usuarios.add(usuario);
        return saveList(usuarios);
    }

    /**
     * Retorna a lista de todos os usuários armazenados.
     *
     * @return lista de usuários, ou lista vazia se arquivo não existir ou estiver vazio.
     */
    @Override
    public List<Usuario> searchAll() {
        if (FileUtils.fileNotFound(PATH)) {
            System.out.println("Arquivo não encontrado. Retornando lista vazia.");
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
            return mapper.readValue(reader, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Busca um usuário pelo seu ID.
     *
     * @param searching ID do usuário.
     * @return usuário encontrado, ou {@code null} se não existir.
     */
    @Override
    public Usuario searchByKey(Integer searching) {
        List<Usuario> usuarios = searchAll();
        return usuarios.stream()
                .filter(usuario -> Objects.equals(usuario.getID(), searching))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca um usuário pelo valor de senha, email ou nome.
     *
     * @param searching valor a ser buscado.
     * @return usuário encontrado, ou {@code null} se não existir.
     */
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

    /**
     * Remove um usuário do arquivo.
     *
     * @param deleting usuário a ser removido.
     * @return {@code true} se a remoção foi bem-sucedida, {@code false} caso contrário.
     */
    @Override
    public boolean delete(Usuario deleting) {
        List<Usuario> usuarios = searchAll();
        usuarios.remove(deleting);
        return saveList(usuarios);
    }

    /**
     * Atualiza um usuário existente com novos dados.
     *
     * @param updating usuário com dados atualizados.
     * @return {@code true} se a atualização foi bem-sucedida, {@code false} caso contrário.
     */
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

    /**
     * Salva a lista completa de usuários no arquivo JSON.
     *
     * @param savingList lista de usuários a ser salva.
     * @return {@code true} se o salvamento foi bem-sucedido, {@code false} caso contrário.
     */
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
