package com.ink.trade.api.model.out;

import java.io.Serializable;

/**
 * 返回渠道编号
 *
 */
public class AsileRouteOutput extends BaseTradeOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8718738717589465722L;
	private String asileCode;// 渠道编号

	public String getAsileCode() {
		return asileCode;
	}

	public void setAsileCode(String asileCode) {
		this.asileCode = asileCode;
	}

	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AsileRouteOutput [asileCode=");
        builder.append(asileCode);
        builder.append("]");
        return builder.toString();
    }

}
