package hu.szrnkapeter.rendertheme;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

public class MainTest {

	@Test
	public void testMissingParameters() {
		String[] params = new String[0];

		assertThrows(RuntimeException.class, () -> Main.main(params));
	}

	@Test
	public void testOnly1Parameter() throws Exception {
		String[] params = new String[] { "src/test/resources/osmarender-test.xml" };
		
		Main.main(params);

		// Cleanup
		new File("out.html").deleteOnExit();
	}

	//

	@Test
	public void testWith2Parameters() throws Exception {
		String[] params = new String[] { "src/test/resources/osmarender-test.xml", "src/test/resources/test-template.html" };

		Main.main(params);

		// Cleanup
		new File("out.html").deleteOnExit();
	}
}