package as.mke.jsgameengine;
import as.mke.gametet.System.logic.*;
import as.mke.gametet.System.view.*;
import android.view.*;
import wendu.dsbridge.*;

public class GameLogic extends Game
{
	
	MainView view;
	public GameLogic(MainView view){
		super(view);
		this.view=view;
	}

	@Override
	public void logic()
	{
		view.activity.runOnUiThread(new Runnable(){
			public void run(){
				
				//view.activity.showToast("logic()");
		view.activity.dWebView.callHandler("init", new Object[]{3, 4}, new OnReturnValue<Integer>() {
				@Override
				public void onValue(final Integer retValue) {
					
					view.activity.runOnUiThread(new Runnable(){
							public void run(){
					
					view.activity.showToast(retValue);
					}});
					
					
				}
			});
			
			
			}
		});
		
	}

	@Override
	public void onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
	}


	
	
}
