package com.systemtray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Toolkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class SystemtrayApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(SystemtrayApplication.class, args);
	}

	@Bean
	public TrayIcon trayIcon(ConfigurableApplicationContext context) throws AWTException {
		if (!SystemTray.isSupported())
			return null;

		Image image = Toolkit.getDefaultToolkit().getImage(
				SystemtrayApplication.class.getResource("/sup.png"));

		PopupMenu popup = new PopupMenu();
		MenuItem exit = new MenuItem("Exit");

		TrayIcon icon = new TrayIcon(image, "Sup!", popup);

		exit.addActionListener(e -> {
			SystemTray.getSystemTray().remove(icon);
			context.close();
		});
		popup.add(exit);

		icon.setImageAutoSize(true);
		SystemTray.getSystemTray().add(icon);
		return icon;
	}
}
