package rs.roulettebudddy.activity;

public class Game_Activity 
extends android.app.Activity
implements android.view.View.OnClickListener
{
	public rs.roulettebudddy.Betting_View bet_view;
	public rs.roulettebudddy.Play_List_View plays_view;
  public rs.roulettebudddy.Wow_View fx_view;
  public rs.roulettebudddy.Stats_View stats_view; 

  public static final int ID_STATSVIEW=3000;
  public static final int ID_PLAYSVIEW=3001;
  public static final int ID_BETVIEW=3002;
  public static final int ID_MAINVIEW=3003;
  public static final int ID_STACKVIEW=3004;
  
	public static final int MENUITEM_MULTI=1;
	public static final int MENUITEM_DEL=2;
	public static final int MENUITEM_DELALL=3;
  public static final int MENUITEM_SETTINGS=4;

  @Override
  public void onCreate(android.os.Bundle state)
	{
		android.widget.LinearLayout main_view;
    android.widget.FrameLayout stack_view;

		super.onCreate(state);

    this.stats_view=new rs.roulettebudddy.Stats_View(this, 4000);
    this.stats_view.setPadding(10, 10, 0, 0);
    
    this.fx_view=new rs.roulettebudddy.Wow_View(this);
    this.fx_view.setLayoutParams(new android.widget.FrameLayout.LayoutParams(
        android.widget.FrameLayout.LayoutParams.WRAP_CONTENT, 
        android.widget.FrameLayout.LayoutParams.WRAP_CONTENT));
        
    this.plays_view=new rs.roulettebudddy.Play_List_View(this);
    this.plays_view.setId(ID_PLAYSVIEW);
    this.plays_view.stats_view=this.stats_view;
    this.plays_view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
        android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 70f));
 
    stack_view=new android.widget.FrameLayout(this);
    stack_view.setId(ID_STACKVIEW);

		this.bet_view = new rs.roulettebudddy.Betting_View(this);
    this.bet_view.setId(ID_BETVIEW);
    this.bet_view.fx_view=this.fx_view;
		this.bet_view.plays_view=this.plays_view;
		this.bet_view.stats_view=this.stats_view;
		this.bet_view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 20f));
		
		main_view = new android.widget.LinearLayout(this);
    main_view.setId(ID_MAINVIEW);
		main_view.setLayoutParams(new android.widget.ScrollView.LayoutParams(
				android.widget.ScrollView.LayoutParams.MATCH_PARENT,
				android.widget.ScrollView.LayoutParams.MATCH_PARENT));
		main_view.setOrientation(android.widget.LinearLayout.VERTICAL);
		main_view.addView(stats_view);
		main_view.addView(this.plays_view);
		main_view.addView(bet_view);
    
    stack_view.addView(main_view);
    stack_view.addView(fx_view);
    
		this.setContentView(stack_view);

    this.Reset();
	}

	@Override
	public void onClick(android.view.View view)
	{
	}

	public void Reset()
	{
	  this.bet_view.Reset();
		this.plays_view.Reset();
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) 
	{
		menu.add(1, MENUITEM_MULTI, MENUITEM_MULTI, "Multiplay");
		menu.add(1, MENUITEM_DEL, MENUITEM_DEL, "Delete Last Play");
		menu.add(1, MENUITEM_DELALL, MENUITEM_DELALL, "Reset");
    menu.add(1, MENUITEM_SETTINGS, MENUITEM_SETTINGS, "Settings");

		return true;
	}

  @Override
	public boolean onOptionsItemSelected(android.view.MenuItem item)
	{
		if (item.getItemId() == MENUITEM_MULTI)
			this.plays_view.Play_Multi();
		else if (item.getItemId() == MENUITEM_DEL)
		  this.plays_view.Delete_Play();
		else if (item.getItemId() == MENUITEM_DELALL)
			this.Reset();
    else if (item.getItemId() == MENUITEM_SETTINGS)
      this.startActivity(new android.content.Intent(this, rs.roulettebudddy.activity.Settings_Activity.class));

		return true;
	}
}
