package aforo255.com.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aforo255.com.dao.ITransactionDao;
import aforo255.com.domain.Operation;
 

@Service
public class OperationService  implements IOperationService{

	
	@Autowired
	private ITransactionDao transactiondao;
	
	/*
	@Autowired
	private ITransactionRedis transactionRedis ;  
	*/
	
	
	@Override
	public Operation findById(Integer id) {
		// TODO Auto-generated method stub
		return transactiondao.findById(id).orElse(null);
	}

	@Override
	public Operation save(Operation transaction) {
		// TODO Auto-generated method stub
		return transactiondao.save(transaction);
	}

	 
	/*
	@Override
	public TransactionRedis findByIdRedis(String id) {
		// TODO Auto-generated method stub
		return transactionRedis.findById(id);
	}

	@Override
	public void save(TransactionRedis transaction) {
		// TODO Auto-generated method stub
		transactionRedis.save(transaction);
	}

	@Override
	public Map<String, TransactionRedis> findAll() {
		// TODO Auto-generated method stub
		return transactionRedis.findAll();
	}*/

	
	
}
