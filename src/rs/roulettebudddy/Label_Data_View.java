package rs.roulettebudddy;

public class Label_Data_View
extends android.widget.LinearLayout
{
  public static final int SIZE_BIG=1;
  public static final int SIZE_SMALL=2;
  
  public android.widget.TextView label_view;
  public android.widget.TextView data_view;
  
  public Label_Data_View(android.content.Context ctx, String label, int id_base)
  {
    this(ctx, label, SIZE_SMALL, 0xffffffff, id_base);
  }
  
  public Label_Data_View(android.content.Context ctx, String label, int size,
    int colour, int id_base)
  {
    super(ctx);
    
    this.label_view=new android.widget.TextView(ctx);
    this.label_view.setId(id_base+1);
    this.label_view.setTextColor(0xff888888);
    this.label_view.setTextSize(15);
    this.label_view.setText(label);
    
    this.data_view=new android.widget.TextView(ctx);
    this.data_view.setId(id_base+2);
    this.data_view.setGravity(android.view.Gravity.RIGHT);
    this.data_view.setPadding(0, 0, 0, 0);
    this.data_view.setTextColor(colour);
    
    if (size==SIZE_BIG)
      this.data_view.setTextSize(50);
    else if (size==SIZE_SMALL)
      this.data_view.setTextSize(20);
    
    this.setOrientation(android.widget.LinearLayout.VERTICAL);
    this.setPadding(0, 0, 20, 10);
    this.addView(this.label_view);
    this.addView(this.data_view);
    this.setId(id_base);
  }

  @Override
  public android.os.Parcelable onSaveInstanceState()
  {
    android.os.Bundle state;
    android.os.Parcelable base_state;

    base_state=super.onSaveInstanceState();

    state=new android.os.Bundle();
    state.putParcelable("base_state", base_state);

    state.putString("label_text", this.label_view.getText().toString());
    state.putString("data_text", this.data_view.getText().toString());

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

      this.label_view.setText(state.getString("label_text"));
      this.data_view.setText(state.getString("data_text"));

      base_state=state.getParcelable("base_state");
      super.onRestoreInstanceState(base_state);
    }
  }
}
