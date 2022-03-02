package hu.szrnkapeter.rendertheme.service;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public interface XmlReaderService {

	public static NodeList getNodeList(File fXmlFile) throws SAXException, IOException, ParserConfigurationException {
		final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		final Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		
		return doc.getElementsByTagName("rule");
	}
}