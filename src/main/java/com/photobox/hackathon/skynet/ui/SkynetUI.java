package com.photobox.hackathon.skynet.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.photobox.hackathon.skynet.GMicExecutor;
import com.photobox.hackathon.skynet.Parameters;

public class SkynetUI extends JFrame implements ActionListener 
{
	public JDesktopPane 	desk;

	private JButton 		butQueryFile;
	private JButton 		butQueryTx;
	private JInternalFrame 	jifTree;
	private JPanel			panelTop;
	private JLabel			labelImage;
	private JLabel			labelImageLeft;
	private JLabel			labelImageRight;
	private JSplitPane 		splitCenter;
	private JPanel 			panelLeft;
	private JPanel 			panelRight;
	private JComboBox		comboTx;
	
	private String path = "/Users/ddarseyne/java/hackathon/img";

	private String 			strFile;
	
	
	public SkynetUI() 
	{
		super("SKYNET UI");

		createDesktopPane();
		buildUI();
	}

	public void createDesktopPane() 
	{
		this.desk = new JDesktopPane();

		this.getContentPane().add(desk, BorderLayout.CENTER);

		desk.putClientProperty("JDesktopPane.dragMode", "faster");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void buildUI() 
	{
		this.jifTree 			= new JInternalFrame("Skynet Processing", true, true, true, true);
 		this.panelTop			= new JPanel();
 		this.butQueryFile		= new JButton("File");
 		this.butQueryTx			= new JButton("Process"); 		
 		this.labelImage			= new JLabel();			
 		this.panelLeft			= new JPanel();
 		this.panelRight			= new JPanel();
 		this.labelImageLeft		= new JLabel();
 		this.labelImageRight	= new JLabel();
 		this.comboTx			= new JComboBox(new String[] {"Tx"});
 		
 		
 		//panelLeft.setPreferredSize(new Dimension(300, 300));
 		//panelRight.setPreferredSize(new Dimension(300, 300));
 		
 		this.splitCenter		= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
		splitCenter.setDividerLocation(0.50);
		//splitCenter.setRes
		
		//labelImageLeft.setPreferredSize(new Dimension(300, 300));
		//labelImageRight.setPreferredSize(new Dimension(300, 300));
		
		panelLeft.add(labelImageLeft);
		panelRight.add(labelImageRight);
 		
		
 		//labelImage.setPreferredSize(new Dimension(100, 20));
 		
 		jifTree.setPreferredSize(new Dimension(900, 700));
 		
 		butQueryFile.addActionListener(this);
 		butQueryTx.addActionListener(this);
 		
 		panelTop.setLayout(	new GridLayout(1, 6));
 		
 		panelTop.add(	butQueryFile);
 		panelTop.add(	comboTx);
 		panelTop.add(	labelImage);
 		panelTop.add(	butQueryTx);
 		
 		
 		//panelTop.setPreferredSize(new Dimension(200, 50));
 		
		jifTree.getContentPane().add(panelTop, 		BorderLayout.NORTH);
		jifTree.getContentPane().add(splitCenter, 	BorderLayout.CENTER);
		
		desk.add(jifTree, 1);
		
		splitCenter.doLayout();		
		splitCenter.updateUI();
		
		jifTree.setVisible(true);
		jifTree.pack();
		jifTree.getContentPane().doLayout();		
		jifTree.updateUI();		
	}
	
	public String chooseFile()
	{
 		JFileChooser chooser 	= new JFileChooser(path);

 		chooser.setFileFilter(new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"));
 	    
 	    int returnVal = chooser.showOpenDialog(this);
 	    
 	    if (returnVal == JFileChooser.APPROVE_OPTION) 
 	    {
 	       return chooser.getSelectedFile().getName();
 	    }		
 	    
 	    return "";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == butQueryFile)
		{
			String strFile = chooseFile();
			labelImage.setText(strFile);
		
			System.out.println("fetch strFile=" + strFile);
		
			ImageIcon ii = new ImageIcon(path + "/" + strFile);
	
			labelImageLeft.setIcon(ii);
			
			//panelLeft.add(new JLabel(ii), BorderLayout.CENTER);
			
			System.out.println("w=" + ii.getIconWidth() + ", h=" + ii.getIconHeight());
			
			//panelLeft.setPreferredSize(new Dimension(ii.getIconWidth(), ii.getIconHeight()));
			labelImageLeft.setPreferredSize(new Dimension(ii.getIconWidth(), ii.getIconHeight()));
			
			//jifTree.pack();
			jifTree.getContentPane().doLayout();		
			jifTree.updateUI();		
			splitCenter.doLayout();		
			splitCenter.updateUI();
			panelLeft.updateUI();
			panelLeft.doLayout();
			
			this.strFile = strFile;
		}
		

		if (e.getSource() == butQueryTx)
		{
			GMicExecutor executor = new GMicExecutor();
			Parameters parameters = new Parameters();
			//parameters.setMainParameter(" -rodilius ");
			parameters.setMainParameter(" -texturize_paper ");
			
			executor.execute(path + "/" + strFile, path + "/" + strFile +"-modified", parameters);
			
			System.out.println("execute has returned");
			
			ImageIcon iim = new ImageIcon(path + "/" + strFile+"-modified");
			
			System.out.println("w=" + iim.getIconWidth() + ", h=" + iim.getIconHeight());
			
			labelImageRight.setIcon(iim);
			labelImageRight.setPreferredSize(new Dimension(iim.getIconWidth(), iim.getIconHeight()));
			
			jifTree.getContentPane().doLayout();		
			jifTree.updateUI();		
			splitCenter.doLayout();		
			splitCenter.updateUI();
			panelLeft.updateUI();
			panelLeft.doLayout();
		}
	
		
	}



}
