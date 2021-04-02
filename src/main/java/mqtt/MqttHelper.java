//package mqtt;
//
//import dao.sql.DaoFactoryImpl;
//import exception.PersistentException;
//import model.Sensor;
//import org.eclipse.paho.client.mqttv3.*;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.apache.commons.lang3.StringUtils;
//import service.SensorService;
//import service.impl.ServiceFactoryImpl;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import java.util.Properties;
//
//public class MqttHelper {
//    private static volatile  MqttHelper INSTANCE = null;
//    private MqttClient mqttClient;
//    final String LOG_TAG = "MqttHelper";
//    String serverUri;
//    String clientId;
//    String subscriptionTopic;
//    private String  publictionTopic;
//    final String username = "";
//    final String password = "";
//    MemoryPersistence persistence;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    private String message;
//
//    static {
//        try {
//            if (INSTANCE == null)
//                INSTANCE = new MqttHelper();
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static MqttHelper getInstance(){
//        MqttHelper result = INSTANCE;
//        if (result != null) {
//            return result;
//        }
//        synchronized(MqttHelper.class) {
//            if (INSTANCE == null) {
//                try {
//                    INSTANCE = new MqttHelper();
//                } catch (MqttException e) {
//                    e.printStackTrace();
//                }
//            }
//            return INSTANCE;
//        }
//    }
//
//    public MqttHelper() throws MqttException {
//        loadSettings();
//        connect();
//        subscribeToTopic();
//    }
//
//    private void loadSettings() {
//        Properties props = new Properties();
//        InputStream in = null;
//        try {
//            in = getClass().getResourceAsStream("/mqtt_config.properties");
//            props.load(in);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String ip = props.getProperty("ip_server", "137.135.83.217");
//        String port = props.getProperty("port_server", "1883");
//        publictionTopic = props.getProperty("topic_sub", "/54226180432125161/sub");
//        subscriptionTopic = props.getProperty("topic_pub", "/54226180432125161/pub");
//        serverUri = "tcp://".concat(ip).concat(":").concat(port);
//        persistence = new MemoryPersistence();
//        this.clientId = MqttClient.generateClientId();
//
////        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(cont);
////        SharedPreferences.Editor writeS= prefs.edit();
////        String ip = prefs.getString("ip_server", "137.135.83.217");
////        String port = prefs.getString("port_server", "1883");
////        String sub = prefs.getString("topic_sub", "/54226180432125161/sub");
////        String pub = prefs.getString("topic_pub", "/54226180432125161/pub");
//
////        writeS.putString("ip_server", ip);
////        writeS.putString("port_server", port);
////        writeS.putString("topic_sub", sub);
////        writeS.putString("topic_pub", pub);
////        writeS.apply();
//    }
//
//    private void connect() throws MqttException {
//        mqttClient = new MqttClient(serverUri, clientId, persistence);
//        // mqttClient.setCallback(this);
//        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.setAutomaticReconnect(true);
//        mqttConnectOptions.setCleanSession(false);
//        mqttConnectOptions.setConnectionTimeout(10);
//        mqttConnectOptions.setKeepAliveInterval(MqttConnectOptions.KEEP_ALIVE_INTERVAL_DEFAULT);
//        //mqttConnectOptions.setUserName(username);
//        //mqttConnectOptions.setPassword(password.toCharArray());
//        mqttClient.connect(mqttConnectOptions);
//    }
//
//    public void publishMessage(String payload) {
//        try {
//            if (mqttClient.isConnected() == false) {
//                mqttClient.connect();
//            }
//            MqttMessage message = new MqttMessage();
//            message.setPayload(payload.getBytes());
//            message.setQos(0);
//            mqttClient.publish(publictionTopic, message);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void subscribeToTopic(){
//        try {
//            mqttClient.subscribe(subscriptionTopic, new IMqttMessageListener() {
//                @Override
//                public void messageArrived(String s, MqttMessage message) throws Exception {
//                    final String payload = new String(message.getPayload());
//                    MessageParsing parsing = new MessageParsing(payload);
//                    parsing.processParsing();
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void myReconnect() {
//
//    }
//}
package mqtt;

import action.ActionManagerFactory;
import action.ActionManagerFactoryImpl;
import dao.sql.DaoFactoryImpl;
import exception.PersistentException;
import model.MqttSettings;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MqttService;
import service.impl.ServiceFactoryImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MqttHelper {
    //private static Logger log = LoggerFactory.getLogger(MqttHelper.class);
    private static volatile  MqttHelper INSTANCE = null;
    private MqttAsyncClient mqttClient;
    String serverUri;
    String clientId;
    private String subscriptionTopic;
    private String publictionTopic;
    final String username = "";
    final String password = "";
    MemoryPersistence persistence;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    static {
        try {
            if (INSTANCE == null)
                INSTANCE = new MqttHelper();
                //log.info("Создали объект");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static MqttHelper getInstance(){
        MqttHelper result = INSTANCE;
        if (result != null) {
            return result;
        }
        synchronized(MqttHelper.class) {
            if (INSTANCE == null) {
                try {
                    INSTANCE = new MqttHelper();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
            return INSTANCE;
        }
    }

    public MqttHelper() throws MqttException {
        loadSettings();
        connect();
    }

    public ActionManagerFactory getFactory() throws PersistentException {
        return new ActionManagerFactoryImpl(new ServiceFactoryImpl(new DaoFactoryImpl()));
    }

    private void loadSettings() {
        //log.info("Создали настройки");
        MqttSettings mqtt = new MqttSettings();
        try {
            MqttService service = new ServiceFactoryImpl(new DaoFactoryImpl()).getService(MqttService.class);
            mqtt = service.readSettings();
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        String ip = mqtt.getIp();
        String port = mqtt.getPort();
        publictionTopic = mqtt.getSubTopic();
        subscriptionTopic = mqtt.getPubTopic();
        serverUri = "tcp://".concat(ip).concat(":").concat(port);

        persistence = new MemoryPersistence();
        this.clientId = MqttClient.generateClientId();

        try {
            mqttClient = new MqttAsyncClient(serverUri, clientId, persistence);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws MqttException {
       // log.info("Создаем подключение");
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(MqttConnectOptions.KEEP_ALIVE_INTERVAL_DEFAULT);
        //mqttConnectOptions.setUserName(username);
        //mqttConnectOptions.setPassword(password.toCharArray());
        IMqttToken token = mqttClient.connect(mqttConnectOptions, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                //подключились
                //log.info("Подключились");
                subscribeToTopic();
                mqttClient.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable throwable) {
                        // log.info("connectionLost");
                    }

                    @Override
                    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                        final String payload = new String(mqttMessage.getPayload());
                        MessageParsing parsing = new MessageParsing(payload);
                        parsing.processParsing();
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                        //log.info("deliveryComplete");
                    }
                });
            }
            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                //log.info("onFailure");
            }
        });
        //token.waitForCompletion();
    }

    public void publishMessage(String payload) {
        try {
            if (!mqttClient.isConnected()) {
                IMqttToken token = mqttClient.connect();
                token.waitForCompletion();
            }
            MqttMessage message = new MqttMessage();
            message.setPayload(payload.getBytes());
            message.setQos(0);
            mqttClient.publish(publictionTopic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribeToTopic(){
        try {
            mqttClient.subscribe(subscriptionTopic, 0);
//            , 0, new IMqttMessageListener() {
//                @Override
//                public void messageArrived(String s, MqttMessage message) throws Exception {
//                    //log.info("Получили сообщение");
//                    final String payload = new String(message.getPayload());
//                    MessageParsing parsing = new MessageParsing(payload);
//                    parsing.processParsing();
//                }
//            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void myReconnect() {
        try {
            if (mqttClient.isConnected()) {
               mqttClient.disconnect(this, new IMqttActionListener(){
                    @Override
                    public void onSuccess(IMqttToken iMqttToken) {
                       // log.info("Пошли на перезагрузку");
                        loadSettings();
                        try {
                            connect();
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                      //  log.info("Ошибка перезагрузки");
                    }
                });
            }else{
                //log.info("Пошли на перезагрузку2");
                loadSettings();
                connect();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}