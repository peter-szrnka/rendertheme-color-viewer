package hu.szrnkapeter.rendertheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testMissingParameters() {
		String[] params = new String[0];

		// act & assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Main.main(params));
		assertEquals("Please provide a file path!", exception.getMessage());
	}

	@Test
	void testOnly1Parameter() throws Exception {
		String[] params = new String[] { "src/test/resources/osmarender-test.xml" };

		// act
		Main.main(params);

		// assert
		assertTrue(Files.exists(Paths.get("out.html")));

		// Cleanup
		new File("out.html").deleteOnExit();
	}

	//

	@Test
	void testWith2Parameters() throws Exception {
		String[] params = new String[] { "src/test/resources/osmarender-test.xml", "test-template.html" };

		// act
		Main.main(params);

		// assert
		assertTrue(Files.exists(Paths.get("out.html")));

		// Cleanup
		new File("out.html").deleteOnExit();
	}
}