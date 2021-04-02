package action;

import service.ServiceFactory;

public class ActionManagerFactoryImpl implements ActionManagerFactory {
    private ServiceFactory factory;

    public ActionManagerFactoryImpl(ServiceFactory factory) {
        this.factory = factory;
    }

    public ActionManager getManager() {
        return new ActionManagerImpl(factory);
    }
}
