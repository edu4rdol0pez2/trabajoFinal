package aforo255.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aforo255.com.entity.Transaction;
import aforo255.com.repository.ITransactionRepository;
 

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	ITransactionRepository repository ; 
	
	@Override
	public Transaction save(Transaction transaction) {
		// TODO Auto-generated method stub
		return repository.save(transaction);
	}

	@Override
	public Iterable<Transaction> findByid_invoice(Integer id_invoice) {
		// TODO Auto-generated method stub
		return repository.findByid_invoice(id_invoice);
	}

	@Override
	public Iterable<Transaction> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
