package aforo255.com.producer;

import org.springframework.stereotype.Component;


import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
  
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aforo255.com.domain.Operation;
import aforo255.com.service.IOperationService;




@Component
public class PayEventProducer {

	String topic = "transaction-events-test";
	private Logger log = LoggerFactory.getLogger(PayEventProducer.class);
	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	@Autowired
	ObjectMapper objectMapper;
	
	//@Autowired
	//private ITransactionService service ; 

	//AQUI GUARDA EN KAFKA Y REDIS
	
	public ListenableFuture<SendResult<Integer, String>> sendPayEvent(Operation payEvent)
			throws JsonProcessingException {
		Integer key = payEvent.getId_operation();
		String value = objectMapper.writeValueAsString(payEvent);

		ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value, topic);
		ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {//GUARDA EN KAFKA

			@Override
			public void onSuccess(SendResult<Integer, String> result) {//VALIDA SI GUARDO BIEN EN KAFKA
				try {
					handleSuccess(key, value, result);
				} catch (JsonProcessingException e) {

					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable ex) {//HUBO ERROR
				handleFailure(key, value, ex);

			}
		});

		return listenableFuture;
	}

	private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
		List<Header> recordHeaders = List.of(new RecordHeader("pay-event-source", "scanner".getBytes()));
		return new ProducerRecord<>(topic, null, key, value, recordHeaders);
	}

	private void handleFailure(Integer key, String value, Throwable ex) {
		log.error("Error sending the Message and the Execption is {} ", ex.getMessage());
		try {
			throw ex;
		} catch (Throwable throwable) {
			log.error("Error in OnFailure {} ", throwable.getMessage());
		}

	}

	private void handleSuccess(Integer key, String value, SendResult<Integer, String> result)
			throws JsonMappingException, JsonProcessingException {

		/*
		TransactionRedis trxRedis = objectMapper.readValue(value, TransactionRedis.class);
		service.save(trxRedis);
		*/
		
		log.info("Message Sent Successfully for the key :{} and the value is {},partition is {}", key, value,
				result.getRecordMetadata().partition());
	}

}
