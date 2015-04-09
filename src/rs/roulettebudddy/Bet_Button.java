package rs.roulettebudddy;

public class Bet_Button
extends android.widget.Button
implements android.view.View.OnClickListener
{
	public static final int TEXTSIZE_BETROW=12;
	public static final int ID_BETDLG=1;
	public static final int BUTTON_REQ=1;
	public static final int BUTTON_COMPLETE=2;

	public float amount;
	public float loss;
	public String label;
  public float limit;
	public Amount_Dialog dlg;
	public android.view.View.OnClickListener 
	on_bet_complete_listener, on_req_bet_listener;

	public Bet_Button(android.content.Context ctx, int id, String label,
	  android.view.View.OnClickListener on_req_bet_listener,
		android.view.View.OnClickListener on_bet_complete_listener)
	{
		super(ctx);
		this.setId(id);
		this.label = label;
		this.Reset();
		this.setOnClickListener(this);
		this.setTextSize(TEXTSIZE_BETROW);
		this.on_bet_complete_listener = on_bet_complete_listener;
		this.on_req_bet_listener = on_req_bet_listener;
	}

	@Override
	public void onClick(android.view.View view)
	{
		if (view == this)
		{
			this.setTag(BUTTON_REQ);
			if (this.on_req_bet_listener != null)
        this.on_req_bet_listener.onClick(this);
			this.Get_Bet();
		}
		else if (view == this.dlg)
		{
			this.setTag(BUTTON_COMPLETE);
			this.On_Set_Amount((rs.roulettebudddy.Amount_Dialog)view);
			if (this.on_bet_complete_listener != null)
				this.on_bet_complete_listener.onClick(this);
		}
	}

	public void Get_Bet()
	{
		this.dlg = new rs.roulettebudddy.Amount_Dialog(
			this.getContext(), this, ID_BETDLG, this.amount, this.limit, "To Bet");
	}

	public void On_Set_Amount(Amount_Dialog dlg)
	{
		this.Bet(dlg.amount);
	}

	public void Inc_Bet(float amount)
	{
		this.Bet(this.amount + amount);
	}

	public void Bet(float amount)
	{
    String text="";

		this.amount = amount;
		text = this.label + " " + rs.android.util.Type.To_String(amount);
		this.setText(text);
		if (amount > 0)
			rs.android.ui.Util.Set_Button_Colour(this, Util.COL_ZERO);
		else
			rs.android.ui.Util.Remove_Button_Colour(this);
	}	

	public float Process_Play(boolean win, boolean suggest)
	{
		float res=0;

		if (this.amount > 0)
		{
			if (win)
			{
				res = this.Calc_Win(this.amount);
				this.loss = 0;
			}
			else
			{
				this.loss = this.amount;
			}
			this.Bet(0);
		}
    else
      this.loss=0;

		if (suggest)
			rs.android.ui.Util.Set_Button_Colour(this, Util.COL_SUGGEST);
    else
      rs.android.ui.Util.Remove_Button_Colour(this);

		return res;
	}

	public float Calc_Win(float amount)
	{
		return amount * 2;
	}

	public float Bet_Auto(boolean can_bet)
	{
		float bet=0, max_bet, min_bet;

		if (can_bet)
		{
      max_bet=rs.roulettebudddy.activity.Settings_Activity.Get_Max_Bet(this.getContext());
      min_bet=rs.roulettebudddy.activity.Settings_Activity.Get_Min_Bet(this.getContext());
      
			if (this.loss > 0)
			{
				bet = this.loss * 2;
        if (max_bet>0 && max_bet>min_bet && bet>max_bet)
          bet=0;
			}
			else
				bet = min_bet;
			this.Bet(bet);
		}
		return bet;
	}

	public void Reset()
	{
		this.Bet(0);
		this.loss = 0;
	}
  
  @Override
  public android.os.Parcelable onSaveInstanceState()
  {
    android.os.Bundle state;
    android.os.Parcelable base_state;
    
    base_state=super.onSaveInstanceState();
    
    state=new android.os.Bundle();
    state.putParcelable("base_state", base_state);
    
    state.putFloat("amount", this.amount);
    state.putFloat("loss", this.loss);
    state.putFloat("limit", this.limit);
    
    return state;
  }
  
  @Override
  public void onRestoreInstanceState(android.os.Parcelable raw_state)
  {
    android.os.Parcelable base_state;
    android.os.Bundle state;
    
    if (raw_state instanceof android.os.Bundle)
    {
      state=(android.os.Bundle)raw_state;
      
      this.Bet(state.getFloat("amount"));
      this.loss=state.getFloat("loss");
      this.limit=state.getFloat("limit");
      
      base_state=state.getParcelable("base_state");
      super.onRestoreInstanceState(base_state);
    }
  }
  
  public String To_String()
  {
    String res=null;
    
    res=
      "amount: "+this.amount+", "+
      "loss: "+this.loss+", "+
      "label: "+this.label+", "+
      "limit: "+this.limit;
    return res;
  }
}
