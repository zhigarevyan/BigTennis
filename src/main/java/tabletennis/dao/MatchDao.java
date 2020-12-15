package dao;

import entity.dbEntity.LeaguesEntity;
import entity.dbEntity.MatchesLEntity;
import entity.dbEntity.PlayersEntity;
import entity.dbEntity.ResultEntity;
import util.HibernateSessionFactoryUtil;
//import jdk.jfr.Name;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao {

    private String GET_LAST_MATCHES_BY_QUANTITY = "From MatchesLEntity Order by date desc";
    private String GET_2PL_MATCHES = "From MatchesLEntity Where player1.name = :p1name and player2.name = :p2name Order by date desc";

    public MatchesLEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(MatchesLEntity.class, id);
    }

    public void save(MatchesLEntity match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(match);
        tx1.commit();
        session.close();
    }

    public void update(MatchesLEntity match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(match);
        tx1.commit();
        session.close();
    }

    public void delete(MatchesLEntity match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(match);
        tx1.commit();
        session.close();
    }

    public List<MatchesLEntity> findAll() {
        List<MatchesLEntity> matches = (List<MatchesLEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From MatchesLEntity ").list();
        return matches;
    }

    public List<MatchesLEntity> getLastMatches(int quantity, String league) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //return session.createQuery(GET_LAST_MATCHES_BY_QUANTITY).setMaxResults(quantity).list();
        return session.createNamedQuery("Match.getMatches")
                .setMaxResults(quantity)
                .setParameter("league",league+"%")
                .getResultList();
    }

    public List<MatchesLEntity> get2PlMatches(int quantity, String p1name, String p2name, String league) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createNamedQuery("Match.get2PlMatches")
                .setParameter("p1name",p1name)
                .setParameter("p2name",p2name)
                .setParameter("league",league+"%")
                .setMaxResults(quantity)
                .getResultList();
    }

    public List<MatchesLEntity> getPlMatches(int quantity, String p1name, String league) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createNamedQuery("Match.getPlMatches")
                .setParameter("p1name",p1name)
                .setParameter("league",league+"%")
                .setMaxResults(quantity)
                .getResultList();
    }

    public List<MatchesLEntity> getPlMatchesByDate(String p1name, String date, String league) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createNamedQuery("Match.getPlMatchesByDate")
                .setParameter("p1name",p1name)
                .setParameter("league",league+"%")
                .setParameter("date",date+"%")
                .getResultList();
    }

    public List<MatchesLEntity> byParams(String p1name, String p2name, String score, String set1, String set2, String set3, String set4, String set5, String set6, String set7, String date, String league) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createNamedQuery("Match.byParams")
                .setParameter("p1name",p1name)
                .setParameter("p2name",p2name)
                .setParameter("score",score)
                .setParameter("set1",set1)
                .setParameter("set2",set2)
                .setParameter("set3",set3)
                .setParameter("set4",set4)
                .setParameter("set5",set5)
                .setParameter("set6",set6)
                .setParameter("set7",set7)
                .setParameter("date",date)
                .setParameter("league",league)
                .getResultList();
    }

    public List<MatchesLEntity> byId(PlayersEntity player1, PlayersEntity player2, ResultEntity result, String date, LeaguesEntity league) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        int a = 5;
        return session.createNamedQuery("Match.byId")
                .setParameter("p1id", player1)
                .setParameter("p2id", player2)
                .setParameter("resultid",result)
                .setParameter("date",date)
                .setParameter("leagueid",league)
                .getResultList();
    }

    //public List<MatchesLEntity>

}
