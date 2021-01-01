package m.mirzaeyan.cart.service;

import m.mirzaeyan.cart.domain.BaseEntity;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T extends BaseEntity, ID extends Serializable> {

    T save(T entity);


    T findById(ID id);

    void delete(ID id) throws Exception;

    List<T> getAll();

    Page<T> find();

}
