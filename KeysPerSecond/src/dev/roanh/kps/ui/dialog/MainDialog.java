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
package dev.roanh.kps.ui.dialog;

import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import dev.roanh.kps.Main;
import dev.roanh.kps.config.ConfigLoader;
import dev.roanh.kps.config.Configuration;
import dev.roanh.util.ClickableLink;
import dev.roanh.util.Dialog;
import dev.roanh.util.Util;

/**
 * Main configuration dialog shown on first launch.
 * @author Roan
 */
public class MainDialog extends JPanel{
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -2620857098469751291L;
	/**
	 * The configuration being created.
	 */
	private Configuration config = new Configuration();
	/**
	 * Panel with check box options.
	 */
	private CheckBoxPanel options = new CheckBoxPanel();
	
	/**
	 * Constructs a new main dialog.
	 */
	public MainDialog(){
		super(new BorderLayout());
		options.syncBoxes();
		
		add(buildLeftPanel(), BorderLayout.CENTER);
		add(buildRightPanel(), BorderLayout.LINE_END);
		add(buildBottomPanel(), BorderLayout.PAGE_END);
	}
	
	/**
	 * Builds the bottom GUI panel that shows links and version information.
	 * @return The constructed bottom GUI panel.
	 */
	private JPanel buildBottomPanel(){
		JLabel forum = new JLabel("<html><font color=blue><u>论坛</u></font> -</html>", SwingConstants.RIGHT);
		forum.addMouseListener(new ClickableLink("https://osu.ppy.sh/community/forums/topics/552405"));

		JLabel git = new JLabel("<html>- <font color=blue><u>GitHub</u></font></html>", SwingConstants.LEFT);
		git.addMouseListener(new ClickableLink("https://github.com/RoanH/KeysPerSecond"));

		JPanel links = new JPanel(new GridLayout(1, 2, -2, 0));
		links.add(forum);
		links.add(git);

		JPanel translatorPanel = new JPanel(new GridLayout(1, 1));
		JLabel translatorLabel = new JLabel("<html><center>汉化: <font color=blue><u>null极光</u></font> (B站)</center></html>", SwingConstants.CENTER);
		translatorLabel.addMouseListener(new ClickableLink("https://space.bilibili.com/317115491"));
		translatorPanel.add(translatorLabel);


//		info.add(Util.getVersionLabel("KeysPerSecond", Main.VERSION.toString()));
		JPanel info = new JPanel(new GridLayout(3, 1, 0, 2));
		JLabel versionLabel = new JLabel();
		versionLabel.setHorizontalAlignment(JLabel.CENTER);
		info.add(versionLabel);
		info.add(links);
		info.add(translatorPanel);
		Util.checkVersion("RoanH", "KeysPerSecond", result -> {
			String latestVersion = result.orElse("未知");
			String text = "<html><center><i>版本: " + Main.VERSION.toString() + ", 最新版本: " + latestVersion + "</i></center></html>";
			versionLabel.setText(text);
		});
		versionLabel.setText("<html><center><i>版本: " + Main.VERSION.toString() + ", 最新版本: <font color='gray'>检查中</font></i></center></html>");
		return info;
	}
	
	/**
	 * Builds the left GUI panel that shows general info, main configuration and check boxes.
	 * @return The constructed left GUI panel.
	 * @see #options
	 */
	private JPanel buildLeftPanel(){
		//info
		JLabel info = new JLabel("<html><body style='width:210px'>您可以在本屏幕上配置程序，或在程序可见后使用<b>右键单击</b>菜单实时查看更改效果。</body></html>");
		info.setBorder(BorderFactory.createTitledBorder("信息"));
		
		//main configuration
		JPanel main = new JPanel(new GridLayout(2, 1));
		main.setBorder(BorderFactory.createTitledBorder("主要配置"));

		JButton keys = new JButton("配置按键和鼠标按钮");
		main.add(keys);
		keys.addActionListener(e->KeysDialog.configureKeys(config, false));
		
		JButton layout = new JButton("配置布局、图表和面板");
		main.add(layout);
		layout.addActionListener(e->LayoutDialog.configureLayout(config, false));

		//left panel
		JPanel left = new JPanel(new BorderLayout());
		left.add(info, BorderLayout.PAGE_START);
		left.add(main, BorderLayout.CENTER);
		left.add(options, BorderLayout.PAGE_END);
		return left;
	}
	
	/**
	 * Builds the right GUI panel shows config saving/loading, settings and about.
	 * @return The constructed right GUI panel.
	 */
	private JPanel buildRightPanel(){
		//configuration
		JPanel configuration = new JPanel(new GridLayout(3, 1));
		configuration.setBorder(BorderFactory.createTitledBorder("配置"));
		
		JButton load = new JButton("加载配置");
		configuration.add(load);
		load.addActionListener(e->{
			Configuration toLoad = ConfigLoader.loadConfiguration();
			if(toLoad != null){
				config = toLoad;
				options.syncBoxes();
			}
		});
		
		JButton save = new JButton("保存配置");
		configuration.add(save);
		save.addActionListener(e->config.saveConfig(false));
		
		JButton defConf = new JButton("默认配置");
		configuration.add(defConf);
		defConf.addActionListener(e->DefaultConfigDialog.showDefaultConfigDialog());
		
		//settings
		JPanel settings = new JPanel(new GridLayout(4, 1));
		settings.setBorder(BorderFactory.createTitledBorder("设置"));
		
		JButton updaterate = new JButton("更新频率");
		settings.add(updaterate);
		updaterate.addActionListener(e->UpdateRateDialog.configureUpdateRate(config));
		
		JButton color = new JButton("颜色");
		settings.add(color);
		color.addActionListener(e->ColorDialog.configureColors(config.getTheme(), false));
		
		JButton autoSave = new JButton("统计保存");
		settings.add(autoSave);
		autoSave.addActionListener(e->StatsSavingDialog.configureStatsSaving(config.getStatsSavingSettings(), false));
		
		JButton cmdkeys = new JButton("命令");
		settings.add(cmdkeys);
		cmdkeys.addActionListener(e->CommandKeysDialog.configureCommandKeys(config.getCommands()));
		
		//about
		JPanel aboutPanel = new JPanel(new BorderLayout());
		aboutPanel.setBorder(BorderFactory.createTitledBorder("帮助"));
		JButton about = new JButton("关于");
		aboutPanel.add(about);
		about.addActionListener(e->AboutDialog.showAbout());
		
		//right panel
		JPanel right = new JPanel(new BorderLayout());
		right.add(configuration, BorderLayout.PAGE_START);
		right.add(settings, BorderLayout.CENTER);
		right.add(aboutPanel, BorderLayout.PAGE_END);
		return right;
	}
	
	/**
	 * Panel with check box style options.
	 * @author Roan
	 */
	private class CheckBoxPanel extends JPanel{
		/**
		 * Serial ID.
		 */
		private static final long serialVersionUID = 7814497194364064857L;
		/**
		 * Whether overlay mode is enabled.
		 */
		private final JCheckBox overlay = new JCheckBox();
		/**
		 * Whether all keys are tracked.
		 */
		private final JCheckBox allKeys = new JCheckBox();
		/**
		 * Whether all buttons are tracked.
		 */
		private final JCheckBox allButtons = new JCheckBox();
		/**
		 * Whether key-modifier tracking is enabled.
		 */
		private final JCheckBox modifiers = new JCheckBox();
		/**
		 * Whether windowed mode is enabled.
		 */
		private final JCheckBox windowed = new JCheckBox();
		
		/**
		 * Constructs a new check box panel.
		 */
		private CheckBoxPanel(){
			super(new BorderLayout());
			setBorder(BorderFactory.createTitledBorder("选项"));
			
			JPanel labels = new JPanel(new GridLayout(5, 1));
			labels.add(new JLabel("置顶模式: "));
			labels.add(new JLabel("跟踪所有按键: "));
			labels.add(new JLabel("跟踪所有鼠标按钮: "));
			labels.add(new JLabel("跟踪按键修饰符: "));
			labels.add(new JLabel("窗口模式:"));
			add(labels, BorderLayout.CENTER);
			
			JPanel boxes = new JPanel(new GridLayout(5, 1));
			boxes.add(overlay);
			boxes.add(allKeys);
			boxes.add(allButtons);
			boxes.add(modifiers);
			boxes.add(windowed);
			add(boxes, BorderLayout.LINE_END);
			
			overlay.addActionListener(e->config.setOverlayMode(overlay.isSelected()));
			allKeys.addActionListener(e->config.setTrackAllKeys(allKeys.isSelected()));
			allButtons.addActionListener(e->config.setTrackAllButtons(allButtons.isSelected()));
			modifiers.addActionListener(e->config.setKeyModifierTrackingEnabled(modifiers.isSelected()));
			windowed.addActionListener(e->config.setWindowedMode(windowed.isSelected()));
		}
		
		/**
		 * Syncs all check boxes with current configuration setting.
		 */
		private void syncBoxes(){
			overlay.setSelected(config.isOverlayMode());
			allKeys.setSelected(config.isTrackAllKeys());
			allButtons.setSelected(config.isTrackAllButtons());
			modifiers.setSelected(config.isKeyModifierTrackingEnabled());
			windowed.setSelected(config.isWindowedMode());
		}
	}
	
	/**
	 * Shows the initial configuration dialog for the program.
	 * @return The configuration created by the user.
	 */
	public static final Configuration configure(){
		CountDownLatch latch = new CountDownLatch(1);
		MainDialog content = new MainDialog();
		JPanel bottomButtons = new JPanel();

		JButton ok = new JButton("确定");
		bottomButtons.add(ok);
		ok.addActionListener(e->{
			if(content.config.isValid()){
				latch.countDown();
			}else{
				Dialog.showDialog("请确保您的布局至少有一个面板可以显示。",new String[]{"确定"});
			}
		});
		
		JButton exit = new JButton("退出");
		bottomButtons.add(exit);
		exit.addActionListener(e->System.exit(0));
		
		JPanel dialog = new JPanel(new BorderLayout());
		dialog.add(content, BorderLayout.CENTER);
		dialog.add(bottomButtons, BorderLayout.PAGE_END);
		dialog.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JFrame conf = new JFrame("KeysPerSecond");
		conf.add(dialog);
		conf.pack();
		conf.setResizable(false);
		conf.setLocationRelativeTo(null);
		conf.setIconImages(Arrays.asList(Main.icon, Main.iconSmall));
		conf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		conf.setVisible(true);
		
		try{
			latch.await();
		}catch(InterruptedException e1){
		}
		
		conf.setVisible(false);
		conf.dispose();
		return content.config;
	}
}
