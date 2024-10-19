package com.sreenutech.rewards.serviceClient;

import com.sreenutech.rewards.dto.CardServiceClientRequest;
import com.sreenutech.rewards.dto.CardServiceClientResponse;

public interface ICardServiceClient {

	CardServiceClientResponse getCardInfo(CardServiceClientRequest cardServiceClientRequest);

}
