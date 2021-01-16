package aforo255.com.listener;
 

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import aforo255.com.service.TransactionEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class ConsumerListener {

	@Autowired
	private TransactionEvents transactionEvents;
	
	private Logger log = LoggerFactory.getLogger(ConsumerListener.class);
	@KafkaListener(topics = {"transaction-events-test"})
	
	public void OnMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		log.info("*************** MICROSERVICE TRANSACTION *******************");
		log.info("ConsumerRecord : {}", consumerRecord.value());
		
		transactionEvents.processTransactionEvent(consumerRecord);
	}
	
	
}
