package rs.roulettebudddy;

public class Betting_View
extends android.widget.LinearLayout
implements android.view.View.OnClickListener
{
	public static final int ID_BETZEROBUTT=2000;
	public static final int ID_POTBUTT=2001;
	public static final int ID_BETEVENBUTT=2002;
	public static final int ID_BETODDBUTT=2003;
	public static final int ID_BETHIGHBUTT=2004;
	public static final int ID_BETLOWBUTT=2005;
	public static final int ID_BETREDBUTT=2006;
	public static final int ID_BETBLACKBUTT=2007;
	public static final int ID_SELPLAYBALLDLG=2008;
  public static final int ID_BETROWVIEW=2009;
  
  public static final int COL_COIN=0xff999900;
	
	public android.widget.Button play_button;
	public android.widget.Button play_auto_button;
	public android.widget.Button play_bet_auto_button;
	public android.widget.Button bet_auto_button;
	public Play_List_View plays_view;
	public Ball_Dialog no_sel_dlg;
	public Stats_View stats_view;
	public Cash_Button cash_button;
  public Wow_View fx_view;
	
	public boolean can_bet_evenodd;
	public boolean can_bet_highlow;
	public boolean can_bet_redblack;
	
	public int stat_play_count;
	public float stat_lrg_sng_bet;
	public float stat_cash_inv;
	 
	public Betting_View(android.content.Context ctx)
	{
		super(ctx);
		android.widget.LinearLayout butt_row;
		
		this.can_bet_evenodd = true;
		this.can_bet_highlow = true;
		this.can_bet_redblack = true;
		
		butt_row=this.Create_Bet_Buttons();
    butt_row.setId(ID_BETROWVIEW);
		butt_row.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 50f));
		this.addView(butt_row);
				
		butt_row=this.Create_Play_Buttons();
		butt_row.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
			 android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 50f));
		this.addView(butt_row);
		
		this.setOrientation(android.widget.LinearLayout.VERTICAL);
	}

	public android.widget.LinearLayout Create_Play_Buttons()
	{
		android.widget.LinearLayout row=null;

		row=new android.widget.LinearLayout(this.getContext());
		row.setOrientation(android.widget.LinearLayout.HORIZONTAL);
		
		cash_button=new Cash_Button(this.getContext(), ID_POTBUTT, this);
		cash_button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		row.addView(cash_button);
		
		this.play_button = new android.widget.Button(this.getContext());
		this.play_button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		this.play_button.setText("Select");
		this.play_button.setOnClickListener(this);
		row.addView(this.play_button);
		
		this.play_auto_button = new android.widget.Button(this.getContext());
		this.play_auto_button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		this.play_auto_button.setText("Play");
		this.play_auto_button.setOnClickListener(this);
		row.addView(this.play_auto_button);
		
		this.bet_auto_button = new android.widget.Button(this.getContext());
		this.bet_auto_button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		this.bet_auto_button.setText("Bet");
		this.bet_auto_button.setOnClickListener(this);
		row.addView(this.bet_auto_button);

		this.play_bet_auto_button = new android.widget.Button(this.getContext());
		this.play_bet_auto_button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		this.play_bet_auto_button.setText("Play & Bet");
		this.play_bet_auto_button.setOnClickListener(this);
		row.addView(this.play_bet_auto_button);

		return row;
	}
	
	public android.widget.LinearLayout Create_Bet_Buttons()
	{
		android.widget.LinearLayout row=null;
		Bet_Button button;
		
		row=new android.widget.LinearLayout(this.getContext());
		row.setOrientation(android.widget.LinearLayout.HORIZONTAL);

		button=new Zero_Button(this.getContext(), ID_BETZEROBUTT, this);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		row.addView(button);
		
		button=new Bet_Button(this.getContext(), ID_BETEVENBUTT, "Bet Even", this, this);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		row.addView(button);

		button=new Bet_Button(this.getContext(), ID_BETODDBUTT, "Bet Odd", this, this);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		row.addView(button);

		button=new Bet_Button(this.getContext(), ID_BETHIGHBUTT, "Bet High", this, this);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		row.addView(button);

		button=new Bet_Button(this.getContext(), ID_BETLOWBUTT, "Bet Low", this, this);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		row.addView(button);

		button=new Bet_Button(this.getContext(), ID_BETREDBUTT, "Bet Red", this, this);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		row.addView(button);

		button=new Bet_Button(this.getContext(), ID_BETBLACKBUTT, "Bet Black", this, this);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 50f));
		row.addView(button);
		
		return row;
	}
	
	public boolean Is_Bet_Button(int id)
	{
		return id==ID_BETZEROBUTT ||
		  id==ID_BETEVENBUTT ||
		  id==ID_BETODDBUTT ||
		  id==ID_BETHIGHBUTT ||
		  id==ID_BETLOWBUTT ||
		  id==ID_BETREDBUTT ||
		  id==ID_BETBLACKBUTT;
	}
	
	public Bet_Button Get_Bet_Button(int id)
	{
		return (Bet_Button)this.findViewById(id);
	}
	
	public void Reset()
	{
		this.can_bet_evenodd = true;
		this.can_bet_highlow = true;
		this.can_bet_redblack = true;
		this.Reset_Bets();
		this.cash_button.Reset();
	  this.Reset_Stats();
	}
	
	public void Reset_Bets()
	{
		this.Get_Bet_Button(ID_BETZEROBUTT).Bet(0);
		this.Get_Bet_Button(ID_BETEVENBUTT).Bet(0);
		this.Get_Bet_Button(ID_BETODDBUTT).Bet(0);
		this.Get_Bet_Button(ID_BETHIGHBUTT).Bet(0);
		this.Get_Bet_Button(ID_BETLOWBUTT).Bet(0);
		this.Get_Bet_Button(ID_BETREDBUTT).Bet(0);
		this.Get_Bet_Button(ID_BETBLACKBUTT).Bet(0);
	}
	
	public void Play(int ball)
	{
		float pot=0;
		boolean suggest;
    int trigger;
		
    trigger=rs.roulettebudddy.activity.Settings_Activity.Get_Run_Trigger_Min(this.getContext());
		this.Update_Stats();
		
		suggest=this.plays_view.Has_Long_Run();
		pot+=this.Get_Bet_Button(ID_BETZEROBUTT).Process_Play(ball==0, suggest);
		
		suggest=this.can_bet_evenodd && this.plays_view.Has_Odd_Run(trigger);
		pot+=this.Get_Bet_Button(ID_BETEVENBUTT).Process_Play(Util.Is_Even(ball), suggest);
		
		suggest=this.can_bet_evenodd && this.plays_view.Has_Even_Run(trigger);
		pot+=this.Get_Bet_Button(ID_BETODDBUTT).Process_Play(Util.Is_Odd(ball), suggest);
		
		suggest=this.can_bet_highlow && this.plays_view.Has_Low_Run(trigger);
		pot+=this.Get_Bet_Button(ID_BETHIGHBUTT).Process_Play(Util.Is_High(ball), suggest);
		
		suggest=this.can_bet_highlow && this.plays_view.Has_High_Run(trigger);
		pot+=this.Get_Bet_Button(ID_BETLOWBUTT).Process_Play(Util.Is_Low(ball), suggest);
		
		suggest=this.can_bet_redblack && this.plays_view.Has_Black_Run(trigger);
		pot+=this.Get_Bet_Button(ID_BETREDBUTT).Process_Play(Util.Is_Red(ball), suggest);
		
		suggest=this.can_bet_redblack && this.plays_view.Has_Red_Run(trigger);
		pot+=this.Get_Bet_Button(ID_BETBLACKBUTT).Process_Play(Util.Is_Black(ball), suggest);
		
    if (pot>0)
    {
      this.Animate_Win(pot);
    }
		this.cash_button.Process_Play(pot);
  }
  
  public void Show_Bets()
  {
    float amount;
    
    amount=this.Get_Bet_Button(ID_BETEVENBUTT).amount+
      this.Get_Bet_Button(ID_BETODDBUTT).amount;
    this.plays_view.Set_Bet(amount, Play_List_View.COL_ODDEVEN);
    
    amount=this.Get_Bet_Button(ID_BETHIGHBUTT).amount+
      this.Get_Bet_Button(ID_BETLOWBUTT).amount;
    this.plays_view.Set_Bet(amount, Play_List_View.COL_HIGHLOW);
    
    amount=this.Get_Bet_Button(ID_BETREDBUTT).amount+
      this.Get_Bet_Button(ID_BETBLACKBUTT).amount;
    this.plays_view.Set_Bet(amount, Play_List_View.COL_REDBLACK);
	}
	
	public void Reset_Stats()
	{
		this.stat_cash_inv=0;
		this.stat_lrg_sng_bet=0;
		this.stat_play_count=0;
		this.Update_Stats_View();
	}
	
	public void Update_Stats()
	{
		this.stat_play_count++;
		
		this.Update_Stat_MaxBet(this.Get_Bet_Button(ID_BETZEROBUTT).amount);
		this.Update_Stat_MaxBet(this.Get_Bet_Button(ID_BETEVENBUTT).amount);
		this.Update_Stat_MaxBet(this.Get_Bet_Button(ID_BETODDBUTT).amount);
		this.Update_Stat_MaxBet(this.Get_Bet_Button(ID_BETHIGHBUTT).amount);
		this.Update_Stat_MaxBet(this.Get_Bet_Button(ID_BETLOWBUTT).amount);
		this.Update_Stat_MaxBet(this.Get_Bet_Button(ID_BETREDBUTT).amount);
		this.Update_Stat_MaxBet(this.Get_Bet_Button(ID_BETBLACKBUTT).amount);
		
		this.Update_Stats_View();
	}
	
	public void Update_Stat_MaxBet(float amount)
	{
		if (amount>this.stat_lrg_sng_bet)
			this.stat_lrg_sng_bet=amount;
	}
	
	public void Update_Stats_View()
	{
		if (this.stats_view!=null)
		{
			this.stats_view.cash_invest_view.data_view.setText(
        rs.android.util.Type.To_String(this.stat_cash_inv));
      this.stats_view.cash_avail_view.data_view.setText(
        rs.android.util.Type.To_String(this.Get_Cash_Avail()));
      this.stats_view.cash_change_view.data_view.setText(
        rs.android.util.Type.To_String(this.Get_Cash_Won()));
      this.stats_view.largest_bet_view.data_view.setText(
        rs.android.util.Type.To_String(this.stat_lrg_sng_bet));
      this.stats_view.play_count_view.data_view.setText(
        rs.android.util.Type.To_String(this.stat_play_count));
		}
	}
	
  public float Get_Cash_Won()
  {
    return this.Get_Cash_Avail()-this.stat_cash_inv;
  }
  
	public float Get_Cash_Avail()
	{
		float res=0;
		
		res = this.cash_button.tot_cash - 
		  this.Get_Bet_Button(ID_BETEVENBUTT).amount - 
		  this.Get_Bet_Button(ID_BETODDBUTT).amount - 
			this.Get_Bet_Button(ID_BETHIGHBUTT).amount - 
			this.Get_Bet_Button(ID_BETLOWBUTT).amount -
		  this.Get_Bet_Button(ID_BETREDBUTT).amount - 
			this.Get_Bet_Button(ID_BETBLACKBUTT).amount-
			this.Get_Bet_Button(ID_BETZEROBUTT).amount;
		return res;
	}
	
	public float Get_Tot_Bet()
	{
		float res=0;

		res =
		  this.Get_Bet_Button(ID_BETEVENBUTT).amount +
		  this.Get_Bet_Button(ID_BETODDBUTT).amount +
			this.Get_Bet_Button(ID_BETHIGHBUTT).amount + 
			this.Get_Bet_Button(ID_BETLOWBUTT).amount +
		  this.Get_Bet_Button(ID_BETREDBUTT).amount + 
			this.Get_Bet_Button(ID_BETBLACKBUTT).amount +
			this.Get_Bet_Button(ID_BETZEROBUTT).amount;
		return res;
	}
	
	public boolean Is_Bet_Req_Click(android.view.View view)
	{
		boolean res=false;
		
		if (this.Is_Bet_Button(view.getId()) && 
		  rs.android.util.Type.To_Int(view.getTag())==Bet_Button.BUTTON_REQ)
			res=true;
		return res;
	}
	
	public boolean Is_Bet_Complete_Click(android.view.View view)
	{
		boolean res=false;
		
		if (this.Is_Bet_Button(view.getId()) && 
		  rs.android.util.Type.To_Int(view.getTag())==Bet_Button.BUTTON_COMPLETE)
			res=true;
		return res;
	}
  
  public void Animate_Win(float win)
  {
    android.animation.ObjectAnimator anim;
    float cx, cy;
    
    cx=(float)this.plays_view.getWidth()/2f;
    cy=(float)this.plays_view.getHeight()/2f;
    this.fx_view.pos=new android.graphics.PointF(cx, cy);
    this.fx_view.text="Win! "+rs.android.util.Type.To_String(win, "0.00", "#,##0.00");
    
    anim=android.animation.ObjectAnimator.ofInt(this.fx_view, "frame", 0, 100);
    anim.setDuration(1000);
    anim.start();
  }
	
	// User Actions ==============================================================
	
	@Override
	public void onClick(android.view.View view)
	{
		if (view == this.cash_button)
	    this.On_Add_Cash();
		
		else if (view==this.play_button)
			this.no_sel_dlg=new Ball_Dialog(this.getContext(), this, ID_SELPLAYBALLDLG, false);
		else if (view.getId() == ID_SELPLAYBALLDLG)
			this.On_Play_Selected_Ball(this.no_sel_dlg.ball_no);
			
		else if (view==this.play_auto_button)
			this.On_Play_Random_Ball();
			
		if (view == this.bet_auto_button)
			this.On_Bet_Auto();
			
		else if (view == this.play_bet_auto_button)
			this.On_Play_Bet_Auto();
			
		else if (this.Is_Bet_Req_Click(view))
			this.On_Bet_Req((Bet_Button)view);
		else if (this.Is_Bet_Complete_Click(view))
			this.On_Bet_Complete((Bet_Button)view);
	}
	
	// add cash
	public void On_Add_Cash()
	{
		this.stat_cash_inv+=this.cash_button.last_entry;
		this.Update_Stats_View();
	}
	
	// select and play next ball
	public void On_Play_Selected_Ball(int ball)
	{
    //android.util.Log.d("On_Play_Selected_Ball()", "Entry");
    //rs.android.ui.Util.Show_Note(this.getContext(), "test");
		this.plays_view.Play(ball);
		this.Play(ball);
    this.Update_Stats_View();
    
    //android.util.Log.d("On_Play_Selected_Ball()", this.Get_Bet_Button(ID_BETEVENBUTT).To_String());
    //android.util.Log.d("On_Play_Selected_Ball()", this.Get_Bet_Button(ID_BETODDBUTT).To_String());
    //android.util.Log.d("On_Play_Selected_Ball()", this.Get_Bet_Button(ID_BETHIGHBUTT).To_String());
    //android.util.Log.d("On_Play_Selected_Ball()", this.Get_Bet_Button(ID_BETLOWBUTT).To_String());
    //android.util.Log.d("On_Play_Selected_Ball()", this.Get_Bet_Button(ID_BETREDBUTT).To_String());
    //android.util.Log.d("On_Play_Selected_Ball()", this.Get_Bet_Button(ID_BETBLACKBUTT).To_String());
    //android.util.Log.d("On_Play_Selected_Ball()", this.Get_Bet_Button(ID_BETZEROBUTT).To_String());
	}
	
	// play next random ball
	public int On_Play_Random_Ball()
	{
		int ball;

    ball=this.plays_view.Play_Auto();
		this.Play(ball);
    this.Update_Stats_View();
    
    return ball;
	}
	
	// place an automatws bet
	public void On_Bet_Auto()
	{
		float tot=0;
		int trigger;

    trigger=rs.roulettebudddy.activity.Settings_Activity.Get_Run_Trigger_Min(this.getContext());
		this.Reset_Bets();
		tot+=this.Get_Bet_Button(ID_BETEVENBUTT).
		  Bet_Auto(this.can_bet_evenodd && this.plays_view.Has_Odd_Run(trigger));
		tot+=this.Get_Bet_Button(ID_BETODDBUTT).
		  Bet_Auto(this.can_bet_evenodd && this.plays_view.Has_Even_Run(trigger));
		tot+=this.Get_Bet_Button(ID_BETHIGHBUTT).
		  Bet_Auto(this.can_bet_highlow && this.plays_view.Has_Low_Run(trigger));
		tot+=this.Get_Bet_Button(ID_BETLOWBUTT).
		  Bet_Auto(this.can_bet_highlow && this.plays_view.Has_High_Run(trigger));
		tot+=this.Get_Bet_Button(ID_BETREDBUTT).
		  Bet_Auto(this.can_bet_redblack && this.plays_view.Has_Black_Run(trigger));
		tot+=this.Get_Bet_Button(ID_BETBLACKBUTT).
		  Bet_Auto(this.can_bet_redblack && this.plays_view.Has_Red_Run(trigger));

		if (tot>this.cash_button.tot_cash)
		{
			this.Reset_Bets();
			rs.android.ui.Util.Show_Note(this.getContext(), "Insufficient Funds");
		}
		else
		  this.cash_button.Set_Reserved_Cash(tot);
      
    this.Update_Stats_View();
	}
	
	// play next random ball and place an automated bet
	public void On_Play_Bet_Auto()
	{
		this.On_Play_Random_Ball();
		this.On_Bet_Auto();
    this.Show_Bets();
    this.Update_Stats_View();
	}
	
	// user wants to place a bet
	public void On_Bet_Req(Bet_Button button)
	{
		button.limit=this.cash_button.Get_Cash_Available();
	}
	
	// user has placed a bet
	public void On_Bet_Complete(Bet_Button button)
	{
		float tot_bet;
		
		tot_bet=this.Get_Tot_Bet();
		this.cash_button.Set_Reserved_Cash(tot_bet);
    this.Show_Bets();
		this.Update_Stats_View();
	}
}
