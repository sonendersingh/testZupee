package screens;

import pageFactory.Elements;
import utils.Helper;

/**
 * @author dhiraj.aggarwal
 *
 */
public abstract class BaseActivity implements Elements {

	Helper helper = new Helper();
	
	public abstract boolean isValid();


}
