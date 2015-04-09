package rs.roulettebudddy;

public class Stats_View
extends android.widget.LinearLayout
{
  public Label_Data_View cash_avail_view;
  public Label_Data_View cash_invest_view;
  public Label_Data_View cash_change_view;
  public Label_Data_View longest_run_view;
  public Label_Data_View largest_bet_view;
  public Label_Data_View play_count_view;
  
  public Stats_View(android.content.Context ctx, int id_base)
  {
    super(ctx);

    this.cash_avail_view=new Label_Data_View(this.getContext(), "Cash Available",
      Label_Data_View.SIZE_BIG, 0xff00ff00, id_base+100);
    this.cash_invest_view=new Label_Data_View(this.getContext(), "Cash Invested", id_base+200);
    this.cash_change_view=new Label_Data_View(this.getContext(), "Cash Won", id_base+300);
    this.longest_run_view=new Label_Data_View(this.getContext(), "Longest Run", id_base+400);
    this.largest_bet_view=new Label_Data_View(this.getContext(), "Largest Bet", id_base+500);
    this.play_count_view=new Label_Data_View(this.getContext(), "Play Count", id_base+600);

    this.setOrientation(android.widget.LinearLayout.HORIZONTAL);
    this.addView(this.cash_avail_view);
    this.addView(this.cash_invest_view);
    this.addView(this.cash_change_view);
    this.addView(this.longest_run_view);
    this.addView(this.largest_bet_view);
    this.addView(this.play_count_view);
    this.setId(id_base);
  }
}
