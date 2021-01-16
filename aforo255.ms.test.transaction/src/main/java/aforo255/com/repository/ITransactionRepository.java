package aforo255.com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import aforo255.com.entity.Transaction;

public interface ITransactionRepository extends MongoRepository<Transaction, String> {

	@Query("{'id_invoice':?0}")
	public Iterable<Transaction> findByid_invoice(Integer id_invoice);
}
