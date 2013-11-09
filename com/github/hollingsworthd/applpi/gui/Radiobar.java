//This file is part of ApplPi.
//
//ApplPi is a stochastic noise machine.
//Personal website: http://danielhollingsworth.com
//Project page: https://github.com/hollingsworthd/ApplPi
//Copyright (C) 2009-2013 Daniel Hollingsworth
//
//ApplPi is free software: you can redistribute it and/or modify
//it under the terms of the GNU Affero General Public License as
//published by the Free Software Foundation, either version 3 of the
//License, or (at your option) any later version.
//
//ApplPi is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU Affero General Public License for more details.
//
//You should have received a copy of the GNU Affero General Public License
//along with ApplPi.  If not, see <http://www.gnu.org/licenses/>.

package com.github.hollingsworthd.applpi.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.hollingsworthd.applpi.composition.BlueMoon;
import com.github.hollingsworthd.applpi.composition.ChocolateStout;
import com.github.hollingsworthd.applpi.composition.CookieCrisp;
import com.github.hollingsworthd.applpi.composition.FrostedFlakes;
import com.github.hollingsworthd.applpi.composition.FruitLoops;
import com.github.hollingsworthd.applpi.composition.Guinness;
import com.github.hollingsworthd.applpi.composition.LuckyCharms;
import com.github.hollingsworthd.applpi.composition.Newcastle;
import com.github.hollingsworthd.applpi.composition.RollingRock;
import com.github.hollingsworthd.applpi.composition.Smithwicks;
import com.github.hollingsworthd.applpi.composition.SummerAle;
import com.github.hollingsworthd.applpi.composition.ToastCrunch;
import com.github.hollingsworthd.applpi.core.Composition;
import com.github.hollingsworthd.applpi.core.Play;

public class Radiobar extends JToolBar {
	private static final long serialVersionUID = 1L;
	private static final double DEFAULT_ROOT = 440;
	private static double root = DEFAULT_ROOT / 16;
	private Composition[] compositions = new Composition[] { new LuckyCharms(),
			new FruitLoops(), new FrostedFlakes(), new CookieCrisp(),
			new ToastCrunch(), new SummerAle(), new Newcastle(),
			new Guinness(), new ChocolateStout(), new BlueMoon(),
			new RollingRock(), new Smithwicks(), };
	//private int curComp = 0;
	private Thread thread = null;
	private boolean isPlaying = false;
	//private JPanel controls;
	JRadioButton rdMoon;
	JRadioButton rdStout;
	JRadioButton rdCrisp;
	JRadioButton rdFlakes;
	JRadioButton rdLoops;
	JRadioButton rdBeer;
	JRadioButton rdCharm;
	JRadioButton rdCastle;
	JRadioButton rdRock;
	JRadioButton rdWicks;
	JRadioButton rdAle;
	JRadioButton rdCrunch;
	JSpinner rootSpinner;

	public synchronized boolean isPlaying() {
		return isPlaying;
	}

	public synchronized void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public synchronized int getCurComp() {
		if( rdMoon.isSelected() )
			{ return 0; }
		else if( rdStout.isSelected() )
			{ return 1; }
		else if( rdCrisp.isSelected() )
			{ return 2; }
		else if( rdFlakes.isSelected() )
			{ return 3; }
		else if( rdLoops.isSelected() )
			{ return 4; }
		else if( rdBeer.isSelected() )
			{ return 5; }
		else if( rdCharm.isSelected() )
			{ return 6; }
		else if( rdCastle.isSelected() )
			{ return 7; }
		else if( rdRock.isSelected() )
			{ return 8; }
		else if( rdWicks.isSelected() )
			{ return 9; }
		else if( rdAle.isSelected() )
			{ return 10; }
		else //if( rdCrunch.isSelected() )
			{ return 11; }
	}

	public void close() {
		if (isPlaying()) {
			togglePlay();
		}
	}

	private void togglePlay() {
		if (isPlaying()) {
			compositions[getCurComp()].stopPlaying();
			setPlaying(false);
		} else {
			try {
				if (thread != null) {
					thread.join();
				}
			} catch (InterruptedException e1) {
				// do nothing
			}
			compositions[getCurComp()].startPlaying();
			thread = new Thread(new Play(compositions[getCurComp()]));
			setPlaying(true);
			thread.start();
		}
		//play.setSelected(isPlaying());
	}

	public Radiobar() {
		setBounds( 100, 100, 520, 148 );
		//controls = new JPanel();
		setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		//controls.setContentPane( );
		//JButton play = new JButton("ab");
		//controls.add(play);
		//controls.setTitle("ApplPi#3.1 sourceforge.net/projects/applpi (c) 2009-2013 Daniel Hollingsworth");
		setLayout(new GridLayout(3, 6, 0, 0));

		ButtonGroup rabtnGroup; // bind radios
		rdMoon = new JRadioButton("\u2648", true);
		rdStout = new JRadioButton("\u2649");
		rdCrisp = new JRadioButton("\u264A");
		rdFlakes = new JRadioButton("\u264B");
		rdLoops = new JRadioButton("\u264C");
		rdBeer = new JRadioButton("\u264D");
		rdCharm = new JRadioButton("\u264E");
		rdCastle = new JRadioButton("\u264F");
		rdRock = new JRadioButton("\u2650");
		rdWicks = new JRadioButton("\u2651");
		rdAle = new JRadioButton("\u2652");
		rdCrunch = new JRadioButton("\u2653");
		add(rdMoon);
		add(rdStout);
		add(rdCrisp);
		add(rdFlakes);
		add(rdLoops);
		add(rdBeer);
		add(rdCharm);
		add(rdCastle);
		add(rdRock);
		add(rdWicks);
		add(rdAle);
		add(rdCrunch);

		rabtnGroup = new ButtonGroup();
		rabtnGroup.add( rdMoon );
		rabtnGroup.add( rdStout );
		rabtnGroup.add( rdCrisp );
		rabtnGroup.add( rdFlakes );
		rabtnGroup.add( rdLoops );
		rabtnGroup.add( rdBeer );
		rabtnGroup.add( rdCharm );
		rabtnGroup.add( rdCastle );
		rabtnGroup.add( rdRock );
		rabtnGroup.add( rdWicks );
		rabtnGroup.add( rdAle );
		rabtnGroup.add( rdCrunch );

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut_2);
		add(horizontalStrut);

		JButton play = new JButton(new AbstractAction("Go") {
			public void actionPerformed(ActionEvent e) {
				compositions[getCurComp()].setRoot((Double) rootSpinner.getValue() / 16d);
				togglePlay();
			}
		});
		add(play);

		rootSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_ROOT, 1,
				20000000, 1));
		rootSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				compositions[getCurComp()].setRoot((Double)
						rootSpinner.getValue() / 16d);
			}
		});
		add(rootSpinner);
	}
/*
		JButton addTrack = new JButton(new AbstractAction("+") {
			public void actionPerformed(ActionEvent e) {
				Tracks.instance().addTrack();
			}
		});
*/
}
