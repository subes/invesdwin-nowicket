package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.step1;

import javax.annotation.concurrent.NotThreadSafe;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import de.invesdwin.norva.beanpath.annotation.BeanPathEndPoint;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.domain.User;
import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.CreateUserWizardStepControl;
import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.ICreateUserWizardStep;
import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.step2.CreateUserWizardStep2;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Objects;

@GeneratedMarkup
@NotThreadSafe
public class CreateUserWizardStep1 extends AValueObject implements ICreateUserWizardStep {

    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String emailAddress;
    @NotBlank
    @Email
    private String emailAddressConfirm;

    private final CreateUserWizardStepControl control;

    public CreateUserWizardStep1(final boolean isModal) {
        control = new CreateUserWizardStepControl(null, isModal);
        final CreateUserWizardStep2 next = new CreateUserWizardStep2(this);
        control.setNext(next);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddressConfirm() {
        return emailAddressConfirm;
    }

    public void setEmailAddressConfirm(final String emailAddressConfirm) {
        this.emailAddressConfirm = emailAddressConfirm;
    }

    public String validateEmailAddressConfirm(final String newValue) {
        if (!Objects.equals(emailAddress, newValue)) {
            return "does not match the first email address";
        }
        return null;
    }

    @Override
    @BeanPathEndPoint
    public CreateUserWizardStepControl getControl() {
        return control;
    }

    @Hidden(skip = true)
    @Override
    public void fillUserDetails(final User user) {
        user.setUsername(username);
        user.setEmailAddress(emailAddress);
    }

    public String title() {
        return getHeader();
    }

    public boolean hideHeader() {
        return control.isModal();
    }

    public String getHeader() {
        return "User Name";
    }

    public String getSubtitle() {
        return "Create a new user by providing a user name and an email address.";
    }

}
