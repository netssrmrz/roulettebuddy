package rs.roulettebudddy;
//import android.graphics.*;
//import android.graphics.*;
//import android.graphics.*;

public class Wow_View
extends android.view.View
{
  public String text;
  public android.graphics.PointF pos;
  public int frame;
  public android.graphics.Paint text_paint;
  
  public Wow_View(android.content.Context ctx)
  {
    super(ctx);
    
    this.text_paint=new android.graphics.Paint();
    this.text_paint.setStyle(android.graphics.Paint.Style.FILL);
    this.text_paint.setColor(0xffffff00);
    this.text_paint.setAntiAlias(true);
    this.text_paint.setTextAlign(android.graphics.Paint.Align.CENTER);
    this.text_paint.setTextSize(20);
  }
  
  public void onDraw(android.graphics.Canvas canvas) 
  {
    int alpha;
    float size;
    android.graphics.PointF pos;
    
    if (rs.android.Util.NotEmpty(this.text) && frame>0)
    {
      alpha=(int)(-255f/100f*(float)frame+255f);
      size=4f*(float)frame;

      this.text_paint.setTextSize(size);
      this.text_paint.setAlpha(alpha);
      
      pos=this.Calc_Text_Center(this.pos, this.text_paint);
      canvas.drawText(this.text, pos.x, pos.y, this.text_paint);
    }
  }

  public void onSizeChanged(int w, int h, int oldw, int oldh)
  {
	}
  
  public void setFrame(int frame)
  {
    this.frame=frame;
    this.invalidate();
  }
  
  public int getFrame()
  {
    return this.frame;
  }
  
  public android.graphics.PointF Calc_Text_Center(android.graphics.PointF pos,
    android.graphics.Paint text_paint)
  {
    float text_height, text_offset;
    android.graphics.PointF res=null;
    
    text_height=text_paint.descent()-text_paint.ascent();
    text_offset=(text_height/2f)-text_paint.descent();
    
    res=new android.graphics.PointF();
    res.x=pos.x;
    res.y=pos.y+text_offset;
    
    return res;
  }
  
  /*public void Set_Pos(android.graphics.PointF pos)
  {
    this.pos=this.Calc_Text_Center(pos, this.text_paint);
  }*/
}
