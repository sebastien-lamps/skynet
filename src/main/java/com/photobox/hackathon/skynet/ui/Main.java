package com.photobox.hackathon.skynet.ui;

public class Main 
{
	
	public static void main(String args[])
	{		
		Main newAppli			= new Main();		
	}
	
	public Main()
	{		
		buildUI();
	}	

	private void buildUI()
	{
		SkynetUI view 		= new SkynetUI();
		
		view.setSize(IViewConstants.INT_APPLI_WIDTH, IViewConstants.INT_APPLI_HEIGHT);
		view.setLocation(100, 0);
		view.setVisible(true);
	}

}
