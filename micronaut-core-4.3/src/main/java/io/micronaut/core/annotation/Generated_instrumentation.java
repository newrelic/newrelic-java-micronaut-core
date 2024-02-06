package io.micronaut.core.annotation;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.WeaveIntoAllMethods;
import com.newrelic.api.agent.weaver.WeaveWithAnnotation;

@WeaveWithAnnotation(annotationClasses = {"io.micronaut.core.annotation.Generated"},type = MatchType.Interface)
public abstract class Generated_instrumentation {

	@WeaveWithAnnotation(annotationClasses = {"io.micronaut.core.annotation.Generated"})
	@WeaveIntoAllMethods
	@Trace
	private static void instrumentation() {
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        StackTraceElement first = traces[1];
        String methodName = first.getMethodName();
        String classname = first.getClassName();
        NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Micronaut","Generated",classname,methodName);
		
	}
	
}
