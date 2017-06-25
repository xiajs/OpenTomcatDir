package opentomcatdir.actions;

import java.io.IOException;

import opentomcatdir.Activator;
import opentomcatdir.util.Lang;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;

public class OpenTomcatDirAction implements IWorkbenchWindowActionDelegate, IPropertyChangeListener {
    
    
    
    public void run(IAction paramIAction) {
        // TODO Auto-generated method stub

        Lang.writeLog("OpenTomcatDirAction run" + Activator.getTomcatRunPath());
        openInBrowser("explorer", Activator.getTomcatRunPath());
    }

    public void selectionChanged(IAction paramIAction,
                                 ISelection paramISelection) {
        // TODO Auto-generated method stub
        Lang.writeLog("OpenTomcatDirAction selectionChanged");
    }

    public void propertyChange(PropertyChangeEvent arg0) {
        // TODO Auto-generated method stub
        Lang.writeLog("OpenTomcatDirAction propertyChange");
    }

    public void dispose() {
        // TODO Auto-generated method stub
        Lang.writeLog("OpenTomcatDirAction dispose");
    }

    public void init(IWorkbenchWindow paramIWorkbenchWindow) {
        // TODO Auto-generated method stub
        Lang.writeLog("OpenTomcatDirAction init");
    }
    
   
    
    protected void openInBrowser(String browser, String location) {
        try {
            if (Lang.isWindows()) {
                Runtime.getRuntime().exec(browser + " \"" + location + "\"");
            } else {
                Runtime.getRuntime().exec(new String[] { browser, location });
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

}
