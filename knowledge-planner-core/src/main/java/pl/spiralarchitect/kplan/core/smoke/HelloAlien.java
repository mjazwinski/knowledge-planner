package pl.spiralarchitect.kplan.core.smoke;

import javax.ejb.Stateless;

/**
 * Component for Arquillian smoke test
 * 
 * @author mjazwinski
 *
 */
@Stateless
public class HelloAlien {

	public String hello(String name) {
		return "Hello Earthling known as " + name;
	}
	
}
