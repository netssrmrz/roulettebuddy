package rs.roulettebudddy;

public class Util
{
	public static final int COL_HIGH=0xff000077;
	public static final int COL_LOW=0xff222222;
	public static final int COL_RED=0xff770000; 
	public static final int COL_BLACK=0xff222222; 
	public static final int COL_ODD=0xff222222; 
	public static final int COL_EVEN=0xff666600;
	public static final int COL_ZERO=0xff006600;
	public static final int COL_SUGGEST=0xff666600;
	
	public static boolean Is_High(int no)
	{
		boolean res=false;

		if (no >= 19 && no <= 36)
			res = true;

		return res;
	}

	public static boolean Is_Low(int no)
	{
		boolean res=false;

		if (no >= 1 && no <= 18)
			res = true;

		return res;
	}
	
	public static boolean Is_Red(int no)
	{
		boolean res=false;

		if (no >= 1 && no <= 10 && Is_Odd(no))
			res = true;
		else if (no >= 19 && no <= 28 && Is_Odd(no))
			res = true;
		else if (no >= 11 && no <= 18 && Is_Even(no))
			res = true;
		else if (no >= 29 && no <= 36 && Is_Even(no))
			res = true;

		return res;
	}

	public static boolean Is_Black(int no)
	{
		boolean res=false;

		if (no >= 29 && no <= 36 && Is_Odd(no))
			res = true;
		else if (no >= 11 && no <= 18 && Is_Odd(no))
			res = true;
		else if (no >= 1 && no <= 10 && Is_Even(no))
			res = true;
		else if (no >= 19 && no <= 28 && Is_Even(no))
			res = true;

		return res;
	}
	
	public static boolean Is_Even(int val)
	{
		boolean res=false;

		if (val != 0 && (val & 1) == 0)
			res = true;
		return res;
	}

	public static boolean Is_Odd(int val)
	{
		boolean res=false;

		if (val != 0 && !Is_Even(val))
			res = true;
		return res;
	}

  public static int Get_OddEven_Col(int ball)
  {
    int col;

    if (ball == 0)
      col=Util.COL_ZERO;
    else if (Util.Is_Even(ball))
      col=Util.COL_EVEN;
    else
      col=Util.COL_ODD;

    return col;
  }

  public static int Get_BigSmall_Col(int ball)
  {
    int col;

    if (ball == 0)
      col=Util.COL_ZERO;
    else if (ball >= 1 && ball <= 18)
      col=Util.COL_LOW;
    else //if (ball >= 19 && ball <= 36)
      col=Util.COL_HIGH;

    return col;
  }
  
  public static int Get_RedBlack_Col(int ball)
  {
    int col;

    if (Util.Is_Red(ball))
      col=Util.COL_RED;
    else if (Util.Is_Black(ball))
      col=Util.COL_BLACK;
    else
      col=Util.COL_ZERO;

    return col;
  }
}
