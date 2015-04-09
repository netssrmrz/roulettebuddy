package rs.roulettebudddy;
//import android.content.*;

public class Circle_View
extends android.view.View
{
  public android.graphics.Paint circle_paint, text_paint;
  public int cx, cy, r;
  public String text;
  
  public Circle_View(android.content.Context ctx)
  {
    super(ctx);
    
    this.circle_paint=new android.graphics.Paint();
    this.circle_paint.setStyle(android.graphics.Paint.Style.FILL);
    this.circle_paint.setColor(0xffffffff);
    this.circle_paint.setAntiAlias(true);
    
    this.text_paint=new android.graphics.Paint();
    this.text_paint.setStyle(android.graphics.Paint.Style.FILL);
    this.text_paint.setColor(0xff000000);
    this.text_paint.setAntiAlias(true);
    this.text_paint.setTextAlign(android.graphics.Paint.Align.CENTER);
    this.text_paint.setTextSize(20);
  }
  
  public void onDraw(android.graphics.Canvas canvas) 
  {
    if (rs.android.Util.NotEmpty(this.text))
    {
      canvas.drawCircle(cx, cy, r, this.circle_paint);
      canvas.drawText(this.text, cx, cy+6.5f, text_paint);
    }
  }

  public void Set_Text(String text, int colour)
  {
    //rs.android.ui.Util.Show_Note(this.getContext(), "Set_Text(): "+text);
    this.text=text;
    this.circle_paint.setColor(colour);
    //rs.android.ui.Border_Drawable.Add_Border(this, 0xffff0000);
    this.invalidate();
  }
  
  public void onSizeChanged(int w, int h, int oldw, int oldh)
  {
    cx=w/2;
    cy=h/2;
    if (cx<cy)
      r=cx;
    else
      r=cy;
    //android.util.Log.d("onSizeChanged", "cx,cy,r:"+cx+","+cy+","+r);
	}
}
