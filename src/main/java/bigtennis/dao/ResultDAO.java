package bigtennis.dao;

import bigtennis.entity.selenium.StringResult;
import bigtennis.entity.dbEntity.ResultEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.BazaBakaSessionFactory;

import java.util.List;

public class ResultDAO {

    public ResultEntity findById(int id) {
        return BazaBakaSessionFactory.getBigTennisFactory().openSession().get(ResultEntity.class, id);
    }

    public static void main(String[] args) {
        ResultDAO r = new ResultDAO();
        System.out.println(r.findAll());

    }

    public void save(ResultEntity result) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(result);
        tx1.commit();
        session.close();
    }

    public void update(ResultEntity result) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(result);
        tx1.commit();
        session.close();
    }

    public void delete(ResultEntity result) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(result);
        tx1.commit();
        session.close();
    }

    public List<ResultEntity> findAll() {
        List<ResultEntity> results = (List<ResultEntity>) BazaBakaSessionFactory.getBigTennisFactory().openSession().createQuery("From ResultEntity ").list();
        return results;
    }

    public List<ResultEntity> byParams(StringResult params) {
        Session session = BazaBakaSessionFactory.getBigTennisFactory().openSession();

        String score = params.getScore();

        String set1 = params.getSet1();
        String set2 = params.getSet2();
        String set3 = params.getSet3();
        String set4 = params.getSet4();
        String set5 = params.getSet5();


        return session.createNamedQuery("Result.byParams")
                .setParameter("score", score)
                .setParameter("set1", set1)
                .setParameter("set2", set2)
                .setParameter("set3", set3)
                .setParameter("set4", set4)
                .setParameter("set5", set5)
                .getResultList();
    }



}
