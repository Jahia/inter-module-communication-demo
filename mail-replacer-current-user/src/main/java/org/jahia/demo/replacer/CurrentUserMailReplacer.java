package org.jahia.demo.replacer;

import org.apache.commons.lang.StringUtils;
import org.jahia.bin.Jahia;
import org.jahia.demo.service.api.MailWrapperReplacer;
import org.jahia.services.content.JCRSessionFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

@Component(name = "org.jahia.demo.currentUserMailReplacer", service = MailWrapperReplacer.class, property = {
        Constants.SERVICE_PID + "=org.jahia.demo.currentUserMailReplacer",
        Constants.SERVICE_DESCRIPTION + "=replace current user in message",
        Constants.SERVICE_VENDOR + "=" + Jahia.VENDOR_NAME}, immediate = true)
public class CurrentUserMailReplacer implements MailWrapperReplacer {

    @Override
    public String replaceInMessage(String message) {
        return StringUtils.replace(message, "{{currentUser}}", JCRSessionFactory.getInstance().getCurrentUser().getUsername());
    }
}
