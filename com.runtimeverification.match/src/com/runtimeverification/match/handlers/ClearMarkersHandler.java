/*******************************************************************************
 * Copyright (c) 2011, 2016 Red Hat, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Elliott Baron <ebaron@redhat.com> - initial API and implementation
 *    Traian Serbanuta <traian.serbanuta@runtimeverification.com> 
 *        - customization for RV Match
 *******************************************************************************/
package com.runtimeverification.match.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;

import com.runtimeverification.match.RVMatchPlugin;

public class ClearMarkersHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        try {
            root.deleteMarkers(RVMatchPlugin.EXECUTION_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
            root.deleteMarkers(RVMatchPlugin.COMPILATION_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
        } catch (CoreException e) {
            // do nothing for now
        }
        // Clear Valgrind view
        Display.getDefault().syncExec(() -> RVMatchPlugin.getDefault().resetView());
        return null;
    }

}
