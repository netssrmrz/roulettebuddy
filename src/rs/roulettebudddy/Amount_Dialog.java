package rs.roulettebudddy;
import android.widget.*;

public class Amount_Dialog
extends android.view.View
implements android.app.Dialog.OnClickListener,
android.view.View.OnClickListener
{
	public static final int ID_BUTTON1=1;
	public static final int ID_BUTTON2=2;
	public static final int ID_BUTTON5=5;
	public static final int ID_BUTTON10=10;
	public static final int ID_BUTTON20=20;

	public android.app.AlertDialog dlg;
	public android.widget.TextView amount_text;
	public float amount;
	public android.view.View.OnClickListener on_click_listener;
	public float limit;
	public String label;

	/*public Amount_Dialog(android.content.Context ctx, 
	 android.view.View.OnClickListener on_click_listener,
	 int id, float amount)
	 {
	 this(ctx, on_click_listener, id, amount, 0, "To Bet");
	 }*/

	public Amount_Dialog(android.content.Context ctx, 
	  android.view.View.OnClickListener on_click_listener,
		int id, float amount, float limit, String label)
	{
		super(ctx);
		android.widget.LinearLayout main_layout, top_row, bottom_row;
		android.widget.Button button;
	  android.app.AlertDialog.Builder bld;

		this.limit = limit;
		this.on_click_listener = on_click_listener;
		this.setId(id);
		this.label = label;

		this.amount_text = new android.widget.TextView(ctx);
		this.amount_text.setGravity(android.view.Gravity.CENTER);
		this.amount_text.setTextSize(20);
		this.amount_text.setPadding(5, 5, 5, 5);
		this.amount_text.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
		this.Set_Amount(amount);

		top_row = new android.widget.LinearLayout(ctx);
		top_row.setOrientation(android.widget.LinearLayout.HORIZONTAL);
		top_row.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
		top_row.setBackgroundColor(android.R.color.transparent);
		//rs.android.ui.Border_Drawable.Add_Border(layout, 0xffff0000);

    bottom_row = new android.widget.LinearLayout(ctx);
    bottom_row.setOrientation(android.widget.LinearLayout.HORIZONTAL);
    bottom_row.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
        android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
    bottom_row.setBackgroundColor(android.R.color.transparent);
    //rs.android.ui.Border_Drawable.Add_Border(layout, 0xffff0000);
    
		main_layout = new android.widget.LinearLayout(ctx);
		main_layout.setOrientation(android.widget.LinearLayout.VERTICAL);
		main_layout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT));
		main_layout.setBackgroundColor(android.R.color.transparent);
		main_layout.addView(this.amount_text);
		main_layout.addView(top_row);
    main_layout.addView(bottom_row);

		button = new android.widget.Button(ctx);
		button.setText("0");
		button.setOnClickListener(this);
		button.setTag(0f);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		top_row.addView(button);
    
    button = new android.widget.Button(ctx);
    button.setText("+0.5");
    button.setOnClickListener(this);
    button.setTag(0.5f);
    button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
        0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		top_row.addView(button);
    
		button = new android.widget.Button(ctx);
		button.setText("+1");
		button.setOnClickListener(this);
		button.setTag(1f);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		top_row.addView(button);

		button = new android.widget.Button(ctx);
		button.setText("+2");
		button.setOnClickListener(this);
		button.setTag(2f);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		top_row.addView(button);

		button = new android.widget.Button(ctx);
		button.setText("+5");
		button.setOnClickListener(this);
		button.setTag(5f);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		top_row.addView(button);

		button = new android.widget.Button(ctx);
		button.setText("+10");
		button.setOnClickListener(this);
		button.setTag(10f);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		bottom_row.addView(button);

		button = new android.widget.Button(ctx);
		button.setText("+20");
		button.setOnClickListener(this);
		button.setTag(20f);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		bottom_row.addView(button);

		button = new android.widget.Button(ctx);
		button.setText("+50");
		button.setOnClickListener(this);
		button.setTag(50f);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		bottom_row.addView(button);

		button = new android.widget.Button(ctx);
		button.setText("+100");
		button.setOnClickListener(this);
		button.setTag(100f);
		button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
				0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		bottom_row.addView(button);

    button = new android.widget.Button(ctx);
    button.setText("+1000");
    button.setOnClickListener(this);
    button.setTag(1000f);
    button.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
        0, android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		bottom_row.addView(button);
    
		bld = new android.app.AlertDialog.Builder(ctx);
		bld.setTitle("Select an amount...");
		bld.setView(main_layout);
		bld.setPositiveButton("Done", this);
		//bld.setNegativeButton("Cancel", this);
		this.dlg = bld.show();
	}

	@Override
	public void onClick(android.content.DialogInterface dlg, int which)
	{
		if (which == android.content.DialogInterface.BUTTON_POSITIVE)
		{
			this.on_click_listener.onClick(this);
		}
	}

	@Override
	public void onClick(android.view.View view)
	{
		float amount;

		amount = rs.android.util.Type.To_Float(view.getTag());
		if (amount == 0)
			this.Set_Amount(0);
		else
		  this.Set_Amount(this.amount + amount);
		//this.on_click_listener.onClick(this);
	}

	public void Set_Amount(float amount)
	{
		String text="";

		if (this.limit > 0)
		{
			if (amount < this.limit)
				this.amount = amount;
			else
				this.amount = this.limit;
		  text = "Available: " + (this.limit - this.amount) + ", ";
		}
		else
			this.amount=amount;
		text = text + label + ": " + rs.android.util.Type.To_String(this.amount);

		this.amount_text.setText(text);
	}
}
