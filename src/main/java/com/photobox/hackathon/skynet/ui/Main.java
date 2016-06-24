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
		
		view.setSize(1920, 1080);
		view.setLocation(0, 0);
		view.setVisible(true);
	}

}
