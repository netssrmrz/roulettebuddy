package rs.roulettebudddy;
//import android.content.*;

public class Ball_Dialog
extends android.view.View
implements android.app.Dialog.OnClickListener,
android.view.View.OnClickListener
{
	public android.app.AlertDialog.Builder bld;
	public int ball_no;
	public android.view.View.OnClickListener on_click_listener;
	public boolean is_multiplay;
	public android.app.AlertDialog dlg;

	public Ball_Dialog(android.content.Context ctx, 
		android.view.View.OnClickListener on_click_listener,
		int id, boolean is_multiplay)
	{
		super(ctx);

		this.on_click_listener = on_click_listener;
		this.setId(id);
		this.is_multiplay = is_multiplay;

		android.app.AlertDialog.Builder bld;
		android.widget.LinearLayout button_rows;
		android.view.ViewGroup.LayoutParams layout;

		layout = new android.view.ViewGroup.LayoutParams(
		  android.view.ViewGroup.LayoutParams.FILL_PARENT,
			android.view.ViewGroup.LayoutParams.FILL_PARENT);
		button_rows = new android.widget.LinearLayout(ctx);
		button_rows.setOrientation(android.widget.LinearLayout.VERTICAL);
		button_rows.setLayoutParams(layout);
		//rs.android.ui.Border_Drawable.Add_Border(button_rows, 0xffff0000);

		this.Add_Button_Row(0, 9, ctx, button_rows);
		this.Add_Button_Row(10, 19, ctx, button_rows);
		this.Add_Button_Row(20, 29, ctx, button_rows);
		this.Add_Button_Row(30, 36, ctx, button_rows);

		bld = new android.app.AlertDialog.Builder(ctx);
		bld.setTitle("Select next number...");
		bld.setView(button_rows);
		if (is_multiplay)
			bld.setPositiveButton("Done", this);
    this.dlg = bld.show();
	}

	public void Add_Button_Row(int from, int to, android.content.Context ctx,
	  android.widget.LinearLayout button_rows)
	{
		android.widget.LinearLayout button_row;
		android.widget.Button button;
		android.view.ViewGroup.LayoutParams row_layout;
		android.widget.LinearLayout.LayoutParams button_layout;
		int c;

		row_layout = new android.widget.LinearLayout.LayoutParams(
		  android.view.ViewGroup.LayoutParams.MATCH_PARENT,
			android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		button_row = new android.widget.LinearLayout(ctx);
		button_row.setOrientation(android.widget.LinearLayout.HORIZONTAL);
		button_row.setLayoutParams(row_layout);
		//rs.android.ui.Border_Drawable.Add_Border(button_row, 0xff00ff00);
		for (c = from; c <= to; c++)
		{
			button_layout = new android.widget.LinearLayout.LayoutParams(
			  0, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
			button = new android.widget.Button(ctx);
			button.setText(rs.android.util.Type.To_String(c));
			button.setTextSize(15);
			button.setPadding(0, 0, 0, 0);
			button.setLayoutParams(button_layout);
			button.setOnClickListener(this);
			button.setTag(new Integer(c));
			if (c == 0)
				rs.android.ui.Util.Set_Button_Colour(button, Util.COL_ZERO);
			else if (Util.Is_Red(c))
				rs.android.ui.Util.Set_Button_Colour(button, Util.COL_RED);
			else if (Util.Is_Black(c))
				rs.android.ui.Util.Set_Button_Colour(button, Util.COL_BLACK);
			//rs.android.ui.Border_Drawable.Add_Border(button, 0xff0000ff);
			button_row.addView(button);
		}
		button_rows.addView(button_row);
	}

	@Override
	public void onClick(android.content.DialogInterface dlg, int which)
	{
		/*if (which == android.content.DialogInterface.BUTTON_POSITIVE)
		 {
		 this.ball_no = rs.android.util.Type.To_Int
		 (this.amount_text.getText().toString());
		 this.on_click_listener.onClick(this);
		 }*/
		this.dlg.dismiss();
	}

	@Override
	public void onClick(android.view.View view)
	{
		if (view.getTag() != null)
		{
			this.ball_no = rs.android.util.Type.To_Int(view.getTag());

			if (!this.is_multiplay)
				this.dlg.dismiss();

			if (this.on_click_listener != null)
			{
				this.on_click_listener.onClick(this);
			}
		}
	}
}
