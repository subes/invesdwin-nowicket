package de.invesdwin.nowicket.examples.guide.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.examples.guide.pages.guestbook.GuestbookExamplePage;
import de.invesdwin.nowicket.examples.guide.pages.secure.SpringSecurePage;
import de.invesdwin.nowicket.examples.guide.pages.secure.WicketSecurePage;

@NotThreadSafe
public abstract class AExampleWebPage extends AWebPage {

    public AExampleWebPage(final IModel<?> model) {
        super(model);
    }

    @Override
    protected Navbar newNavbar(final String id) {

        final Navbar navbar = super.newNavbar(id);
        navbar.setBrandName(Model.of("NoWicket Examples"));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarButton<Void>(GuestbookExamplePage.class, new ResourceModel("menu.guestbook"))
                        .setIconType(GlyphIconType.book)));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                new NavbarDropDownButton(new ResourceModel("menu.secure")) {

                    @Override
                    public boolean isActive(final Component item) {
                        return false;
                    }

                    @Override
                    protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                        final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();

                        subMenu.add(new MenuBookmarkablePageLink<Void>(WicketSecurePage.class,
                                new ResourceModel("menu.wicketsecure")));
                        subMenu.add(new MenuBookmarkablePageLink<Void>(SpringSecurePage.class,
                                new ResourceModel("menu.springsecure")));

                        if (Roles.isAuthenticated()) {
                            subMenu.add(new MenuBookmarkablePageLink<Void>(ABaseWebApplication.get().getSignOutPage(),
                                    new ResourceModel("menu.sign.out")));
                        } else {
                            subMenu.add(new MenuBookmarkablePageLink<Void>(ABaseWebApplication.get().getSignInPage(),
                                    new ResourceModel("menu.sign.in")));
                        }

                        return subMenu;
                    }

                }));

        return navbar;
    }

}