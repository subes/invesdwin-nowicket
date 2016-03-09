package de.invesdwin.nowicket.examples.guide.pages.documentation.concept;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.home.Home;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Concept extends AValueObject {

    public Home goBackToIntroduction() {
        return new Home();
    }

    public Object readNextChapter() {
        return null;
    }

}
