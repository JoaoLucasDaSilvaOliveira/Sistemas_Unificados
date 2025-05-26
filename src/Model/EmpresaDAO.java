package Model;

import java.util.*;

public class EmpresaDAO implements OperacoesDAO <Empresa, String, String>{
    @Override
        //TODO: Implementar a lógica de login com JSON - Criar uma branch nova para implementação
    public boolean create(Empresa creating) {
        //deve adicionar um Objeto do tipo pessoa no arquivo JSON utilizado
        return false;
    }

    @Override
    public List<Empresa> searchAll(Empresa searching) {
        //deve retornar uma lista com todas as empresas do arqv JSON
        return null;
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
}
