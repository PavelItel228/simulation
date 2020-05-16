package dao;

import java.util.List;
import java.util.Optional;

// Интерфейс для взаимодейсвия с обектами
// Все остальныые интерфейсы в этой папке расширяют этот интерфейс
public interface GenericDAO<T> {
    void create (T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(Long id);
}
