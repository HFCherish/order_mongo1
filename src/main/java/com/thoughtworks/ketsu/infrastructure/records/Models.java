package com.thoughtworks.ketsu.infrastructure.records;

import com.google.inject.AbstractModule;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.thoughtworks.ketsu.Dao.ProductDao;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.users.UserRepository;
import com.thoughtworks.ketsu.infrastructure.mongo.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepositoryImpl;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepositoryImpl;

import java.net.UnknownHostException;
import java.util.Properties;

public class Models extends AbstractModule {
    private static final String DEFAULT_CONFIG_RESOURCE = "db.properties";

    private final String classPathResource;

    private final String environmentId;

    private final Properties properties;

    private ClassLoader resourcesClassLoader = getDefaultClassLoader();

    private ClassLoader driverClassLoader = getDefaultClassLoader();

    public Models(String environment) {
        this(environment, DEFAULT_CONFIG_RESOURCE, new Properties());
    }

    public Models(String environment, Properties properties) {
        this(environment, DEFAULT_CONFIG_RESOURCE, properties);
    }

    public Models(String environment, String classPathResource, Properties properties) {
        this.environmentId = environment;
        this.classPathResource = classPathResource;
        this.properties = properties;
    }

    @Override
    protected void configure() {
//        bindPersistence();
        String dbname = System.getenv().getOrDefault("MONGODB_DATABASE", "mongodb_store");
        String host = System.getenv().getOrDefault("MONGODB_HOST", "localhost");
        String port = System.getenv().getOrDefault("MONGODB_PORT", "27017");
        String username = System.getenv().getOrDefault("MONGODB_USER", "admin");
        String password = System.getenv().getOrDefault("MONGODB_PASS", "mypass");
        String connectURL = String.format(
                "mongodb://%s:%s@%s/%s",
                username,
                password,
                host,
                dbname
        );
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(
                    new MongoClientURI(connectURL));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DB db = mongoClient.getDB("mongodb_store");
        bind(DB.class).toInstance(db);
        bind(ProductRepository.class).to(ProductRepositoryImpl.class);
        bind(ProductMapper.class).to(ProductDao.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);
    }
//
//    private void bindPersistence() {
//        try {
//            bindSqlManager();
//            bindTransactionalInterceptor();
//            bindSqlSessionFactory();
//            bind(ClassLoader.class)
//                    .annotatedWith(named("JDBC.driverClassLoader"))
//                    .toInstance(driverClassLoader);
//        } finally {
//            resourcesClassLoader = getDefaultClassLoader();
//            driverClassLoader = getDefaultClassLoader();
//        }
//    }

//    private void bindSqlSessionFactory() {
//        try (Reader reader = getResourceAsReader(getResourceClassLoader(), classPathResource)) {
//            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader,
//                    environmentId,
//                    properties);
//            bind(SqlSessionFactory.class).toInstance(sessionFactory);
//
//            Configuration configuration = sessionFactory.getConfiguration();
//
//            bindObjectFactory(configuration);
//            bindMappers(configuration);
//            bindTypeHandlers(configuration);
//            bindInterceptors(configuration);
//        } catch (Exception e) {
//            addError("Impossible to read classpath resource '%s', see nested exceptions: %s",
//                    classPathResource,
//                    e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private void bindObjectFactory(Configuration configuration) {
//        requestInjection(configuration.getObjectFactory());
//    }
//
//    private void bindInterceptors(Configuration configuration) {
//        Collection<Interceptor> interceptors = configuration.getInterceptors();
//        for (Interceptor interceptor : interceptors) {
//            requestInjection(interceptor);
//        }
//    }
//
//    private void bindTypeHandlers(Configuration configuration) {
//        Collection<TypeHandler<?>> allTypeHandlers = configuration.getTypeHandlerRegistry().getTypeHandlers();
//        for (TypeHandler<?> handler : allTypeHandlers) {
//            requestInjection(handler);
//        }
//    }
//
//    private void bindMappers(Configuration configuration) {
//        Collection<Class<?>> mapperClasses = configuration.getMapperRegistry().getMappers();
//        for (Class<?> mapperType : mapperClasses) {
//            bindMapper(mapperType);
//        }
//    }
//
//    private void bindTransactionalInterceptor() {
//        TransactionalMethodInterceptor interceptor = new TransactionalMethodInterceptor();
//        requestInjection(interceptor);
//        bindInterceptor(any(), annotatedWith(Transactional.class), interceptor);
//        bindInterceptor(annotatedWith(Transactional.class), not(annotatedWith(Transactional.class)), interceptor);
//    }
//
//    private void bindSqlManager() {
//        bind(SqlSessionManager.class).toProvider(SqlSessionManagerProvider.class).in(Scopes.SINGLETON);
//        bind(SqlSession.class).to(SqlSessionManager.class).in(Scopes.SINGLETON);
//    }
//
//    final <T> void bindMapper(Class<T> mapperType) {
//        bind(mapperType).toProvider(guicify(new MapperProvider<T>(mapperType))).in(Scopes.SINGLETON);
//    }

    protected final ClassLoader getResourceClassLoader() {
        return resourcesClassLoader;
    }

    private ClassLoader getDefaultClassLoader() {
        return getClass().getClassLoader();
    }
}

