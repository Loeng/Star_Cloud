package cn.com.bonc.sce.tool;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class KafkaProducer {

    private static Producer producer;
    private static final String ZK_ADDRESS = "192.168.1.23:2181";
    private static final String KAFKA_ADDRESS = "192.168.1.23:9092";
    private static final String TOPIC = "mytopic";

    static {
        producer = createProducer();
    }

    private KafkaProducer(){}

    public static Producer getProducer(){
        if( producer == null ){
            return createProducer();
        }
        return producer;
    }

    private static Producer createProducer(){
        Properties properties = new Properties();
        properties.put( "zookeeper.connect", ZK_ADDRESS );//声明zk
        properties.put( "serializer.class", StringEncoder.class.getName() );
        properties.put( "metadata.broker.list", KAFKA_ADDRESS );// 声明kafka broker
        return new Producer< Integer, String >( new ProducerConfig( properties ) );
    }

    public static boolean sendMessageByKafka(String message){
        Producer producer = getProducer();
        KeyedMessage keyedMessage = new KeyedMessage<Integer, String>( TOPIC, message );
        try {
            producer.send(keyedMessage);
        }catch (Exception e){
            log.error( "kafka发送消息出错" );
            return false;
        }
        return true;
    }

//    public static void main(String[] add){
//        Producer producer = KafkaProducer.getProducer();
//        KeyedMessage keyedMessage = new KeyedMessage<Integer, String>( TOPIC, "测试一下" );
//        for(int i = 0;i<5;i++){
//            producer.send(keyedMessage);
//            try {
//                Thread.sleep(1000);
//            }catch (InterruptedException e){
//                System.out.println(e.getMessage());
//            }
//        }
//
////        KafkaProducer.sendMessageByKafka("测试一下");
//    }
}
