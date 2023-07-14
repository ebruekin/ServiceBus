import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusMessageBatch;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import java.util.Arrays;
import java.util.List;

public class sendQinBatch {
    static String connectionString="";
    static String queueName="";

    //multiple messages at the same time
    public static void main(String[] args) {
        sendMessageBatch();
    }

    static List<ServiceBusMessage> createMessages(){
        ServiceBusMessage[] messages = {
         new ServiceBusMessage("message A batch test"),
         new ServiceBusMessage("message B batch test"),
         new ServiceBusMessage("message C batch test")
        };
        return Arrays.asList(messages);
    }

    static void sendMessageBatch(){
        //send simple message to ASB
        ServiceBusSenderClient serviceBusSenderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender().queueName(queueName)
                .buildClient();
        ServiceBusMessageBatch messageBatch= serviceBusSenderClient.createMessageBatch();

        List<ServiceBusMessage> listOfMessages = createMessages();
        for(ServiceBusMessage message: listOfMessages){
            messageBatch.tryAddMessage(message);
        }
        serviceBusSenderClient.sendMessages(messageBatch);
        System.out.println("sent completed");
        serviceBusSenderClient.close();

    }
}
