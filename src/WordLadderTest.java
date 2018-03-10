import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordLadderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLadder() {
		Set<String> dictionary=new HashSet<String>();
		Stack<String> ladder = new Stack<String>();
		dictionary.add("data");
		dictionary.add("date");
		dictionary.add("cate");
		dictionary.add("cade");
		dictionary.add("code");
		ladder.push("code");
		ladder.push("cade");
		ladder.push("cate");
		ladder.push("date");
		WordLadder.ladder("code", "data", dictionary);
		assertEquals(ladder,WordLadder.getLadder());
	}

}
