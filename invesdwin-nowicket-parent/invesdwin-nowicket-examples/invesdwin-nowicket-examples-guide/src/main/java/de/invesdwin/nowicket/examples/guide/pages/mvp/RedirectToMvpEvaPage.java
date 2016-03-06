package de.invesdwin.nowicket.examples.guide.pages.mvp;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;

@NotThreadSafe
public class RedirectToMvpEvaPage extends AExampleWebPage {

    public static final PackageResourceReference ICON = new PackageResourceReference(RedirectToMvpBsgcoachPage.class,
            "eva.ico");

    public RedirectToMvpEvaPage() {
        super(null);
        throw new RedirectToUrlException("http://invesdwin.de/nowicket-examples-mvp-eva/",
                HttpServletResponse.SC_MOVED_PERMANENTLY);
    }

}
