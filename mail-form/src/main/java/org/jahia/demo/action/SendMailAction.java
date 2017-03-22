package org.jahia.demo.action;

import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.bin.Jahia;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.mail.MailService;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component(name = "org.jahia.bundles.clustering", service = Action.class, property = {
        Constants.SERVICE_PID + "=org.jahia.demo.mailAction",
        Constants.SERVICE_DESCRIPTION + "=Send mail",
        Constants.SERVICE_VENDOR + "=" + Jahia.VENDOR_NAME}, immediate = true)
public class SendMailAction extends Action {

    @Reference(service = MailService.class)
    protected void bindMailService(MailService mailService) {
        this.mailService = mailService;
    }
    private MailService mailService;

    private static Logger logger = LoggerFactory.getLogger(SendMailAction.class);

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        // get the message from the request
        String message = getParameter(parameters, "message");

        if(message != null) {
            // send the mail
            mailService.sendMessage(message);
        }

        return new ActionResult(HttpServletResponse.SC_OK);
    }
}
