package rs.roulettebudddy;

public class Cash_Button
extends android.widget.Button
implements android.view.View.OnClickListener
{
	public static final int TEXTSIZE_BETROW=12;
	public static final int ID_DLG=1;
	
	public float tot_cash;
	public float last_entry;
	public float reserved_cash;
	android.view.View.OnClickListener on_deposit_complete_listener;
	public Amount_Dialog dlg;
	
	public Cash_Button(android.content.Context ctx, int id,
		android.view.View.OnClickListener on_deposit_complete_listener)
	{
		super(ctx);
		this.setId(id);
		this.Set_Cash(0);
		this.setOnClickListener(this);
		this.setTextSize(TEXTSIZE_BETROW);
		this.on_deposit_complete_listener=on_deposit_complete_listener;
	}
	
	@Override
	public void onClick(android.view.View view)
	{
		if (view == this)
		 {
			 this.dlg = new rs.roulettebudddy.Amount_Dialog(
				 this.getContext(), this, ID_DLG, 0, 0, "Cash to Add");
		 }
		 else if (view == this.dlg)
		{
			this.Add_Cash(((rs.roulettebudddy.Amount_Dialog)view).amount);
		}
	}
	
	public void Reset()
	{
		this.tot_cash=0;
		this.reserved_cash=0;
		this.last_entry=0;
		this.Update_UI();
	}
	
	public void Process_Play(float win)
	{
		this.tot_cash=this.tot_cash-this.reserved_cash+win;
		this.reserved_cash=0;
		this.Update_UI();
	}
	
	public void Add_Cash(float amount)
	{
		this.last_entry=amount;
		this.Set_Cash(this.tot_cash+amount);
		if (this.on_deposit_complete_listener!=null)
			this.on_deposit_complete_listener.onClick(this);
	}
	
	public void Set_Cash(float amount)
	{
		this.tot_cash=amount;
		this.Update_UI();
	}
	
	public void Set_Reserved_Cash(float amount)
	{
		this.reserved_cash=amount;
		this.Update_UI();
	}
	
	public void Update_UI()
	{
		float avail_cash;
		
		avail_cash=this.tot_cash-this.reserved_cash;
		this.setText("Cash "+avail_cash);
	}
	
	public float Get_Cash_Available()
	{
		return this.tot_cash-this.reserved_cash;
	}
  
  @Override
  public android.os.Parcelable onSaveInstanceState()
  {
    android.os.Bundle state;
    android.os.Parcelable base_state;

    base_state=super.onSaveInstanceState();

    state=new android.os.Bundle();
    state.putParcelable("base_state", base_state);

    state.putFloat("tot_cash", this.tot_cash);
    state.putFloat("last_entry", this.last_entry);
    state.putFloat("reserved_cash", this.reserved_cash);

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
      
      this.last_entry=state.getFloat("last_entry");
      this.Set_Cash(state.getFloat("tot_cash"));
      this.Set_Reserved_Cash(state.getFloat("reserved_cash"));

      base_state=state.getParcelable("base_state");
      super.onRestoreInstanceState(base_state);
    }
  }
}
