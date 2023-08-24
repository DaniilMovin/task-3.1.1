package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;
@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private  EntityManager entityManager;
    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void removeUser(Long id) {
        User user = entityManager.find(User.class , id);
        entityManager.remove(user);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User " ,User.class ).getResultList();

    }
    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

}
