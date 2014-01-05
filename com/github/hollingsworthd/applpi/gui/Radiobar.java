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
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import com.github.hollingsworthd.applpi.gui.Chan;

public class Radiobar extends JToolBar {
	private static final long serialVersionUID = 1L;
	private static final double DEFAULT_ROOT = 440;
	//private static double root = DEFAULT_ROOT / 16;
	private Composition[] compositions;
	private Thread thread = null;
	private boolean isPlaying = false;
	private int whichChan = 0;
	protected JRadioButton rdMoon;
	protected JRadioButton rdStout;
	protected JRadioButton rdCrisp;
	protected JRadioButton rdFlakes;
	protected JRadioButton rdLoops;
	protected JRadioButton rdBeer;
	protected JRadioButton rdCharm;
	protected JRadioButton rdCastle;
	protected JRadioButton rdRock;
	protected JRadioButton rdWicks;
	protected JRadioButton rdAle;
	protected JRadioButton rdCrunch;
	protected ButtonGroup rabtnGroup;
	protected JSpinner rootSpinner;
	protected JButton play;

	public Radiobar() {
		setBounds( 0, 0, 500, 160 );
		setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setLayout(new FlowLayout() );//GridLayout(3, 6, 0, 0));

		addRadios();
		addCompositions();

		/*Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut_2);
		add(horizontalStrut);*/
		
		play = new JButton(new AbstractAction("Play") {
			private static final long serialVersionUID = -761L;
			public void actionPerformed(ActionEvent e) {
				compositions[currChan()].setRoot( adjSpinner() );
				togglePlay();
			}
		});
		add(play);

		rootSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_ROOT, 1,
				20000000, 1));
		rootSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				compositions[currChan()].setRoot( adjSpinner() );
			}
		});
		add(rootSpinner);
	}

	private void addRadios()
	{
		rdMoon = new JRadioButton("\u2648", true);
		rdStout = new JRadioButton("\u2649");
		rdCrisp = new JRadioButton("\u264A");
		rdFlakes = new JRadioButton("\u264B"); // these are unicode
		rdLoops = new JRadioButton("\u264C"); // astrological signs
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

		rabtnGroup = new ButtonGroup(); // bind radios
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
	}

	private void addCompositions() // not the most optimized way :|
	{
		compositions  = new Composition[ Chan.CRUNC.i + 1 ]; // assuming it is the last one
		compositions[ Chan.ALE.i ] = new SummerAle();
		compositions[ Chan.BEER.i ] = new Guinness();
		compositions[ Chan.CASTL.i ] = new Newcastle();
		compositions[ Chan.CHARM.i ] = new LuckyCharms();
		compositions[ Chan.CRISP.i ] = new CookieCrisp();
		compositions[ Chan.CRUNC.i ] = new ToastCrunch();
		compositions[ Chan.FLAKE.i ] = new FrostedFlakes();
		compositions[ Chan.LOOPS.i ] = new FruitLoops();
		compositions[ Chan.MOON.i ] = new BlueMoon();
		compositions[ Chan.ROCK.i ] = new RollingRock();
		compositions[ Chan.STOUT.i ] = new ChocolateStout();
		compositions[ Chan.WICKS.i ] = new Smithwicks();
	}

	public synchronized boolean isPlaying() {
		return isPlaying;
	}

	public synchronized void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public synchronized int currChan() {
		if( rdMoon.isSelected() ) // these are indicies.
			{ return Chan.MOON.i; }		// perhaps make an enum?
		else if( rdStout.isSelected() )
			{ return Chan.STOUT.i; }
		else if( rdCrisp.isSelected() )
			{ return Chan.CRISP.i; }
		else if( rdFlakes.isSelected() )
			{ return Chan.FLAKE.i; }
		else if( rdLoops.isSelected() )
			{ return Chan.LOOPS.i; }
		else if( rdBeer.isSelected() )
			{ return Chan.BEER.i; }
		else if( rdCharm.isSelected() )
			{ return Chan.CHARM.i; }
		else if( rdCastle.isSelected() )
			{ return Chan.CASTL.i; }
		else if( rdRock.isSelected() )
			{ return Chan.ROCK.i; }
		else if( rdWicks.isSelected() )
			{ return Chan.WICKS.i; }
		else if( rdAle.isSelected() )
			{ return Chan.ALE.i; }
		else //( rdCrunch.isSelected() )
			{ return Chan.CRUNC.i; }
	}

	private double adjSpinner()
	{
		return (Double) rootSpinner.getValue() / 16d;
	}

	public void close() {
		if (isPlaying()) {
			togglePlay();
		}
	}

	private void togglePlay() {
		if (isPlaying()) {
			compositions[ whichChan ].stopPlaying();
			whichChan = currChan();
			setPlaying(false);
			play.setText( "Play" );
		} else {
			try {
				if (thread != null) {
					thread.join();
				}
			} catch (InterruptedException e1) {
				Thread.yield( ); // back of the thread queue for you
			}
			whichChan = currChan();
			compositions[ whichChan ].startPlaying();
			thread = new Thread( new Play(compositions[ currChan() ]) );
			setPlaying(true);
			thread.start();
			play.setText( "Stop" );
		}
	}
}
