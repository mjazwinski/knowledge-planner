package pl.spiralarchitect.kplan.core.smoke;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Smoke test for running with Arquillian
 * We will use Arquillian runner, test must also specify what is to be deployed
 * so we extend ArquillianDeploymentSpec that provides required methods
 * 
 * @author majazwinski
 *
 */
@RunWith(Arquillian.class)
public class SmokeTestITCase extends ArquillianDeploymentSpec { 
	
	//Using CDI 1.1
	@Inject
	private HelloAlien alien;

	@Test
	public void test() {
		String hello = alien.hello("Mac");
		System.out.println("Response: " + hello);
	}

}
