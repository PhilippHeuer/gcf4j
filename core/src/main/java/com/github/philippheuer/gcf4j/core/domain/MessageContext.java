package com.github.philippheuer.gcf4j.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.philippheuer.gcf4j.api.domain.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Provides information about the context in which a command was executed.
 */
@Getter
@EqualsAndHashCode
@ToString
public class MessageContext implements IMessageContext {

	// Instance
	private final IMessageInstance instance;

	// Channel on the instance
	private final IMessageChannel channel;

	// Author
	private final IMessageAuthor author;

	// Message
	private final IMessage message;

	// Message Responder
	@JsonIgnore
	private final IMessageResponder responder;

	/**
	 * Command Context
	 *
	 * @param instance
	 * @param channel
	 * @param author
	 * @param message
	 */
	public MessageContext(IMessageInstance instance, IMessageChannel channel, IMessageAuthor author, IMessage message, IMessageResponder responder) {
		this.instance = instance;
		this.channel = channel;
		this.author = author;
		this.message = message;
		this.responder = responder;
	}

}