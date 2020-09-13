package com.github.philippheuer.gcf4j.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.philippheuer.gcf4j.api.IMessageConnector;
import com.github.philippheuer.gcf4j.api.domain.*;
import lombok.*;

/**
 * Provides information about the context in which a command was executed.
 */
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Builder
public class GCFMessageContext implements IGCFMessageContext {

	// Instance
	private IGCFInstance instance;

	// Channel on the instance
	private IGCFChannel channel;

	// Author
	private IGCFMember author;

	// Message
	private IGCFMessage message;

	// Author
	private IGCFMember botMember;

	// Message Responder
	@JsonIgnore
	private IMessageConnector connector;

	/**
	 * Command Context
	 *
	 * @param instance Instance
	 * @param channel Channel
	 * @param author Author
	 * @param message Message
	 * @param botMember Bot Member Info
	 * @param connector Message Connector
	 */
	public GCFMessageContext(IGCFInstance instance, IGCFChannel channel, IGCFMember author, IGCFMessage message, IGCFMember botMember, IMessageConnector connector) {
		this.instance = instance;
		this.channel = channel;
		this.author = author;
		this.message = message;
		this.botMember = botMember;
		this.connector = connector;
	}

}