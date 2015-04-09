package rs.roulettebudddy;

public class Zero_Button
extends Bet_Button
{
	public Zero_Button(android.content.Context ctx, int id, android.view.View.OnClickListener on_click_listener)
	{
		super(ctx, id, "Bet Zero", null, on_click_listener);
	}
	
	@Override
	public float Calc_Win(float amount)
	{
		return amount*36;
	}
}
