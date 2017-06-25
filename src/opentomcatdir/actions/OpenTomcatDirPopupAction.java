package opentomcatdir.actions;

import opentomcatdir.Activator;
import opentomcatdir.util.Lang;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

public class OpenTomcatDirPopupAction extends OpenTomcatDirAction {
    
    public void run(IAction arg0) {
        // TODO Auto-generated method stub
        //Lang.writeLog("System.getenv()==>"+System.getenv("catalina.base"));
        //Lang.writeLog("System.getProperty()==>"+System.getProperty("catalina.base"));
        Lang.writeLog("OpenTomcatDirPopupAction run");
        
        openInBrowser("explorer", Activator.getTomcatRunPath());
    }

    public void selectionChanged(IAction arg0, ISelection arg1) {
        // TODO Auto-generated method stub
        Lang.writeLog("OpenTomcatDirPopupAction selectionChanged");
    }

    public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
        // TODO Auto-generated method stub
        Lang.writeLog("OpenTomcatDirPopupAction setActivePart");
    }

    public void dispose() {
        // TODO Auto-generated method stub
        Lang.writeLog("OpenTomcatDirPopupAction dispose");
    }

    public void init(IWorkbenchWindow arg0) {
        // TODO Auto-generated method stub
        Lang.writeLog("OpenTomcatDirPopupAction init");
    }
    
}
