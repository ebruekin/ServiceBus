import com.azure.messaging.servicebus.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ReceivingFromQ {

    static String connectionString="";
    static String queueName="";

    public static void main(String[] args) throws InterruptedException {
        receiveMessages();
    }

    static void receiveMessages() throws InterruptedException{
        CountDownLatch countdownLatch = new CountDownLatch(1);
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(ReceivingFromQ::processMessage)
                .processError(context -> processError(context,countdownLatch))
                .buildProcessorClient();
        System.out.println("Starting the processors");
        processorClient.start();
        TimeUnit.SECONDS.sleep(100);
        processorClient.close();

    }

    private static void processMessage(ServiceBusReceivedMessageContext context){
        ServiceBusReceivedMessage message = context.getMessage();
        //System.out.println("Processing Message. Session %s, Sequence  #: %s. Contents: %s%n", message.getMessageId(),message.getSequenceNumber(),message.getBody());
        System.out.println(String.format("Processing Message. Session %s, Sequence #: %s. Contents: %s%n",
                message.getMessageId(),
                message.getSequenceNumber(),
                message.getBody()));
    }

    private static void processError(ServiceBusErrorContext context , CountDownLatch countdownLatch){}
}
