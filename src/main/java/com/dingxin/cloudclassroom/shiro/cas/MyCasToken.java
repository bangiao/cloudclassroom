package com.dingxin.cloudclassroom.shiro.cas;

import org.apache.shiro.cas.CasToken;

public class MyCasToken extends CasToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MyCasToken(String ticket) {
		super(ticket);
		// TODO Auto-generated constructor stub
	}
    
	public MyCasToken(String ticket,String url) {
		super(ticket);
		this.url = url;
	}
    
}