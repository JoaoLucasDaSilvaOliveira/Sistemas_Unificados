package DAO;

import java.util.*;

public interface OperacoesDAO<T,K,V> {
    public boolean create (T creating);
    public List<T> searchAll ();
    public T searchByKey (K searching);
    public T searchByValue (V searching);
    public boolean delete (T deleting);
    public boolean update (T updating);
    public boolean saveList (List<T> savingList);
}
