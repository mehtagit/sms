package com.sms.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sms.util.Profile;

@Entity
@Table(name = "requests")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@JsonInclude(Include.NON_NULL)
public class Request {

	@Id
	private String tid;

	@NotBlank
	private String fromMsisdn;
	private String toMsisdn;
	private String message;
	private String messageId;
	private String deliveryMask;
	private String fdaId;
	private long reqestTime;
	private long submitTime;
	private Profile profile;
	private String forwardMsisdn;
	private String requestType;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getFromMsisdn() {
		return fromMsisdn;
	}

	public void setFromMsisdn(String fromMsisdn) {
		this.fromMsisdn = fromMsisdn;
	}

	public String getToMsisdn() {
		return toMsisdn;
	}

	public void setToMsisdn(String toMsisdn) {
		this.toMsisdn = toMsisdn;
	}

	public String getForwardMsisdn() {
		return forwardMsisdn;
	}

	public void setForwardMsisdn(String forwardMsisdn) {
		this.forwardMsisdn = forwardMsisdn;
	}

	@JsonIgnoreProperties
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@JsonIgnoreProperties
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getDeliveryMask() {
		return deliveryMask;
	}

	public void setDeliveryMask(String deliveryMask) {
		this.deliveryMask = deliveryMask;
	}

	public String getFdaId() {
		return fdaId;
	}

	public void setFdaId(String fdaId) {
		this.fdaId = fdaId;
	}

	public long getReqestTime() {
		return reqestTime;
	}

	public void setReqestTime(long reqestTime) {
		this.reqestTime = reqestTime;
	}

	public long getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(long submitTime) {
		this.submitTime = submitTime;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return "Request [" + (tid != null ? "tid=" + tid + ", " : "")
				+ (fromMsisdn != null ? "fromMsisdn=" + fromMsisdn + ", " : "")
				+ (toMsisdn != null ? "toMsisdn=" + toMsisdn + ", " : "")
				+ (message != null ? "message=" + message + ", " : "")
				+ (messageId != null ? "messageId=" + messageId + ", " : "")
				+ (deliveryMask != null ? "deliveryMask=" + deliveryMask + ", " : "")
				+ (fdaId != null ? "fdaId=" + fdaId + ", " : "") + "reqestTime=" + reqestTime + ", submitTime="
				+ submitTime + ", " + (profile != null ? "profile=" + profile + ", " : "")
				+ (forwardMsisdn != null ? "forwardMsisdn=" + forwardMsisdn + ", " : "")
				+ (requestType != null ? "requestType=" + requestType + ", " : "")
				+ (createdAt != null ? "createdAt=" + createdAt + ", " : "")
				+ (updatedAt != null ? "updatedAt=" + updatedAt : "") + "]";
	}

}
