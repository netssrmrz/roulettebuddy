package rs.roulettebudddy;
import android.widget.*;

public class Play_List_View
extends android.widget.LinearLayout
implements android.view.View.OnClickListener
{
  public static final int ID_SELPLAYBALLDLG=1000;
  public static final int ID_HIGHLOWRUN=1001;
  public static final int ID_ODDEVENRUN=1002;
	public static final int ID_REDBLACKRUN=1003;
 
	public static final int RUN_TRIGGER2=6;
	public static final int LIST_MAX=100;
	public static final int LIST_TOP=1;
  public static final int COL_BALL=0;
	public static final int COL_HIGHLOW=3;
	public static final int COL_ODDEVEN=1;
	public static final int COL_REDBLACK=5;
	
	public android.widget.TableLayout table_layout;
	public Ball_Dialog no_sel_dlg;
	public java.util.Random rnd;
  public Stats_View stats_view;
  public int max_run;
	
	public Play_List_View(android.content.Context ctx)
	{
		super(ctx);
		android.widget.ScrollView play_list;
		
		this.rnd = new java.util.Random();
		this.setOrientation(android.widget.LinearLayout.VERTICAL);
    this.max_run=0;
		
		play_list=this.Create_Play_List();
		play_list.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 90f));
		this.addView(play_list);
	}
	
	public android.widget.ScrollView Create_Play_List()
	{
		android.widget.ScrollView list=null;
		android.widget.TableRow row;

		list=new android.widget.ScrollView(this.getContext());
		
		this.table_layout = new android.widget.TableLayout(this.getContext());
		this.table_layout.setStretchAllColumns(true);
		this.setBackgroundColor(android.R.color.transparent);
		this.table_layout.setLayoutParams(new android.widget.ScrollView.LayoutParams(
				android.widget.ScrollView.LayoutParams.MATCH_PARENT, 
				android.widget.ScrollView.LayoutParams.MATCH_PARENT));
		list.addView(this.table_layout);
		
		row=this.Create_List_Header();
		this.table_layout.addView(row);
		
		return list;
	}
	
	public android.widget.TableRow Create_List_Header()
	{
	  android.widget.TableRow header=null;
		android.widget.TextView text;
		android.widget.TableRow.LayoutParams layout;
		
		header=new android.widget.TableRow(this.getContext());
		rs.android.ui.Border_Drawable.Add_Border_Bottom(header, 0xff444444, 2, 0);
		
		layout = new android.widget.TableRow.LayoutParams();
		layout.setMargins(5, 5, 5, 5);
		text=this.Create_Header_Cell(0);
		text.setLayoutParams(layout);
    text.setText("Ball No.");
		header.addView(text);
		
		layout = new android.widget.TableRow.LayoutParams();
		layout.setMargins(5, 5, 5, 5);
		text=this.Create_Header_Cell(ID_ODDEVENRUN);
		text.setLayoutParams(layout);
		header.addView(text);

    layout = new android.widget.TableRow.LayoutParams();
    layout.setMargins(5, 5, 5, 5);
    text=this.Create_Header_Cell(0);
    text.setLayoutParams(layout);
    text.setText("Bet");
    header.addView(text);
    
		layout = new android.widget.TableRow.LayoutParams();
		layout.setMargins(5, 5, 5, 5);
		text=this.Create_Header_Cell(ID_HIGHLOWRUN);
		text.setLayoutParams(layout);
		header.addView(text);

    layout = new android.widget.TableRow.LayoutParams();
    layout.setMargins(5, 5, 5, 5);
    text=this.Create_Header_Cell(0);
    text.setLayoutParams(layout);
    text.setText("Bet");
    header.addView(text);
    
		layout = new android.widget.TableRow.LayoutParams();
		layout.setMargins(5, 5, 5, 5);
		text=this.Create_Header_Cell(ID_REDBLACKRUN);
		text.setLayoutParams(layout);
		header.addView(text);

    layout = new android.widget.TableRow.LayoutParams();
    layout.setMargins(5, 5, 5, 5);
    text=this.Create_Header_Cell(0);
    text.setLayoutParams(layout);
    text.setText("Bet");
    header.addView(text);
    
		return header;
	}
	
	public android.widget.TextView Create_Header_Cell(int id)
	{
		android.widget.TextView text;
		
		text = new android.widget.TextView(this.getContext());
		text.setId(id);
		text.setGravity(android.view.Gravity.CENTER);
		text.setTextSize(20);
		text.setPadding(5, 5, 5, 5);
		this.Set_Run(text, 0);
		
		return text;
	}
	
	public void Set_Run(int id, int run)
	{
		android.widget.TextView cell;
		
		cell=(android.widget.TextView)this.table_layout.findViewById(id);
		this.Set_Run(cell, run);
	}
	
	public void Set_Run(android.widget.TextView cell, int run)
	{
		cell.setTag(run);
		cell.setText("Run "+run);
		if (run>=RUN_TRIGGER2)
			cell.setBackgroundColor(Util.COL_ZERO);
		else if (run>=rs.roulettebudddy.activity.Settings_Activity.Get_Run_Trigger_Min(this.getContext()))
			cell.setBackgroundColor(Util.COL_EVEN);
		else
			cell.setBackgroundColor(0xff000000);
	}
	
	public boolean Has_Long_Run()
	{
		boolean res=false;
		
		if (this.Count_Run(COL_ODDEVEN)>8 || this.Count_Run(COL_HIGHLOW)>8 || 
      this.Count_Run(COL_REDBLACK)>8)
			res=true;
			
		return res;
	}
	
	public boolean Has_Even_Run(int trigger)
	{
		return this.Has_Run(COL_ODDEVEN, "Even", trigger);
	}

	public boolean Has_Odd_Run(int trigger)
	{
		return this.Has_Run(COL_ODDEVEN, "Odd", trigger);
	}

	public boolean Has_High_Run(int trigger)
	{
		return this.Has_Run(COL_HIGHLOW, "High", trigger);
	}

	public boolean Has_Low_Run(int trigger)
	{
		return this.Has_Run(COL_HIGHLOW, "Low", trigger);
	}

	public boolean Has_Red_Run(int trigger)
	{
		return this.Has_Run(COL_REDBLACK, "Red", trigger);
	}

	public boolean Has_Black_Run(int trigger)
	{
		return this.Has_Run(COL_REDBLACK, "Black", trigger);
	}
	
	public boolean Has_Run(int col_idx, String match, int trigger)
	{
		boolean res=false;
		int c;

		if (this.Get_Length() >= trigger)
		{
			for (c=0; c<trigger; c++)
			{
        if (this.Cell_Changed(col_idx, c, match))
					break;
			}
			if (c==trigger)
				res = true;
		}
		return res;
	}
	
	public int Count_Run(int col_idx)
	{
		int res=0, c;
		String match;
    android.widget.TableRow row;
		
		if (this.Get_Length()>0)
		{
      row=this.Get_First_Row();
			match=this.Get_Cell_Text(col_idx, row);
			for (c=0; c<this.Get_Length(); c++)
			{
        if (this.Cell_Changed(col_idx, c, match))
					break;
			}
			res=c;
		}
		return res;
	}
	
  public boolean Cell_Changed(int col_idx, int row_idx, String match)
  {
    boolean res=false;
    String cell_text;
    android.widget.TableRow row;
    
    row=this.Get_Row(row_idx);
    cell_text=this.Get_Cell_Text(col_idx, row);
    if (rs.android.Util.NotEmpty(cell_text) && !cell_text.startsWith(match))
      res=true;
      
    return res;
  }
  
	public String Get_Cell_Text(int col_idx, android.widget.TableRow row)
	{
		android.widget.TextView cell;
		String res=null;

		cell = (android.widget.TextView)row.getChildAt(col_idx);
    if (cell!=null)
		  res = cell.getText().toString();

		return res;
	}
	
  public float Get_Circle_Float(int col_idx, android.widget.TableRow row)
  {
    Circle_View cell;
    float res=0;

    cell = (Circle_View)row.getChildAt(col_idx);
    if (cell!=null && cell.getTag()!=null)
      res = (float)cell.getTag();

    return res;
	}
  
	public void Delete_Play()
	{
		if (this.table_layout.getChildCount() > 1)
		  this.table_layout.removeViewAt(1);
	}
	
	public void Reset()
	{
		this.table_layout.removeAllViews();
		this.table_layout.addView(this.Create_List_Header());
    this.Reset_Stats();
	}
  
  public void Reset_Stats()
  {
    this.max_run=0;
    this.Update_Stats_View();
	}
	
  public int Get_Length()
  {
    return this.table_layout.getChildCount() - LIST_TOP;
  }

  public void Insert_Text_Cell(android.widget.TableRow row, int col)
  {
    Circle_View text;
    android.widget.TableRow.LayoutParams layout;

    layout = new android.widget.TableRow.LayoutParams();
    layout.setMargins(5, 5, 5, 5);
    layout.width=android.widget.TableRow.LayoutParams.MATCH_PARENT;
    layout.height=android.widget.TableRow.LayoutParams.MATCH_PARENT;

    text = new Circle_View(this.getContext());
    text.setPadding(5, 5, 5, 5);

		row.addView(text, layout);
  }
  
	public void Insert_Play_Ball(int ball, android.widget.TableRow row)
	{
		Circle_View text;
		android.widget.TableRow.LayoutParams layout;

		layout = new android.widget.TableRow.LayoutParams();
		layout.setMargins(5, 5, 5, 5);
    layout.width=android.widget.TableRow.LayoutParams.MATCH_PARENT;
    layout.height=android.widget.TableRow.LayoutParams.MATCH_PARENT;
    
		text = new Circle_View(this.getContext());
    text.Set_Text(rs.android.util.Type.To_String(ball), 0xffcccccc);
		text.setPadding(5, 5, 5, 5);
    text.setTag(ball);

		row.addView(text, layout);
	}

	public void Insert_Play_OddEven(int ball, android.widget.TableRow row)
	{
		android.widget.TextView text;
		String oddeven;
		android.widget.TableRow.LayoutParams layout;

		if (ball == 0)
			oddeven = "Zero";
		else if (Util.Is_Even(ball))
			oddeven = "Even";
		else
			oddeven = "Odd";

		layout = new android.widget.TableRow.LayoutParams();
		layout.setMargins(5, 5, 5, 5);

		text = new android.widget.TextView(this.getContext());
		text.setText(oddeven);
		text.setGravity(android.view.Gravity.CENTER);
		text.setTextSize(20);
		text.setPadding(5, 5, 5, 5);
		text.setBackgroundColor(Util.Get_OddEven_Col(ball));

		row.addView(text, layout);
	}

	public void Insert_Play_BigSmall(int ball, android.widget.TableRow row)
	{
		android.widget.TextView text;
		String highlow;
		android.widget.TableRow.LayoutParams layout;

		if (ball == 0)
			highlow = "Zero";
		else if (ball >= 1 && ball <= 18)
			highlow = "Low";
		else //if (ball >= 19 && ball <= 36)
			highlow = "High";

		layout = new android.widget.TableRow.LayoutParams();
		layout.setMargins(5, 5, 5, 5);

		text = new android.widget.TextView(this.getContext());
		text.setText(highlow);
		text.setGravity(android.view.Gravity.CENTER);
		text.setTextSize(20);
		text.setPadding(5, 5, 5, 5);
	  text.setBackgroundColor(Util.Get_BigSmall_Col(ball));

		row.addView(text, layout);
	}
  
	public void Insert_Play_RedBlack(int ball, android.widget.TableRow row)
	{
		android.widget.TextView text;
		android.widget.TableRow.LayoutParams layout;

		layout = new android.widget.TableRow.LayoutParams();
		layout.setMargins(5, 5, 5, 5);

		text = new android.widget.TextView(this.getContext());
		text.setGravity(android.view.Gravity.CENTER);
		text.setTextSize(20);
		text.setPadding(5, 5, 5, 5);
    text.setBackgroundColor(Util.Get_RedBlack_Col(ball));
		if (Util.Is_Red(ball))
			text.setText("Red");
		else if (Util.Is_Black(ball))
			text.setText("Black");
		else
			text.setText("Zero");

		row.addView(text, layout);
	}
	
  public void Play(int ball)
  {
    android.widget.TableRow row;

    this.Update_Stats_View();

    row = new android.widget.TableRow(this.getContext());
    this.Insert_Play_Ball(ball, row);
    this.Insert_Play_OddEven(ball, row);
    this.Insert_Text_Cell(row, Util.Get_OddEven_Col(ball));
    this.Insert_Play_BigSmall(ball, row);
    this.Insert_Text_Cell(row, Util.Get_BigSmall_Col(ball));
    this.Insert_Play_RedBlack(ball, row);
    this.Insert_Text_Cell(row, Util.Get_RedBlack_Col(ball));
    this.table_layout.addView(row, 1);

    if (this.table_layout.getChildCount()>LIST_MAX+1)
      this.table_layout.removeViewAt(LIST_MAX+1);

    this.Set_Run(ID_ODDEVENRUN, this.Count_Run(COL_ODDEVEN));
    this.Set_Run(ID_HIGHLOWRUN, this.Count_Run(COL_HIGHLOW));
    this.Set_Run(ID_REDBLACKRUN, this.Count_Run(COL_REDBLACK));
	}
  
	public int Play_Auto()
	{
		int ball;

		ball = this.Play_Ball();
		this.Play(ball);
		return ball;
	}
	
	public int Play_Ball()
	{
		int ball;

		ball = rnd.nextInt(37);
		return ball;
	}
	
	@Override
	public void onClick(android.view.View view)
	{
		if (view.getId()==Play_List_View.ID_SELPLAYBALLDLG)
      this.Play(this.no_sel_dlg.ball_no);
	}
	
	public void Play_Multi()
	{
		this.no_sel_dlg = new Ball_Dialog(this.getContext(), this, 
		  Play_List_View.ID_SELPLAYBALLDLG, true);
	}
	
	public void Log_Run(java.util.ArrayList<android.graphics.Point> log,
    int col_idx)
	{
		int run_length;
		
		run_length=this.Count_Run(col_idx);
    this.Inc_Run(log, run_length);
	}
  
  public void Inc_Run(java.util.ArrayList<android.graphics.Point> log, int x)
  {
    int c;
    boolean pt_found=false;
    android.graphics.Point pt;
    
    for (c=0; c<log.size() && !pt_found; c++)
    {
      pt=log.get(c);
      if (pt.x==x)
      {
        pt.y++;
        pt_found=true;
      }
    }
    
    if (!pt_found)
    {
      pt=new android.graphics.Point();
      pt.x=x;
      pt.y=1;
      log.add(pt);
    }
  }
  
  public void Update_Stats_View()
  {
    if (this.stats_view!=null)
    {
      this.Update_Max_Run(COL_REDBLACK);
      this.Update_Max_Run(COL_ODDEVEN);
      this.Update_Max_Run(COL_HIGHLOW);
      
      this.stats_view.longest_run_view.data_view.setText(
        rs.android.util.Type.To_String(this.max_run));
    }
	}
  
  public void Update_Max_Run(int col_id)
  {
    int curr_run;
    
    curr_run=this.Count_Run(col_id);
    if (curr_run>this.max_run)
      this.max_run=curr_run;
  }
  
  public boolean Has_Ball_Data()
  {
    boolean res=false;
    
    if (this.Get_Length()>0)
      res=true;
     return res;
  }
  
  public android.widget.TableRow Get_First_Row()
  {
    return this.Get_Row(0);
  }
  
  public android.widget.TableRow Get_Row(int row_idx)
  {
    android.widget.TableRow row=null;
    
    if (this.Has_Ball_Data())
      row = (android.widget.TableRow)this.table_layout.getChildAt(row_idx+LIST_TOP);
    return row;
  }
  
  public void Set_Circle_Float(float amount, int col_idx, int colour)
  {
    android.widget.TableRow row=null;
    Circle_View cell;

    row=this.Get_First_Row();
    if (row!=null)
    {
      cell = (Circle_View)row.getChildAt(col_idx);
      if (cell!=null)
      {
        cell.Set_Text(rs.android.util.Type.To_String(amount), colour);
        cell.setTag(amount);
      }
    }
  }
  
  public void Insert_Cell_Msg(String msg, int col_idx, int colour)
  {
    android.widget.TableRow row=null;
    android.widget.TextView cell;
    String text;
    
    row=this.Get_First_Row();
    if (row!=null)
    {
      cell = (android.widget.TextView)row.getChildAt(col_idx);
      if (cell!=null)
      {
        text=cell.getText().toString()+" "+msg;
        cell.setText(text);
        cell.setBackgroundColor(colour);
      }
    }
  }

  @Override
  public android.os.Parcelable onSaveInstanceState()
  {
    android.os.Bundle state;
    android.os.Parcelable base_state;
    int c, length;
    android.widget.TableRow row;

    base_state=super.onSaveInstanceState();

    state=new android.os.Bundle();
    state.putParcelable("base_state", base_state);

    length=this.Get_Length();
    state.putInt("length", length);
    if (this.Get_Length()>0)
    {
      for (c=0; c<length; c++)
      {
        row=this.Get_Row(c);
        state.putInt("ball_"+c, 
          (int)row.getChildAt(COL_BALL).getTag());
        state.putFloat("highlow_bet_"+c, 
          this.Get_Circle_Float(COL_HIGHLOW+1, row));
        state.putFloat("oddeven_bet_"+c, 
          this.Get_Circle_Float(COL_ODDEVEN+1, row));
        state.putFloat("redblack_bet_"+c, 
          this.Get_Circle_Float(COL_REDBLACK+1, row));
      }
    }

    return state;
  }

  @Override
  public void onRestoreInstanceState(android.os.Parcelable raw_state)
  {
    android.os.Parcelable base_state;
    android.os.Bundle state;
    int c, length, ball;
    float amount;

    if (raw_state instanceof android.os.Bundle)
    {
      state=(android.os.Bundle)raw_state;

      length=state.getInt("length");
      if (length>0)
      {
        for (c=length-1; c>=0; c--)
        {
          ball=state.getInt("ball_"+c);
          this.Play(ball);
          
          amount=state.getFloat("highlow_bet_"+c);
          this.Set_Bet(amount, COL_HIGHLOW);
          amount=state.getFloat("oddeven_bet_"+c);
          this.Set_Bet(amount, COL_ODDEVEN);
          amount=state.getFloat("redblack_bet_"+c);
          this.Set_Bet(amount, COL_REDBLACK);
        }
      }

      base_state=state.getParcelable("base_state");
      super.onRestoreInstanceState(base_state);
    }
  }
  
  public void Set_Bet(float amount, int col_idx)
  {
    if (amount>0)
      this.Set_Circle_Float(amount, col_idx+1, Betting_View.COL_COIN);
  }
}
