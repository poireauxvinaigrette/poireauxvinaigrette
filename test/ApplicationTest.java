import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {

	@Test
	public void simpleCheck() {
		int a = 1 + 1;
		assertThat(a).isEqualTo(2);
	}

	// @Test
	// public void renderTemplate() {
	// Content html = views.html.index.render("Your new application is ready.");
	// assertThat(contentType(html)).isEqualTo("text/html");
	// assertThat(contentAsString(html)).contains("Your new application is ready.");
	// }


}
