package com.eva.web.error;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.http.WebResponse;

import com.eva.web.AEvaWebPage;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.error.PageExpiredPanel;

@NotThreadSafe
public class PageExpiredPage extends AEvaWebPage {

    public static final String MOUNT_PATH = "/pageexpired";

    public PageExpiredPage() {
        super(null);
        add(new PageExpiredPanel("panel"));
        setTitleModel(new I18nModel(this, "page.expired.title"));
    }

    @Override
    protected void setHeaders(final WebResponse response) {
        response.setStatus(HttpServletResponse.SC_GONE);
    }

    @Override
    public boolean isErrorPage() {
        return true;
    }

    @Override
    public boolean isVersioned() {
        return false;
    }

}