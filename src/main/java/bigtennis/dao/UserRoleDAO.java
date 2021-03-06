package bigtennis.dao;

import bigtennis.entity.dbEntity.UserRoleEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class UserRoleDAO {

    public UserRoleEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UserRoleEntity.class, id);
    }

    public void save(UserRoleEntity role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(role);
        tx1.commit();
        session.close();
    }

    public void update(UserRoleEntity role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(role);
        tx1.commit();
        session.close();
    }

    public void delete(UserRoleEntity role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(role);
        tx1.commit();
        session.close();
    }

    public List<UserRoleEntity> findAll() {
        List<UserRoleEntity> roles = (List<UserRoleEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From UserRoleEntity ").list();
        return roles;
    }

}
