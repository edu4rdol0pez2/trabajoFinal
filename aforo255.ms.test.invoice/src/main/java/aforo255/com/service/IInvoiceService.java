package aforo255.com.service;

import java.util.List;
import aforo255.com.entity.Invoice;

public interface IInvoiceService {
	public List<Invoice> findAll();
	public Invoice findById (Integer id ); 
	public Invoice save (Invoice invoice);
}
