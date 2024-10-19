package com.sreenutech.rewards.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sreenutech.rewards.dto.RewardsDaoRequest;
import com.sreenutech.rewards.dto.RewardsDaoResponse;
import com.sreenutech.rewards.dto.TransactionsDao;
import com.sreenutech.rewards.exception.BusinessException;
import com.sreenutech.rewards.exception.SystemException;
import com.sreenutech.rewards.util.RewardsErrorInfoEnum;
import com.sreenutech.rewards.util.RewardsInfoConstants;

//@Component
public class RewardsInfoDaoImpl implements IRewardsInfoDao{

	@Override
	public RewardsDaoResponse getRewardsInfo(RewardsDaoRequest rewardsDaoRequest) throws BusinessException, SystemException {
		
		
		RewardsDaoResponse rewardsDaoResponse = new RewardsDaoResponse();
		
		String dbRespCode = "0";
		String dbRespMessage = "success";
		
		if ( RewardsInfoConstants.SUCCESS_RESPONSE_CODE.equals(dbRespCode) ) {
			
			rewardsDaoResponse.setResponseCode(dbRespCode);
			rewardsDaoResponse.setResponseMessage(dbRespMessage);
			
			List<TransactionsDao> transactionsDaoList = new ArrayList<TransactionsDao>();
			
			for( int i = 0; i < 50; i++ ) {
				
				TransactionsDao transactionsDao = new TransactionsDao();
				transactionsDao.setName("Jagan");
				transactionsDao.setMerchantName("Maha Lakshmi Stores");
			 	transactionsDao.setAmount(i*100);
				transactionsDao.setDate("19/07/2024");
				transactionsDao.setPoints(i);
				transactionsDao.setStatus("success");
				transactionsDao.setDescription("LifeStyle");
				transactionsDao.setTxnId(UUID.randomUUID().toString().substring(0,10));
				transactionsDao.setRemarks("NA");

				transactionsDaoList.add(transactionsDao);
			}
			
			rewardsDaoResponse.setTransactionsDao(transactionsDaoList);
		}
	
		else if( RewardsErrorInfoEnum.checkErrorCode(dbRespCode, RewardsInfoConstants.DATA_ERROR) ) {
			
			throw new BusinessException(dbRespCode, dbRespMessage);
		
		} else if( RewardsErrorInfoEnum.checkErrorCode(dbRespCode, RewardsInfoConstants.SYSTEM_ERROR) ) {
			
			throw new SystemException(dbRespCode, dbRespMessage);
		
		} else {
		
			throw new SystemException(RewardsInfoConstants.DB_UNKOWN_RESPONSE_CODE, RewardsInfoConstants.DB_UNKOWN_RESPONSE_MESSAGE);
		}
		
		
		return rewardsDaoResponse;
	}

}
