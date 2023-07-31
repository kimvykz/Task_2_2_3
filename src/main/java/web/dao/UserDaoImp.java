package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        List<User> res = entityManager.createQuery("from User u").getResultList();
        return res;
    }

    @Override
    public User findUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }
}
