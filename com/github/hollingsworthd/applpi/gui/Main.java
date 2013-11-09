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

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JPanel {
	private static final long serialVersionUID = 1L;
	private Radiobar toolbar;

	public Main(){
		Radiobar toolbar = new Radiobar();
        add(toolbar);
		setBounds( 100, 100, 520, 148 );
		setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
    }
    
    public void stop(){
    	toolbar.close();
    }
}
