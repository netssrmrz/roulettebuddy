package rs.roulettebudddy.activity;
//import android.preference.*;

public class Settings_Activity 
extends android.preference.PreferenceActivity 
implements android.preference.Preference.OnPreferenceChangeListener
{
	public static final String SETTING_KEY_MINBET="min_bet";
	public static final String SETTING_KEY_RUNTRIGGERMIN="run_trigger_min";
	public static final String SETTING_KEY_RUNTRIGGERMAX="run_trigger_max";
	public static final String SETTING_KEY_MAXBET="max_bet";
	/*public static final String SETTING_KEY_TIMESHEET_COMPANY="timesheet_company";
	public static final String SETTING_KEY_TIMESHEET_DEPT="timesheet_dept";
	public static final String SETTING_KEY_TIMESHEET_JOB="timesheet_job";
	public static final String SETTING_KEY_TIMESHEET_MAN="timesheet_man";
	public static final String SETTING_KEY_TIMESHEET_SIG="timesheet_sig";	
	public static final String SETTING_KEY_BACKUP="backup";*/

  public android.preference.PreferenceScreen ps;
	//android.preference.ListPreference round_pref;

  @Override
  public void onCreate(android.os.Bundle savedInstanceState) 
  {
		android.preference.EditTextPreference text_pref;
		android.preference.PreferenceManager pm;
		/*String[] e=
		{
			"No Rounding",
			"5 minutes",
			"10 minutes",
			"15 minutes", 
			"30 minutes",
			"45 minutes",
			"1 hour"
		};
		String[] v={"None", "5", "10", "15", "30", "45", "60"};*/

		super.onCreate(savedInstanceState);
		pm = this.getPreferenceManager();
		ps = pm.createPreferenceScreen(this);

		text_pref = new android.preference.EditTextPreference(this);
		text_pref.setKey(SETTING_KEY_MINBET);
		text_pref.setTitle("Minimum Automatic Bet");
		text_pref.setOnPreferenceChangeListener(this);
		Set_Summary(text_pref, this.Get_Min_Bet(this));
		ps.addPreference(text_pref);

    text_pref = new android.preference.EditTextPreference(this);
    text_pref.setKey(SETTING_KEY_MAXBET);
    text_pref.setTitle("Maximum Automatic Bet");
    text_pref.setOnPreferenceChangeListener(this);
    Set_Summary(text_pref, this.Get_Max_Bet(this));
		ps.addPreference(text_pref);
    
    text_pref = new android.preference.EditTextPreference(this);
    text_pref.setKey(SETTING_KEY_RUNTRIGGERMIN);
    text_pref.setTitle("Run Alert Minimum");
    text_pref.setOnPreferenceChangeListener(this);
    Set_Summary(text_pref, this.Get_Run_Trigger_Min(this));
		ps.addPreference(text_pref);
    
    text_pref = new android.preference.EditTextPreference(this);
    text_pref.setKey(SETTING_KEY_RUNTRIGGERMAX);
    text_pref.setTitle("Run Alert Maximum");
    text_pref.setOnPreferenceChangeListener(this);
    Set_Summary(text_pref, this.Get_Run_Trigger_Max(this));
		ps.addPreference(text_pref);
    
		/*this.round_pref = new android.preference.ListPreference(this);
		this.round_pref.setKey(SETTING_KEY_ROUND_TO);
		this.round_pref.setTitle("Round Activity Time");
		this.round_pref.setDefaultValue("15");
		this.round_pref.setOnPreferenceChangeListener(this);
		this.round_pref.setSummary(this.ps.getSharedPreferences().getString(SETTING_KEY_ROUND_TO, null));
		this.round_pref.setEntries(e);
		this.round_pref.setEntryValues(v);
		ps.addPreference(this.round_pref);*/
		
		this.setPreferenceScreen(ps);
  }
	
	/*@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) 
	{
		super.onCreateOptionsMenu(menu);
		
		rs.workbuddy.Menus.Create_Options_Menu(menu);
		menu.findItem(Menus.MENUITEM_EXPORT).setVisible(true);
		menu.findItem(Menus.MENUITEM_IMPORT).setVisible(true);

		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item)
	{
		boolean res=true;

		if (item.getItemId() == Menus.MENUITEM_EXPORT)
			On_Export();
		else if (item.getItemId()==Menus.MENUITEM_IMPORT)
		  On_Import();
		else
			res=rs.workbuddy.Menus.Options_Item_Selected(item, this);

		return res;
	}*/
	
	/*public void On_Export()
	{
		String filename;
		
		filename=Settings_Activity.Get_Backup_Filename(this);
		rs.workbuddy.Db.Backup(filename);
		rs.android.ui.Util.Show_Note(this, "Export to file \""+filename+"\" in Downloads directory complete.");
	}
	
	public void On_Import()
	{
		String filename;
		Db db;

		filename=Settings_Activity.Get_Backup_Filename(this);
		if (rs.workbuddy.Db.Restore(filename, this))
		{
		  rs.android.ui.Util.Show_Note(this, "Import from file \""+filename+
			"\" in Downloads directory complete.");
			
			db=new rs.workbuddy.Db(this);
			rs.android.Log.Save_Restore(db);
			db.Close();
		}
		else
			rs.android.ui.Util.Show_Note(this, "Import file \""+filename+
			"\" not found in Downloads directory.");
	}*/
	
  public void Set_Summary(android.preference.Preference p, Object val)
  {
    p.setSummary(rs.android.util.Type.To_String(val));
  }
  
	public boolean onPreferenceChange(android.preference.Preference p, Object newValue)
	{
		boolean res=true;
		
    //if (p.getKey().equals(SETTING_KEY_MINBET))
      Set_Summary(p, newValue);

		return res;
	}
  
  public static float Get_Min_Bet(android.content.Context ctx)
  {
    return rs.android.util.Type.To_Float(android.preference.PreferenceManager.
      getDefaultSharedPreferences(ctx).
      getString(SETTING_KEY_MINBET, "0.5"));
	}
  
  public static float Get_Max_Bet(android.content.Context ctx)
  {
    return rs.android.util.Type.To_Float(android.preference.PreferenceManager.
      getDefaultSharedPreferences(ctx).
      getString(SETTING_KEY_MAXBET, "0.5"));
	}
  
	public static int Get_Run_Trigger_Min(android.content.Context ctx)
	{
		return rs.android.util.Type.To_Int(android.preference.PreferenceManager.
			getDefaultSharedPreferences(ctx).
			getString(SETTING_KEY_RUNTRIGGERMIN, "4"));
	}
	
  public static int Get_Run_Trigger_Max(android.content.Context ctx)
  {
    return rs.android.util.Type.To_Int(android.preference.PreferenceManager.
      getDefaultSharedPreferences(ctx).
      getString(SETTING_KEY_RUNTRIGGERMAX, "0"));
	}
  
	/*public static Long Get_Rounding(android.content.Context ctx)
	{
		String round_str;
		Long res=null;

		round_str = android.preference.PreferenceManager.
		  getDefaultSharedPreferences(ctx).
			getString(SETTING_KEY_ROUND_TO, "");

		if (rs.android.Util.NotEmpty(round_str) && !round_str.equals("None"))
			res = rs.android.util.Type.To_Long(round_str) * 60 * 1000;

		return res;
	}*/
}
