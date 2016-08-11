package com.ink.route.api.service.rule;

import com.ink.route.api.model.in.AsileRouteInput;
import com.ink.route.api.model.out.AsileRouteOutput;

/**
 * 渠道路由规则，返回渠道编号
 *
 */
public interface IAsileRoute {

	/**
	 * 
	 * @Title: getTradeAsile
	 * @Description: 路由选择接口
	 * @param @param asileRouteInput
	 * @param @return
	 * @param @throws Exception
	 * @return AsileRouteOutput
	 * @throws
	 */
	public AsileRouteOutput getTradeAsile(AsileRouteInput asileRouteInput)
			;

	/**
	 * 
	 * @Title: asileRouteDemote
	 * @Description: 银行优先级降级
	 * @param @param asileId
	 * @return void
	 * @throws
	 */
	public AsileRouteOutput degradeAsile(String asileCode, String bankCode,String payType);
	

	/**
	 * 四要素认证路由
	 * @param input
	 * @return
     */
	AsileRouteOutput authRoute(AsileRouteInput input);

}
