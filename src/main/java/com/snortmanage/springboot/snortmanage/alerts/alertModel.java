package com.snortmanage.springboot.snortmanage.alerts;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class alertModel {
	@Id
	private String time;	
	private String rid;
	private String msg;
	private String priority;
	private String protocol;
	private String srcip;
	private String src_port;
	private String flow;
	private String dst_ip;
	private String dst_port;
    public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getSrcip() {
		return srcip;
	}
	public void setSrcip(String srcip) {
		this.srcip = srcip;
	}
	public String getSrc_port() {
		return src_port;
	}
	public void setSrc_port(String src_port) {
		this.src_port = src_port;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getDst_ip() {
		return dst_ip;
	}
	public void setDst_ip(String dst_ip) {
		this.dst_ip = dst_ip;
	}
	public String getDst_port() {
		return dst_port;
	}
	public void setDst_port(String dst_port) {
		this.dst_port = dst_port;
	}
	
	
}
