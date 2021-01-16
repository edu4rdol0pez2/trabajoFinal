package aforo255.com.repository;

import org.springframework.data.repository.CrudRepository;
import aforo255.com.entity.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer>{
	
}
