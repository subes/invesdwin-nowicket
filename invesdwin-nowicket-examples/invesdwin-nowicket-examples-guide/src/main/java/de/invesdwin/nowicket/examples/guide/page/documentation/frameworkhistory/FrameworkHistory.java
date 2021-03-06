package de.invesdwin.nowicket.examples.guide.page.documentation.frameworkhistory;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.installation.Installation;
import de.invesdwin.nowicket.examples.guide.page.documentation.wicketintegration.WicketIntegration;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class FrameworkHistory extends AValueObject {

    public WicketIntegration goBackToPreviousChapter() {
        return new WicketIntegration();
    }

    public Installation readNextChapter() {
        return new Installation();
    }

    public String getHistoryImg() {
        return "History.png";
    }

}
