package service.impl;

import dao.DaoFactory;
import exception.PersistentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactoryImpl implements ServiceFactory {
    private static Logger logger = LoggerFactory.getLogger(ServiceFactoryImpl.class);

    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(UserService.class, UserServiceImpl.class);
        SERVICES.put(SensorService.class, SensorServiceImpl.class);
        SERVICES.put(ScheduleService.class, ScheduleServiceImpl.class);
        SERVICES.put(ScoreboardService.class, ScoreboardServiceImpl.class);
        SERVICES.put(MqttService.class, MqttServiceImpl.class);
        SERVICES.put(SensorTypeService.class, SensorTypeServiceImpl.class);
    }

    private DaoFactory factory;

    public ServiceFactoryImpl(DaoFactory factory) throws PersistentException {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Type extends Service> Type getService(Class<Type> key) throws PersistentException {
        Class<? extends ServiceImpl> value = SERVICES.get(key);
        if (value != null) {
            try {
                //создать экземпляр класса
                ServiceImpl service = value.newInstance();
                service.setDaoFactory(factory);
                return (Type) service;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to instance service class", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        factory.close();
    }
}
