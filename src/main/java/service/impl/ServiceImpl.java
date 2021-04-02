package service.impl;

import dao.DaoFactory;
import service.Service;
import service.ServiceFactory;

abstract public class ServiceImpl implements Service {
    protected DaoFactory factory = null;

    protected ServiceFactory serviceFactory = null;

    public void setDaoFactory(DaoFactory factory) {
        this.factory = factory;
    }

    public void setServiceFactory(ServiceFactory serviceFactory) {

        this.serviceFactory = serviceFactory;
    }
}
