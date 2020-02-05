package com.youyue.framework.domain.order.response;

import com.youyue.framework.domain.order.YyOrders;
import com.youyue.framework.model.response.ResponseResult;
import com.youyue.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/26.
 */
@Data
@ToString
public class OrderResult extends ResponseResult {
    private YyOrders yyOrders;
    public OrderResult(ResultCode resultCode, YyOrders yyOrders) {
        super(resultCode);
        this.yyOrders = yyOrders;
    }


}
