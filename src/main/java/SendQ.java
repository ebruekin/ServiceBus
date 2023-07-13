import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

public class SendQ {

    static String connectionString="Endpoint=sb://testname-s.servicebus.windows.net/;SharedAccessKeyName=policy001;SharedAccessKey=9O52sP0y9sMT9HQpeiKI9CLozaKo8bdJA+ASbLM+JsY=;EntityPath=fqueue";
    static String queueName="fqueue";

    public static void main(String[] args) {
        sendMessage();
    }

    static void sendMessage(){
        //send simple message to ASB
        ServiceBusSenderClient serviceBusSenderClient = new ServiceBusClientBuilder()
                                                            .connectionString(connectionString)
                                                             .sender().queueName(queueName)
                                                               .buildClient();

        serviceBusSenderClient.sendMessage(new ServiceBusMessage("I sent this from java"));
        System.out.println("message has been sent :  I sent this from java " );
    }

}
