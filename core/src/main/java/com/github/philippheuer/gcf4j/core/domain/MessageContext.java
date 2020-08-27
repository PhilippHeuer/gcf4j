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
public class MessageContext implements IMessageContext {

	// Instance
	private IMessageInstance instance;

	// Channel on the instance
	private IMessageChannel channel;

	// Author
	private IMessageAuthor author;

	// Message
	private IMessage message;

	// Message Responder
	@JsonIgnore
	private IMessageResponder responder;

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