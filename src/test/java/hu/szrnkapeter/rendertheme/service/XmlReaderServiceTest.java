package hu.szrnkapeter.rendertheme.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Unit test of {@link XmlReaderService}
 */
public class XmlReaderServiceTest {

	@Test
	public void testFileMissing() {
		assertThrows(FileNotFoundException.class, () -> XmlReaderService.getNodeList(new File("src/test/resources/sample0.xml")));
	}
	
	@Test
	public void testSuccess() throws Exception {
		URL resource = getClass().getClassLoader().getResource("sample1.xml");
		NodeList result = XmlReaderService.getNodeList(new File(resource.toURI()));
		assertNotNull(result);
		
		Node a = (Node) result.item(0);
		assertNotNull(a);
		assertEquals("rule", a.getNodeName());
	}
}