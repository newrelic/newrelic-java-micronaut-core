package io.micronaut.core.execution;

import java.util.function.BiConsumer;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.micronaut.core_4.NRBiConsumerWrapper;

@Weave
class ImperativeExecutionFlowImpl {

	@NewField
	protected Token token = null;

	<T> ImperativeExecutionFlowImpl(T value, Throwable error) {
		if(token == null) {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Trace(async = true)
	public void onComplete(BiConsumer<? super Object, Throwable> fn) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
			NRBiConsumerWrapper wrapper = new NRBiConsumerWrapper(fn);
			fn = wrapper;
		}
		Weaver.callOriginal();
	}
}
