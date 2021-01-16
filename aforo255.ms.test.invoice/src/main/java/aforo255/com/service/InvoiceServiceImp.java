package aforo255.com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aforo255.com.entity.Invoice;
import aforo255.com.repository.InvoiceRepository;

@Service
public class InvoiceServiceImp implements IInvoiceService{

	@Autowired
	InvoiceRepository invoicerepository;
	 
	@Override
	public List<Invoice> findAll() {
		return (List<Invoice>)invoicerepository.findAll();
	}

	@Override
	public Invoice findById(Integer id) {
		return invoicerepository.findById(id).orElse(null);
	}

	@Override
	public Invoice save(Invoice account) {
		return invoicerepository.save(account);
	}

	
}
