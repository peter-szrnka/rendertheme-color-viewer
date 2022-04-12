package hu.szrnkapeter.rendertheme.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import hu.szrnkapeter.rendertheme.model.RuleData;
import hu.szrnkapeter.rendertheme.util.TestUtils;

/**
 * Unit test of {@link OutputBuilderService}
 */
class OutputBuilderServiceTest {

	@Test
	void testEmptyRuleDataList() throws IOException {
		List<RuleData> ruleDataList = new ArrayList<>();
		String result = OutputBuilderService.buildOutput(ruleDataList, "src/main/resources/output-template.html");
		
		assertNotNull(result);
		assertEquals(TestUtils.getFileContent("src/test/resources/empty-resultlist.html"), result);
	}
}