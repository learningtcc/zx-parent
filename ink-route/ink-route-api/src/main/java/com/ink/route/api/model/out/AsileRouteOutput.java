package com.ink.route.api.model.out;

import java.io.Serializable;

/**
 * 返回渠道编号
 *
 */
public class AsileRouteOutput  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8718738717589465722L;
	private String asileCode;// 渠道编号
    /**响应码**/
    private String reponseCode;
    /**响应详细信息**/
    private String reponseMsg;
	public String getAsileCode() {
		return asileCode;
	}

	public void setAsileCode(String asileCode) {
		this.asileCode = asileCode;
	}

	public String getReponseCode() {
		return reponseCode;
	}

	public void setReponseCode(String reponseCode) {
		this.reponseCode = reponseCode;
	}

	public String getReponseMsg() {
		return reponseMsg;
	}

	public void setReponseMsg(String reponseMsg) {
		this.reponseMsg = reponseMsg;
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
