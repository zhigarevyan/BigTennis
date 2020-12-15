package util;

import entity.dbEntity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class BazaBakaSessionFactory {
    private static SessionFactory tableTennisFactory;
    private static SessionFactory bigTennisFactory;

    private BazaBakaSessionFactory() {}

//    public static SessionFactory getTableTennisFactory() {
//        if (tableTennisFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//                configuration.configure("table_tennis.cfg.xml");
//                configuration.addAnnotatedClass(MatchesLEntity.class);
//                configuration.addAnnotatedClass(PlayersEntity.class);
//                configuration.addAnnotatedClass(LeaguesEntity.class);
//                configuration.addAnnotatedClass(ResultEntity.class);
//                configuration.addAnnotatedClass(WinLsEntity.class);
//                configuration.addAnnotatedClass(MatchesUpdatesEntity.class);
//                configuration.addAnnotatedClass(AppusersEntity.class);
//                configuration.addAnnotatedClass(UnregUsersEntity.class);
//                tableTennisFactory = configuration.buildSessionFactory();
//
//            } catch (Exception e) {
//                System.out.println("Исключение!" + e);
//            }
//        }
//        return tableTennisFactory;
//    }

    public static SessionFactory getBigTennisFactory() {
        if (bigTennisFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("/big_tennis.cfg.xml");
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
