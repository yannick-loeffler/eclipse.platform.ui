/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.ui.tests.statushandlers;

import org.eclipse.ui.statushandlers.AbstractStatusHandler;
import org.eclipse.ui.statushandlers.StatusAdapter;

/**
 * The handler should be used during tests. It allows for checking the status
 * and style used during last handling.
 * 
 * @since 3.3
 */
public class TestStatusHandler extends AbstractStatusHandler {

	private static StatusAdapter lastHandledStatusAdapter;

	private static int lastHandledStyle;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.statushandlers.AbstractStatusHandler#handle(org.eclipse.ui.statushandlers.StatusAdapter,
	 *      int)
	 */
	public void handle(StatusAdapter statusAdapter, int style) {
		lastHandledStatusAdapter = statusAdapter;
		lastHandledStyle = style;
	}

	/**
	 * Returns the status used during last handling
	 * 
	 * @return the status
	 */
	public static StatusAdapter getLastHandledStatusAdapter() {
		return lastHandledStatusAdapter;
	}

	/**
	 * Returns the style used during last handling
	 * 
	 * @return the style
	 */
	public static int getLastHandledStyle() {
		return lastHandledStyle;
	}
}