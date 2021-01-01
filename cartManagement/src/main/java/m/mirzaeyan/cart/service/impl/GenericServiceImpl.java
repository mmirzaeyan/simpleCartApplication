package m.mirzaeyan.cart.service.impl;

import m.mirzaeyan.cart.domain.BaseEntity;
import m.mirzaeyan.cart.domain.User;
import m.mirzaeyan.cart.repository.GenericRepository;
import m.mirzaeyan.cart.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceImpl<T extends BaseEntity, ID extends Serializable> implements GenericService<T, ID> {

    protected final GenericRepository<T, ID> repository;

    public GenericServiceImpl(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }


    @Transactional
    @Override
    public T save(T entity) {
        entity.setCreatedDate(new Date());
        entity.setUpdatedDate(new Date());
        return Optional.of(entity)
                .map(this::validate)
                .map(repository::save)
                .orElseThrow(RuntimeException::new);
    }

    protected T validate(T t) {
        return t;
    }



    @Override
    public T findById(ID id) {
        return repository.getOne(id);
    }

    @Override
    public void delete(ID id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> find() {
        return repository.findAll(PageRequest.of(0, 50));
    }
}
