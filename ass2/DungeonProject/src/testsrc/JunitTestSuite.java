package project.testsrc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ 
		TestJunitSimpleDungeon.class, TestJunitTreasure.class, TestJunitEnemy.class, TestJunitPotions.class, TestJunitWeapons.class
		
	})

public class JunitTestSuite {

}
