package de.invesdwin.nowicket.examples.guide.pages.mvp;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.PackageResourceReference;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;

@MountPath("bsgcoach")
@NotThreadSafe
public class RedirectToMvpBsgcoachPage extends AExampleWebPage {

    public static final PackageResourceReference ICON = new PackageResourceReference(RedirectToMvpBsgcoachPage.class,
            "bsgcoach.ico");

    public RedirectToMvpBsgcoachPage() {
        super(null);
    }

}
