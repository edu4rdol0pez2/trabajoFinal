package aforo255.com.dao;

import org.springframework.data.repository.CrudRepository;
import aforo255.com.domain.Operation;

public interface ITransactionDao extends CrudRepository<Operation, Integer> {
	
}
