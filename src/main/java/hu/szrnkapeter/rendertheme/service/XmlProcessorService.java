package hu.szrnkapeter.rendertheme.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import hu.szrnkapeter.rendertheme.model.Param;
import hu.szrnkapeter.rendertheme.model.RuleData;

public class XmlProcessorService {
	
	private static final String AREA = "AREA";
	private static final String CAPTION = "CAPTION";
	private static final String LINE = "LINE";
	private static List<RuleData> ruleDataList = new ArrayList<>();
	
	public static List<RuleData> getRules(NodeList nList) {
		getRule(nList, 0);
		return ruleDataList;
	}

	private static void getRule(final NodeList nList, int level) {
		if (nList == null || nList.getLength() == 0) {
			return;
		}

		for (int temp = 0; temp < nList.getLength(); temp++) {
			final Node nNode = nList.item(temp);
			boolean hasData = false;
			final RuleData rule = new RuleData();

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				final Element eElement = (Element) nNode;

				rule.setE(eElement.getAttribute("e"));
				rule.setK(Arrays.asList(eElement.getAttribute("k").split("\\|")));
				rule.setV(Arrays.asList(eElement.getAttribute("v").split("\\|")));

				getTextAttribute("RULE", hasData, rule, eElement, "zoom-min");

				final NodeList newNl = eElement.getElementsByTagName("rule");
				if (newNl != null && newNl.getLength() > 0) {
					getRule(newNl, level + 1/*, sb*/);
				} else {
					// Areas
					final Element area = (Element) eElement.getElementsByTagName("area").item(0);
					if (area != null) {
						final boolean hasAnyParameter = false;
						getColorAttribute(AREA, hasAnyParameter, rule, area, "fill");
						getColorAttribute(AREA, hasAnyParameter, rule, area, "stroke");
						rule.setType(AREA);
						hasData = true;
					}

					// Captions
					final Element caption = (Element) eElement.getElementsByTagName("caption").item(0);
					if (caption != null) {
						final boolean hasAnyParameter = false;
						getColorAttribute(CAPTION, hasAnyParameter, rule, caption, "fill");
						getColorAttribute(CAPTION, hasAnyParameter, rule, caption, "stroke");
						getTextAttribute(CAPTION, hasAnyParameter, rule, caption, "font-size");
						getTextAttribute(CAPTION, hasAnyParameter, rule, caption, "symbol-id");
						getTextAttribute(CAPTION, hasAnyParameter, rule, caption, "k");
						rule.setType(CAPTION);

						hasData = true;
					}

					// Lines
					final Element line = (Element) eElement.getElementsByTagName("line").item(0);
					if (line != null) {
						final boolean hasAnyParameter = false;
						getColorAttribute(LINE, hasAnyParameter, rule, line, "fill");
						getColorAttribute(LINE, hasAnyParameter, rule, line, "stroke");
						rule.setType(LINE);

						hasData = true;
					}
				}
			}

			if (hasData) {
				ruleDataList.add(rule);
			}
		}
	}
	
	private static void getTextAttribute(String type, boolean hasData, final RuleData rule, /*StringBuilder sb, */Element element, String elementName) {
		if (element.getAttribute(elementName) == null || element.getAttribute(elementName).isEmpty()) {
			return;
		}

		hasData = true;
		rule.getParams().put(elementName, new Param(type, element.getAttribute(elementName)));
	}
	
	private static void getColorAttribute(String type, boolean hasAnyParameter, final RuleData rule, Element element, String elementName) {
		if (element.getAttribute(elementName) == null || element.getAttribute(elementName).isEmpty()) {
			return;
		}

		hasAnyParameter = true;
		rule.getParams().put(elementName, new Param(type, element.getAttribute(elementName)));
	}
}