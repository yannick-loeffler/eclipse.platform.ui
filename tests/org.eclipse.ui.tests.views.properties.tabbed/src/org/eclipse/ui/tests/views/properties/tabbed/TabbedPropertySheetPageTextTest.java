/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.tests.views.properties.tabbed;

import junit.framework.TestCase;

import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList;
import org.eclipse.ui.tests.views.properties.tabbed.text.TextTestsView;
import org.eclipse.ui.tests.views.properties.tabbed.views.TestsPerspective;

/**
 * Tests for the text tests view.
 * 
 * @author Anthony Hunter
 * @since 3.4
 */
public class TabbedPropertySheetPageTextTest extends TestCase {

	private TextTestsView textTestsView;

	protected void setUp() throws Exception {
		super.setUp();

        /**
         * Close the existing perspectives.
         */
        IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow();
        assertNotNull(workbenchWindow);
        IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
        assertNotNull(workbenchPage);
        workbenchPage.closeAllPerspectives(false, false);

        /**
         * Open the tests perspective.
         */
        PlatformUI.getWorkbench().showPerspective(
            TestsPerspective.TESTS_PERSPECTIVE_ID, workbenchWindow);

		/**
		 * Open the dynamic tests view.
		 */
		IViewPart view = workbenchPage
				.showView(TextTestsView.TEXT_TESTS_VIEW_ID);
		assertNotNull(view);
		assertTrue(view instanceof TextTestsView);
		textTestsView = (TextTestsView) view;
	}

	protected void tearDown() throws Exception {
		super.tearDown();

		/**
		 * Bug 175070: Make sure the views have finished painting.
		 */
		while (Display.getCurrent().readAndDispatch()) {
			//
		}

	}

	/**
	 * Get the list of tabs from the tabbed properties view.
	 * 
	 * @return the tab list.
	 */
	private TabbedPropertyList getTabbedPropertyList() {
		Control control = textTestsView.getTabbedPropertySheetPage()
				.getControl();
		assertTrue(control instanceof TabbedPropertyComposite);
		TabbedPropertyComposite tabbedPropertyComposite = (TabbedPropertyComposite) control;
		return tabbedPropertyComposite.getList();
	}
	
    /**
     * When text is selected, there is one tab for each selected word.
     */
    public void test_tabForSelectedTextDisplay() {
        /**
         * select node 0 which is an Information
         */
        IDocument document = textTestsView.getViewer().getDocument();
        document.set("This is a test");
        textTestsView.getViewer().setSelectedRange(0, 14);
        
        TabbedPropertyList tabbedPropertyList = getTabbedPropertyList();
        /**
         * First tab is "This"
         */
        assertEquals(tabbedPropertyList.getElementAt(0).toString(), "This");//$NON-NLS-1$
        /**
         * Second tab is "is"
         */
        assertEquals(tabbedPropertyList.getElementAt(1).toString(),
            "is");//$NON-NLS-1$
        /**
         * Third tab is "a"
         */
        assertEquals(tabbedPropertyList.getElementAt(2).toString(), "a");//$NON-NLS-1$
        /**
         * Third tab is "test"
         */
        assertEquals(tabbedPropertyList.getElementAt(3).toString(), "test");//$NON-NLS-1$
        /**
         * No fifth tab
         */
        assertNull(tabbedPropertyList.getElementAt(4));
    }

}