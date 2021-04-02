package hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
//1
//    private static SessionFactory sessionFactory = null;
//
//    static {
//        Configuration cfg = new Configuration().configure();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
//
//        sessionFactory = cfg.buildSessionFactory(builder.build());
//    }
    //2 example
//StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//        .configure( "org/hibernate/example/hibernate.cfg.xml" )
//        .build();
//
//    Metadata metadata = new MetadataSources( standardRegistry )
//            .addAnnotatedClass( MyEntity.class )
//            .addAnnotatedClassName( "org.hibernate.example.Customer" )
//            .addResource( "org/hibernate/example/Order.hbm.xml" )
//            .addResource( "org/hibernate/example/Product.orm.xml" )
//            .getMetadataBuilder()
//            .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
//            .build();
//
//    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
//            .applyBeanManager( getBeanManager() )
//            .build();
//3
//if (sessionFactory == null) {
//        try {
//            Configuration configuration = new Configuration().configure();
//            configuration.addAnnotatedClass(User.class);
//            configuration.addAnnotatedClass(Auto.class);
//            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//            sessionFactory = configuration.buildSessionFactory(builder.build());
//
//        } catch (Exception e) {
//            System.out.println("Исключение!" + e);
//        }
//    }
//        return sessionFactory;
//4
    private static SessionFactory sessionFactory = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
//        sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }


    public static SessionFactory getSessionfactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionfactory().close();
    }

}
