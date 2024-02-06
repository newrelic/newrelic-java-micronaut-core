package io.micronaut.core.bind;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class BoundExecutable<T, R> {

	@Trace
	public R invoke(T instance) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","Micronaut","BoundExecutable",getClass().getSimpleName(),"invoke");
		traced.addCustomAttribute("Instance", instance.getClass().getName());
		return Weaver.callOriginal();
	}
}
