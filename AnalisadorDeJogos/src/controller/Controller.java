package controller;

import javax.servlet.http.HttpSession;

public abstract class Controller {

	
		public static boolean sessionVerify(HttpSession session) {
			if(session.getAttribute("user") == null)
				return false;
			else
				return true;
				
		}
}
