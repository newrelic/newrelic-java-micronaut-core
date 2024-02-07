package io.micronaut.core.type;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class UnsafeExecutable<T, R> {
	
	public R invokeUnsafe(T instance, Object... arguments) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Micronaut","UnsafeExecutable",getClass().getSimpleName(),"invoke");
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Instance", instance.getClass().getName());
		return Weaver.callOriginal();
	}
}
