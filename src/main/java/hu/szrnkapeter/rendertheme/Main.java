package hu.szrnkapeter.rendertheme;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import hu.szrnkapeter.rendertheme.model.RuleData;
import hu.szrnkapeter.rendertheme.service.OutputBuilderService;
import hu.szrnkapeter.rendertheme.service.XmlProcessorService;
import hu.szrnkapeter.rendertheme.service.XmlReaderService;

public class Main {

	public static void main(String[] args) throws Exception {
		new Main().execute(args);
	}
	
	public void execute(String[] args) throws IOException, SAXException, ParserConfigurationException {
		if (args.length < 1) {
			throw new IllegalArgumentException("Please provide a file path!");
		}
		
		String templatePath = "src/main/resources/output-template.tpl";
		if (args.length == 2) {
			templatePath = args[1];
		}

		List<RuleData> ruleDataList = XmlProcessorService.getRules(XmlReaderService.getNodeList(new File(args[0])));
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.html")))) {
			bw.write(OutputBuilderService.buildOutput(ruleDataList, templatePath));
		}
	}
}