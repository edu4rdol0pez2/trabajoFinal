package aforo255.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
 
import com.fasterxml.jackson.core.JsonProcessingException;

import aforo255.com.domain.Operation;
import aforo255.com.producer.PayEventProducer;
import aforo255.com.service.IOperationService;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PayEventController {

	@Autowired
	private IOperationService service;
	
	@Autowired
	PayEventProducer eventProducer;

	private Logger log = LoggerFactory.getLogger(PayEventController.class);
	
	@PostMapping("/v1/payevent")
	public ResponseEntity<Operation> postPayEvent(@RequestBody Operation transaction) throws JsonProcessingException {
		log.info("antes de transql");
		Operation transql = service.save(transaction);
		log.info("despues de transql");
		log.info("antes de postPayEvent");
		eventProducer.sendPayEvent(transql);
		log.info("despues de postPayEvent");
		return ResponseEntity.status(HttpStatus.CREATED).body(transql);
	}
	
  /*
	@GetMapping("/all")
	public Map<String , TransactionRedis> getAll(){		
		return service.findAll();
	}	*/
	
	
}

