/*
 * KeysPerSecond: An open source input statistics displayer.
 * Copyright (C) 2017  Roan Hofland (roan@roanh.dev).  All rights reserved.
 * GitHub Repository: https://github.com/RoanH/KeysPerSecond
 *
 * KeysPerSecond is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KeysPerSecond is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dev.roanh.kps.ui.editor;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import dev.roanh.kps.Main;
import dev.roanh.kps.config.group.AveragePanelSettings;

/**
 * Editor for the average panel settings.
 * @author Roan
 * @see AveragePanelSettings
 */
public class AvgPanelEditor extends DataPanelEditor{
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -6191919120127582754L;

	/**
	 * Constructs a new average panel settings editor.
	 * @param config The settings to update.
	 * @param live If updates should be reflected in real time.
	 */
	public AvgPanelEditor(AveragePanelSettings config, boolean live){
		super(config, live);
		
		labels.add(new JLabel("精度:"));
		JComboBox<String> values = new JComboBox<String>(new String[]{
				"小数点后不显示数字",
				"小数点后显示1位数字",
				"小数点后显示2位数字",
				"小数点后显示3位数字"
		});
		fields.add(values);
		values.setSelectedIndex(config.getPrecision());
		values.addActionListener(e->{
			config.setPrecision(values.getSelectedIndex());
			if(live){
				Main.frame.repaint();
			}
		});
	}
}
