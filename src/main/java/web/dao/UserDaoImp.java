package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void modify(User user) {
        entityManager.merge(user);
    }

    @Override
    public void remove(User user) {
        User userForRemove = findUserById(user.getId());
        entityManager.remove(userForRemove);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User u").getResultList();
    }

    @Override
    public User findUserById(Long id) {

        return entityManager.find(User.class, id);
    }
}
