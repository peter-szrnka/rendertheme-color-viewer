package hu.szrnkapeter.rendertheme;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import hu.szrnkapeter.rendertheme.model.RuleData;
import hu.szrnkapeter.rendertheme.service.OutputBuilderService;
import hu.szrnkapeter.rendertheme.service.XmlProcessorService;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			throw new RuntimeException("Please provide a file path!");
		}
		
		String templatePath = "src/main/resources/output-template.html";
		if (args.length == 2) {
			templatePath = args[1];
		}
		
		final File fXmlFile = new File(args[0]);
		final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		final Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		final NodeList nList = doc.getElementsByTagName("rule");
		List<RuleData> ruleDataList = XmlProcessorService.getRules(nList);

		final BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.html")));
		bw.write(OutputBuilderService.buildOutput(ruleDataList, templatePath));
		bw.close();
	}
}