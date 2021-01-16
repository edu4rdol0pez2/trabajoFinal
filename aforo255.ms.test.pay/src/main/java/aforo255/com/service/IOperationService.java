package aforo255.com.service;

import java.util.Map;

import aforo255.com.domain.Operation;
 

public interface IOperationService {

	public Operation findById (Integer id );
	public Operation save (Operation transaction);	
	
	
	// Metodos Redis 
	/*public TransactionRedis findByIdRedis (String id ); 
	public void save (TransactionRedis transaction);
	public Map<String ,TransactionRedis> findAll();
	*/
}
