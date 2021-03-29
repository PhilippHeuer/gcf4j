package com.github.philippheuer.gcf4j.core.util;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.core.domain.GCFInstance;
import com.github.philippheuer.gcf4j.core.domain.GCFMember;
import com.github.philippheuer.gcf4j.core.domain.GCFMessage;
import com.github.philippheuer.gcf4j.core.domain.GCFMessageContext;
import io.opentracing.Tracer;
import io.opentracing.mock.MockTracer;

public class MessageContextTestUtils {

    private static final Tracer TRACER = new MockTracer();

    public static IGCFMessageContext getMessageWithFakeContext(String text) {
        return GCFMessageContext.builder()
                .span(TRACER.buildSpan("testspan").start())
                .instance(
                        GCFInstance.builder()
                                .type("dummy")
                                .id("1")
                                .build()
                )
                .author(
                        GCFMember.builder()
                                .id("1")
                                .bot(false)
                                .build()
                )
                .message(
                        GCFMessage.builder()
                                .text(text)
                                .build()
                )
                .build();
    }

}
