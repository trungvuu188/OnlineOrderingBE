package com.springboot.osahaneat.service.imp;

import com.springboot.osahaneat.payload.request.OrderRequest;

public interface OrderServiceImp {
	boolean insertOrder(OrderRequest orderRequest);
}
