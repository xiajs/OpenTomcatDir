package opentomcatdir;


import opentomcatdir.util.Lang;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "OpenTomcatDir"; //$NON-NLS-1$

    // The plugin version
    public static final String VERSION = "1.0.0";

    // The shared instance
    private static Activator plugin;


    public Activator() {
    }


    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }


    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }


    public static Activator getDefault() {
        return plugin;
    }


    public static String getTomcatRunPath(){
        String runPath = null;
        IPreferenceStore store = getDefault().getPreferenceStore();
        runPath = store.getString("TOMCAT_RUN_DIR");
        
        if( Lang.isBlank(runPath) ){
            runPath = "D:\\eclipse\\.metadata\\.plugins\\org.eclipse.wst.server.core\\";
        }
        return runPath;
    }
}
