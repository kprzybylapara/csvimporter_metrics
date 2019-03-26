package examples.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		InputForm formBean = (InputForm) form;
		String password = formBean.getPassword();
		String username = formBean.getUsername();

		if (username.equals("Jtest") && password.equals("Parasoft")) {
			return mapping.findForward("Success");
		} else {
			return mapping.findForward("LoginError");
		}
	}
}
