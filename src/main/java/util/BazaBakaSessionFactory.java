package util;

import bigtennis.entity.dbEntity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class BazaBakaSessionFactory {
    private static SessionFactory tableTennisFactory;
    private static SessionFactory bigTennisFactory;

    private BazaBakaSessionFactory() {}

    public static SessionFactory getTableTennisFactory() {
        if (tableTennisFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("tabletennis/tabletennis.cfg.xml");
                configuration.addAnnotatedClass(tabletennis.entity.dbEntity.MatchesLEntity.class);
                configuration.addAnnotatedClass(tabletennis.entity.dbEntity.PlayersEntity.class);
                configuration.addAnnotatedClass(tabletennis.entity.dbEntity.LeaguesEntity.class);
                configuration.addAnnotatedClass(tabletennis.entity.dbEntity.ResultEntity.class);
                configuration.addAnnotatedClass(tabletennis.entity.dbEntity.WinLsEntity.class);
                configuration.addAnnotatedClass(tabletennis.entity.dbEntity.MatchesUpdatesEntity.class);
                configuration.addAnnotatedClass(tabletennis.entity.dbEntity.AppusersEntity.class);
                configuration.addAnnotatedClass(tabletennis.entity.dbEntity.UnregUsersEntity.class);
                tableTennisFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return tableTennisFactory;
    }

    public static SessionFactory getBigTennisFactory() {
        if (bigTennisFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("bigtennis/bigtennis.cfg.xml");
                configuration.addAnnotatedClass(MatchEntity.class);
                configuration.addAnnotatedClass(PlayerEntity.class);
                configuration.addAnnotatedClass(LeagueEntity.class);
                configuration.addAnnotatedClass(ResultEntity.class);
                configuration.addAnnotatedClass(UserEntity.class);
                configuration.addAnnotatedClass(UserRoleEntity.class);
                configuration.addAnnotatedClass(CourtTypeEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                bigTennisFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return bigTennisFactory;
    }

}
