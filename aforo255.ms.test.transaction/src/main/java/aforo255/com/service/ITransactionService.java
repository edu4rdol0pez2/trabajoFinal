package aforo255.com.service;

import aforo255.com.entity.Transaction;

public interface ITransactionService {
	public Transaction save (Transaction transaction);
	public Iterable<Transaction> findByid_invoice (Integer id_invoice);
	public Iterable <Transaction> findAll();
}
