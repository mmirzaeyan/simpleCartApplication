package m.mirzaeyan.cart.service.impl;

import m.mirzaeyan.cart.domain.User;
import m.mirzaeyan.cart.repository.GenericRepository;
import m.mirzaeyan.cart.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends GenericServiceImpl<User, String> implements UserService {


    public UserServiceImpl(GenericRepository<User, String> repository) {
        super(repository);
    }
}
