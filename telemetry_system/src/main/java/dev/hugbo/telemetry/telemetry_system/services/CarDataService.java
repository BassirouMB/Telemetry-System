package dev.hugbo.telemetry.telemetry_system.services;

import org.eclipse.paho.mqttv5.client.IMqttDeliveryToken;
import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.stereotype.Service;

import dev.hugbo.telemetry.telemetry_system.CanFramesProtos;


import jakarta.annotation.PostConstruct;

@Service
public class CarDataService {

    private final String topic = "mqttExamples";
    private final String content = "Get rekt lol";
    private final int qos = 2;
    private final String broker = "tcp://localhost:1883";
    private final String clientId = "JavaSample";
    private final MemoryPersistence persistence = new MemoryPersistence();

    private final MqttCallback callback = new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause);
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    try {
                        CanFramesProtos.CanFrame canFrame = CanFramesProtos.CanFrame.parseFrom(message.getPayload());
                        System.out.println("Received CAN Frame on topic " + topic + ": " + canFrame);
                    } catch(Exception e) {
                        System.out.println("Payload is not a valid CAN frame: " + e.getMessage());
                    }
                }

                
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used for receiving, only for confirming publishes
                }

                @Override
                public void disconnected(MqttDisconnectResponse disconnectResponse) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mqttErrorOccurred(MqttException exception) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void deliveryComplete(IMqttToken token) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void authPacketArrived(int reasonCode, MqttProperties properties) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
    };

    
    // static {
    //    System.loadLibrary("libname");
    // }

    @PostConstruct
    public void serviceFunction(){
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectionOptions connOpts = new MqttConnectionOptions();
            connOpts.setCleanStart(true);
            sampleClient.connect(connOpts);
            sampleClient.subscribe("#", 2);
            sampleClient.setCallback(callback);

        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
        }
    }

    

    // private native CarData decode(byte[] canFrame);
    // private native byte[] encode(CarData configs);
}
