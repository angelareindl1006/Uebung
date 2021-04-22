package _20210325_Convert_To_DAO2.persistence;

import java.util.List;
import java.util.Optional;

public interface Dao <T>{

    Optional<T> get(int id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(int id);
}