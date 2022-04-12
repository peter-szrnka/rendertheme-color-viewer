package hu.szrnkapeter.rendertheme.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import hu.szrnkapeter.rendertheme.model.RuleData;
import hu.szrnkapeter.rendertheme.util.TestUtils;

/**
 * Unit test of {@link XmlProcessorService}
 */
class XmlProcessorServiceTest {
	
	@Test
	void testPrivateConstructor() {
		assertDoesNotThrow(() -> TestUtils.testPrivateConstructor(XmlProcessorService.class));
	}
	
	@AfterEach
	@BeforeEach
	public void cleanup() {
		XmlProcessorService.ruleDataList = new ArrayList<>();
	}

	@Test
	void testNodeListNull() {
		NodeList input = null;
		List<RuleData> result = XmlProcessorService.getRules(input);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	void testNodeListEmpty() {
		NodeList input = null;
		List<RuleData> result = XmlProcessorService.getRules(input);

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	void testNodeListHasOnlyAttributeNode() {
		NodeList input = Mockito.mock(NodeList.class);
		Mockito.when(input.getLength()).thenReturn(1);
		
		Node mockNode = Mockito.mock(Node.class);
		Mockito.when(mockNode.getNodeType()).thenReturn(Node.ATTRIBUTE_NODE);
		Mockito.when(input.item(Mockito.anyInt())).thenReturn(mockNode);
		
		List<RuleData> result = XmlProcessorService.getRules(input);

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	void testNodeListHasElementWithNoParams() {
		NodeList input = Mockito.mock(NodeList.class);
		Mockito.when(input.getLength()).thenReturn(1);
		
		Element mockElement1 = Mockito.mock(Element.class);
		Mockito.when(mockElement1.getNodeType()).thenReturn(Node.ELEMENT_NODE);
		Mockito.when(mockElement1.getAttribute("e")).thenReturn("eValue");
		Mockito.when(mockElement1.getAttribute("k")).thenReturn("a|b");
		Mockito.when(mockElement1.getAttribute("v")).thenReturn("c|d");
		
		NodeList subNL1 = Mockito.mock(NodeList.class);
		NodeList subNL2 = Mockito.mock(NodeList.class);
		NodeList subNL3 = Mockito.mock(NodeList.class);

		Mockito.when(subNL1.item(0)).thenReturn(Mockito.mock(Element.class));
		Mockito.when(subNL2.item(0)).thenReturn(Mockito.mock(Element.class));
		
		Mockito.when(mockElement1.getElementsByTagName("area")).thenReturn(subNL1);
		Mockito.when(mockElement1.getElementsByTagName("caption")).thenReturn(subNL2);
		Mockito.when(mockElement1.getElementsByTagName("line")).thenReturn(subNL3);
		Mockito.when(input.item(0)).thenReturn(mockElement1);
		
		List<RuleData> result = XmlProcessorService.getRules(input);

		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		
		RuleData ruleData = result.get(0);
		
		assertEquals("eValue", ruleData.getE());
		assertEquals(2, ruleData.getK().size());
		assertEquals(2, ruleData.getV().size());
		assertEquals("CAPTION", ruleData.getType());
		assertTrue(ruleData.getParams().isEmpty());
	}
	
	@Test
	void testNodeListHasElementWithParams() {
		NodeList input = Mockito.mock(NodeList.class);
		Mockito.when(input.getLength()).thenReturn(1);
		
		Element mockElement1 = Mockito.mock(Element.class);
		Mockito.when(mockElement1.getNodeType()).thenReturn(Node.ELEMENT_NODE);
		Mockito.when(mockElement1.getAttribute("e")).thenReturn("eValue");
		Mockito.when(mockElement1.getAttribute("k")).thenReturn("a|b");
		Mockito.when(mockElement1.getAttribute("v")).thenReturn("c|d");
		
		Mockito.when(mockElement1.getAttribute("zoom-min")).thenReturn("1.0");
		
		NodeList subNL1 = Mockito.mock(NodeList.class);
		NodeList subNL2 = Mockito.mock(NodeList.class);
		NodeList subNL3 = Mockito.mock(NodeList.class);

		Mockito.when(subNL1.item(0)).thenReturn(Mockito.mock(Element.class));
		Mockito.when(subNL2.item(0)).thenReturn(Mockito.mock(Element.class));
		
		Mockito.when(mockElement1.getElementsByTagName("area")).thenReturn(subNL1);
		Mockito.when(mockElement1.getElementsByTagName("caption")).thenReturn(subNL2);
		Mockito.when(mockElement1.getElementsByTagName("line")).thenReturn(subNL3);
		Mockito.when(input.item(0)).thenReturn(mockElement1);
		
		List<RuleData> result = XmlProcessorService.getRules(input);

		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		
		RuleData ruleData = result.get(0);
		
		assertEquals("eValue", ruleData.getE());
		assertEquals(2, ruleData.getK().size());
		assertEquals(2, ruleData.getV().size());
		assertEquals("CAPTION", ruleData.getType());
		assertFalse(ruleData.getParams().isEmpty());
		
		ruleData.getParams().entrySet().forEach(entry -> {
			assertEquals("zoom-min", entry.getKey());
			assertEquals("1.0", entry.getValue().getValue());
			assertEquals("RULE", entry.getValue().getType());
		});
	}
}