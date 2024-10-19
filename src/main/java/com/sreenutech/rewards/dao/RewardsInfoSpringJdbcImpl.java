package com.sreenutech.rewards.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.sreenutech.rewards.dto.RewardsDaoRequest;
import com.sreenutech.rewards.dto.RewardsDaoResponse;
import com.sreenutech.rewards.dto.TransactionsDao;
import com.sreenutech.rewards.exception.BusinessException;
import com.sreenutech.rewards.exception.SystemException;
import com.sreenutech.rewards.util.RewardsErrorInfoEnum;
import com.sreenutech.rewards.util.RewardsInfoConstants;


@Component
//@Primary
public class RewardsInfoSpringJdbcImpl extends StoredProcedure implements RowMapper<TransactionsDao>, IRewardsInfoDao{
	
	@Autowired
	public RewardsInfoSpringJdbcImpl(JdbcTemplate jdbcTemplate) {
		
		super(jdbcTemplate, RewardsInfoConstants.SP_NAME);
		
		compileSP();
	}



	private void compileSP() {
		
		// register input params
		
		declareParameter(new SqlParameter("CLIENT_ID_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CARDNUM_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CHANNEL_ID", Types.VARCHAR));
		
		// register output params
		
		declareParameter(new SqlOutParameter("RESP_CODE_OUT", Types.VARCHAR));
		declareParameter(new SqlOutParameter("RESP_MSG_OUT", Types.VARCHAR));
		
		// register ResultSet
		
		declareParameter(new SqlReturnResultSet("rewardsResult", this));
		
		compile();
	}




	@Override
	public RewardsDaoResponse getRewardsInfo(RewardsDaoRequest rewardsDaoRequest)
			throws BusinessException, SystemException {
		
		RewardsDaoResponse rewardsDaoResponse = new RewardsDaoResponse();
		
		try {
		
			//prepare the SP Request
			
			Map<String, Object> inParams = new HashMap<String, Object>();
			
			inParams.put("CLIENT_ID_IN", rewardsDaoRequest.getClientId());
			inParams.put("CARDNUM_IN", rewardsDaoRequest.getCardNum());
			inParams.put("CHANNEL_ID", "online");
			
			
			//call the Stored Procedure and get the response
			
			Map<String, Object> respMap =  super.execute(inParams);
			
			// get the resp from db
			
			String dbRespCode = respMap.get("RESP_CODE_OUT").toString();
			String dbRespMsg = respMap.get("RESP_MSG_OUT").toString();
			
			List<TransactionsDao> txnsDaoList = new ArrayList<TransactionsDao>();
			
			if(RewardsInfoConstants.SUCCESS_RESPONSE_CODE.equals(dbRespCode)) {
				
				rewardsDaoResponse.setResponseCode(dbRespMsg);
				rewardsDaoResponse.setResponseMessage(dbRespMsg);

				System.out.println("Entered into getRewardsInfo method .....");
				
				// this will call mapRow method internally and gives response 
				
				txnsDaoList = (List<TransactionsDao>) respMap.get("rewardsResult");
				
				rewardsDaoResponse.setTransactionsDao(txnsDaoList);
				
				System.out.println(txnsDaoList);
			
			} else if (RewardsErrorInfoEnum.checkErrorCode(dbRespCode, RewardsInfoConstants.DATA_ERROR)) {
				
				throw new BusinessException(dbRespCode, dbRespMsg);
			
			} else if (RewardsErrorInfoEnum.checkErrorCode(dbRespCode, RewardsInfoConstants.SYSTEM_ERROR)) {
				
				throw new SystemException(dbRespCode, dbRespMsg);
			
			} else {
				
				throw new SystemException(RewardsInfoConstants.DB_UNKOWN_RESPONSE_CODE, RewardsInfoConstants.DB_UNKOWN_RESPONSE_MESSAGE);
			}
			
			
		} catch (BusinessException exception) {
		
			throw exception;
		
		} catch (SystemException exception) {
			
			throw exception;
		}
		catch (Exception exception) {
		
			throw exception;
		}
		
		return rewardsDaoResponse;
	}

	
	@Override
	public TransactionsDao mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		System.out.println("enterd into mapRow method....");
		
		TransactionsDao transactionsDao = new TransactionsDao();
		
		transactionsDao.setAmount(Float.valueOf(rs.getString(4)));
		transactionsDao.setDate(rs.getString(2));
		transactionsDao.setDescription(rs.getString(6));
		transactionsDao.setMerchantName(rs.getString(5));
		transactionsDao.setName(rs.getString(3));
		transactionsDao.setPoints(rs.getLong(rowNum));
		transactionsDao.setRemarks(rs.getString(8));
		transactionsDao.setStatus(rs.getString(7));
		transactionsDao.setTxnId(rs.getString(1));
		
		return transactionsDao;
	}



}
