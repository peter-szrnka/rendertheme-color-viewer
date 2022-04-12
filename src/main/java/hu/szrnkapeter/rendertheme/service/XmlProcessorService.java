package hu.szrnkapeter.rendertheme.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import hu.szrnkapeter.rendertheme.model.Param;
import hu.szrnkapeter.rendertheme.model.RuleData;

public final class XmlProcessorService {
	
	private static final String STROKE = "stroke";
	private static final String AREA = "AREA";
	private static final String CAPTION = "CAPTION";
	private static final String LINE = "LINE";
	static List<RuleData> ruleDataList = new ArrayList<>();
	
	private XmlProcessorService() {}
	
	public static List<RuleData> getRules(NodeList nList) {
		getRule(nList, 0);
		return ruleDataList;
	}

	private static void getRule(final NodeList nList, int level) {
		if (nList == null || nList.getLength() == 0) {
			return;
		}

		for (int temp = 0; temp < nList.getLength(); temp++) {
			iterateNode(nList.item(temp), level);
		}
	}
	
	private static void iterateNode(Node nNode, int level) {
		AtomicBoolean hasData = new AtomicBoolean(false);
		final RuleData rule = new RuleData();

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			final Element eElement = (Element) nNode;

			rule.setE(eElement.getAttribute("e"));
			rule.setK(Arrays.asList(eElement.getAttribute("k").split("\\|")));
			rule.setV(Arrays.asList(eElement.getAttribute("v").split("\\|")));

			getAttribute("RULE", rule, eElement, "zoom-min").ifPresent(hasData::set);

			final NodeList newNl = eElement.getElementsByTagName("rule");
			if (newNl != null && newNl.getLength() > 0) {
				getRule(newNl, level + 1/*, sb*/);
			} else {
				// Areas
				final Element area = (Element) eElement.getElementsByTagName("area").item(0);
				if (area != null) {
					getAttribute(AREA, rule, area, "fill");
					getAttribute(AREA, rule, area, STROKE);
					rule.setType(AREA);

					hasData.set(true);
				}

				// Captions
				final Element caption = (Element) eElement.getElementsByTagName("caption").item(0);
				if (caption != null) {
					getAttribute(CAPTION, rule, caption, "fill");
					getAttribute(CAPTION, rule, caption, STROKE);
					getAttribute(CAPTION, rule, caption, "font-size");
					getAttribute(CAPTION, rule, caption, "symbol-id");
					getAttribute(CAPTION, rule, caption, "k");
					rule.setType(CAPTION);

					hasData.set(true);
				}

				// Lines
				final Element line = (Element) eElement.getElementsByTagName("line").item(0);
				if (line != null) {
					getAttribute(LINE, rule, line, "fill");
					getAttribute(LINE, rule, line, STROKE);
					rule.setType(LINE);

					hasData.set(true);
				}
			}
		}

		if (hasData.get()) {
			ruleDataList.add(rule);
		}
	}

	private static Optional<Boolean> getAttribute(String type, final RuleData rule, Element element, String elementName) {
		if (element.getAttribute(elementName) == null || element.getAttribute(elementName).isEmpty()) {
			return Optional.empty();
		}

		rule.getParams().put(elementName, new Param(type, element.getAttribute(elementName)));
		
		return Optional.of(true);
	}
}