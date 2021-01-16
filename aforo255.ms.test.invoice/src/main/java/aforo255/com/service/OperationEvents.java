package aforo255.com.service;

import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aforo255.com.entity.Invoice;
import aforo255.com.entity.Operation;

@Service
public class OperationEvents {

	private Logger log = LoggerFactory.getLogger(OperationEvents.class);
	
	@Autowired
	private IInvoiceService invoiceService ; 
	
	@Autowired
	private ObjectMapper objectMapper ; 
	
	public void processOperationEvent(ConsumerRecord<Integer , String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		
		double newAmout = 0 ; 
		Invoice invoice = new Invoice ();
		Operation event = objectMapper.readValue(consumerRecord.value(), Operation.class);
		
		invoice = invoiceService.findById(event.getId_invoice());
		
		//cambiar el estado de la factura cuando esta se paga a través del microservicio de pago.
		
		//TOTAL : invoice.getAmount()
		//ENVIADO X POSTMAN : event.getAmount()
		
		
		
		
		if(invoice.getAmount() >0) {
			
			if(invoice.getAmount() >= event.getAmount()) {
				newAmout = invoice.getAmount() - event.getAmount();
				invoice.setAmount(newAmout);	
			}
			
		}
		
		
		if(invoice.getAmount() == 0 || invoice.getAmount() == 0.0) {
			invoice.setState(1);//SIN DEUDA
		}
		
	 
		//update amount
		//update estado
		
		/*
		switch(event.getType()) {
		case "deposito":
			newAmout =  account.getTotalAmount() + event.getAmount();
			break ; 
			
		case "retiro":
			newAmout =  account.getTotalAmount()  - event.getAmount();
			break ; 	
		
		}
		
		account.setTotalAmount(newAmout);
		*/
		
		
		
		log.info("Actulizando N° invoice *** "+event.getId_invoice());
		invoiceService.save(invoice);
	} 
	
}

