package opentomcatdir.preferences;

import opentomcatdir.Activator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;


public class PreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault("TOMCAT_RUN_DIR", "D:\\eclipse\\.metadata\\.plugins\\org.eclipse.wst.server.core\\");
	}

}
