package util;

import bigtennis.entity.dbEntity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(MatchEntity.class);
                configuration.addAnnotatedClass(PlayerEntity.class);
                configuration.addAnnotatedClass(LeagueEntity.class);
                configuration.addAnnotatedClass(ResultEntity.class);
                configuration.addAnnotatedClass(UserEntity.class);
                configuration.addAnnotatedClass(UserRoleEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;

    }
}
