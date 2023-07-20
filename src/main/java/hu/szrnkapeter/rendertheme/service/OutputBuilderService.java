package hu.szrnkapeter.rendertheme.service;

import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import hu.szrnkapeter.rendertheme.model.RuleData;

public interface OutputBuilderService {

	public static String buildOutput(List<RuleData> ruleDataList, String template) {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("resource.loader", "class");
		velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocityEngine.init();
		   
		Template t = velocityEngine.getTemplate(template);
		    
		VelocityContext context = new VelocityContext();
		context.put("ruleDataList", ruleDataList);
		    
		StringWriter writer = new StringWriter();
		t.merge( context, writer );
		
		return writer.toString();
	}
}