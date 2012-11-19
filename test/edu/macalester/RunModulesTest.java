package edu.macalester;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

public class RunModulesTest extends TestCase {
	public void runModulesTest(){
		TriangulumMain t = new TriangulumMain();

		List<String> allMods = new LinkedList<String>();
		allMods.add("location");
		allMods.add("menu");
		allMods.add("lock");
		allMods.add("alert");

		assertEquals(t.runModules(allMods),allMods);
	}
}
