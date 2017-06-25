package opentomcatdir.preferences;

import java.io.File;
import opentomcatdir.Activator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class MainPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    public void init(IWorkbench arg0) {
        setDescription("set Tomcat run directory path");
    }
    
    @Override
    protected IPreferenceStore doGetPreferenceStore() {
        return Activator.getDefault().getPreferenceStore();
    }

    @Override
    protected Control createContents(Composite paramComposite) {
        Composite composite = createComposite(paramComposite);
        createRunPathGroup(composite);
        return composite;
    }

    protected Composite createComposite(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
                | GridData.HORIZONTAL_ALIGN_FILL));
        return composite;
    }

    private Group createGroup(Composite composite, String title) {
        Group groupComposite = new Group(composite, SWT.LEFT);
        GridLayout layout = new GridLayout();
        groupComposite.setLayout(layout);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL);
        groupComposite.setLayoutData(data);
        groupComposite.setText(title);
        return groupComposite;
    }
    
    private Text textRunPath = null;
    private Button btn1 = null;
    private Label lable1 = null;
    
    protected void createRunPathGroup(Composite composite) {
        Group groupComposite = createGroup(composite, "Run Path");
        
        Composite c = new Composite(groupComposite, SWT.NONE);
        c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        GridLayout layout = new GridLayout(3, false);
        layout.marginWidth = 0;
        c.setLayout(layout);
        
        lable1 = new Label(c, SWT.NONE);
        lable1.setText("Path:");
        textRunPath = new Text(c, SWT.BORDER);
        
        String strRunPath =  Activator.getTomcatRunPath();
        if( null != strRunPath && strRunPath.trim().length()>0 ){
            textRunPath.setText(strRunPath);
        }
        textRunPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        textRunPath.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                if (textRunPath.isEnabled()) {
                    String path = textRunPath.getText();
                    if (path == null || path.equals("")) {
                        setValid(false);
                        setErrorMessage("Run path is null!");
                        return;
                    }
                    File f = new File(path);
                    if (!f.exists() || !f.isDirectory() ) {
                        setValid(false);
                        setErrorMessage("Specified Run Path not exist or is not a directory.");
                        return;
                    }
                    setErrorMessage(null);
                    setValid(true);
                }
            }
        });
        
        btn1 = new Button(c, SWT.PUSH);
        btn1.setText("Browse ...");
        btn1.setFont(composite.getFont());
        btn1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent evt) {
                String newValue = browsePressed();
                if (newValue != null) {
                    textRunPath.setText(newValue);
                }
            }
        });
        btn1.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent event) {
                btn1 = null;
            }
        });

    }
    
    private String browsePressed() {
        File f = new File(textRunPath.getText());
        if (!f.exists()) {
            f = null;
        }
        File filePath = getFilePath(f);
        if (filePath == null) {
            return null;
        }

        return filePath.getAbsolutePath();
    }
    
    private File getFilePath(File startingDirectory) {
        DirectoryDialog dirDialog = new DirectoryDialog(getShell());
        if (startingDirectory != null) {
            dirDialog.setFilterPath(startingDirectory.getPath());
        }
        String filePath = dirDialog.open();
        if (filePath != null) {
            filePath = filePath.trim();
            if (filePath.length() > 0) {
                return new File(filePath);
            }
        }

        return null;
    }
    
    public boolean performOk() {
        if( null != textRunPath ){
            IPreferenceStore store = getPreferenceStore();
            store.setValue("TOMCAT_RUN_DIR", textRunPath.getText());
        }
        return super.performOk();
    }
    
}
