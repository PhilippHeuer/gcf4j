package com.github.philippheuer.gcf4j.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	// Message Responder
	@JsonIgnore
	private IGCFMessageResponder responder;

	/**
	 * Command Context
	 *
	 * @param instance
	 * @param channel
	 * @param author
	 * @param message
	 */
	public GCFMessageContext(IGCFInstance instance, IGCFChannel channel, IGCFMember author, IGCFMessage message, IGCFMessageResponder responder) {
		this.instance = instance;
		this.channel = channel;
		this.author = author;
		this.message = message;
		this.responder = responder;
	}

}