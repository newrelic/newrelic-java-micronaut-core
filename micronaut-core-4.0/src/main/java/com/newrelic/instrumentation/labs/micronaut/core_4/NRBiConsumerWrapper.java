package com.newrelic.instrumentation.labs.micronaut.core_4;

import java.util.function.BiConsumer;

import com.newrelic.api.agent.NewRelic;

public class NRBiConsumerWrapper<R> implements BiConsumer<R, Throwable> {

	BiConsumer<R, Throwable> delegate = null;
	
	public NRBiConsumerWrapper(BiConsumer<R, Throwable> d) {
		delegate = d;
	}
	
	@Override
	public void accept(R t, Throwable u) {
		if(u != null) {
			NewRelic.noticeError(u);
		}
		if(delegate != null) {
			delegate.accept(t, u);
		}
	}

}
