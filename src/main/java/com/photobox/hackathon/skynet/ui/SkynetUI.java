package com.photobox.hackathon.skynet.ui;

import com.photobox.hackathon.skynet.GMicExecutor;
import com.photobox.hackathon.skynet.Parameters;
import com.photobox.hackathon.skynet.Scale2XExecutor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private String path = "/home/slamps/workspaces/git/skynet/src/test/resources";

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
 		this.labelImageLeft		= new JLabel(" ");
 		this.labelImageRight	= new JLabel(" ");
 		this.comboTx			= new JComboBox(new String[] {"Rescale", "Filter"});
 		
 		labelImageLeft.setPreferredSize(new Dimension(900, 1000));
 		labelImageRight.setPreferredSize(new Dimension(900, 1000));
 		
 		panelLeft.add(labelImageLeft);
 		panelRight.add(labelImageRight);
 		
 		this.splitCenter		= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
		splitCenter.setDividerLocation(0.50);
		
		
 		//jifTree.setPreferredSize(new Dimension(1920, 1080));
		jifTree.setPreferredSize(new Dimension(1920, 1080));
		
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

		chooser.setFileFilter(new FileNameExtensionFilter("JPG & PNG Images", "jpg", "PNG"));
 	    
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
			//ii.se
//			labelImageLeft.setPreferredSize(new Dimension(420, 300));
	
//			Image img = ii.getImage();
//			BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//			Graphics g = bi.createGraphics();
//			g.drawImage(img, 0, 0, 420, 300, null);
//			ImageIcon newIcon = new ImageIcon(bi);
			
			/*while (labelImageLeft.getSize().getWidth() < 420)
				labelImageLeft.setPreferredSize(new Dimension((int) labelImageLeft.getSize().getWidth() * 2, 
						(int) labelImageLeft.getSize().getHeight() * 2)); */

			labelImageLeft.setIcon(ii);
//			labelImageLeft.setIcon(newIcon);

			System.out.println("w=" + ii.getIconWidth() + ", h=" + ii.getIconHeight());
			
			//jifTree.pack();
			jifTree.getContentPane().doLayout();		
			jifTree.updateUI();		
			splitCenter.doLayout();		
			splitCenter.updateUI();
			panelLeft.updateUI();
			panelLeft.doLayout();
			
			this.strFile = strFile;
		}
		

		if (e.getSource() == butQueryTx) {
			if (comboTx.getSelectedItem().equals("Rescale"))
				exacuteScale();
			else if (comboTx.getSelectedItem().equals("Filter"))
				executeFilter();
		}

	}


	public void exacuteScale() {
		Scale2XExecutor executor = new Scale2XExecutor();
			Parameters parameters = new Parameters();
		parameters.setMainParameter(" -k 4 ");

			executor.execute(path + "/" + strFile, path + "/" + strFile +"-modified", parameters);
			
			System.out.println("execute has returned");
			
			ImageIcon iim = new ImageIcon(path + "/" + strFile+"-modified");
			
			System.out.println("w=" + iim.getIconWidth() + ", h=" + iim.getIconHeight());
			
			labelImageRight.setIcon(iim);

		//labelImageRight.setPreferredSize(new Dimension(iim.getIconWidth(), iim.getIconHeight()));
			
			jifTree.getContentPane().doLayout();		
			jifTree.updateUI();		
			splitCenter.doLayout();		
			splitCenter.updateUI();
			panelLeft.updateUI();
			panelLeft.doLayout();
		}

	public void executeFilter()
	{
		GMicExecutor executor = new GMicExecutor();
		Parameters parameters = new Parameters();
		//parameters.setMainParameter(" -rodilius ");
//		parameters.setMainParameter(" -texturize_paper ");
		parameters.setMainParameter("  -normalize_local 5  -glow 2  "-rotate 13 ");
		
		executor.execute(path + "/" + strFile, path + "/" + strFile +"-modified", parameters);
		
		System.out.println("execute has returned");
		
		ImageIcon iim = new ImageIcon(path + "/" + strFile+"-modified");
		
		System.out.println("w=" + iim.getIconWidth() + ", h=" + iim.getIconHeight());
		
		labelImageRight.setIcon(iim);
		
		//labelImageRight.setPreferredSize(new Dimension(iim.getIconWidth(), iim.getIconHeight()));
		
		jifTree.getContentPane().doLayout();		
		jifTree.updateUI();		
		splitCenter.doLayout();		
		splitCenter.updateUI();
		panelLeft.updateUI();
		panelLeft.doLayout();
	}
}
