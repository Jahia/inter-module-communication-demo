package org.jahia.demo.action;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.bin.Jahia;
import org.jahia.demo.service.api.MailWrapperService;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component(name = "org.jahia.demo.wrappedMailAction", service = Action.class, property = {
        Constants.SERVICE_PID + "=org.jahia.demo.wrappedMailAction",
        Constants.SERVICE_DESCRIPTION + "=Send wrapped mail",
        Constants.SERVICE_VENDOR + "=" + Jahia.VENDOR_NAME}, immediate = true)
public class SendWrappedMailAction extends Action {

    @Reference(service = MailWrapperService.class)
    protected void bindMailService(MailWrapperService mailService) {
        this.mailWrapperService = mailService;
    }
    private MailWrapperService mailWrapperService;

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        // get the message from the request
        String message = getParameter(parameters, "message");

        if(message != null) {
            // send the mail
            mailWrapperService.wrapAndSendMessage(message);
        }

        return new ActionResult(HttpServletResponse.SC_OK);
    }
}
