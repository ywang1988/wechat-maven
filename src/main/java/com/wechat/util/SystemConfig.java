package com.wechat.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemConfig {
	private static Logger logger = Logger.getLogger(SystemConfig.class);
	private static Map<String, String> configs = new HashMap<String, String>();
	public static String AUTOREPLY_ROOT = "";
	public static String MENU_ROOT = "";

	public static void init() {
		InputStream in = null;
		try {
			logger.info("--------The system config begin init---------");
			Properties properties = new Properties();
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("system-config.properties");
			properties.load(in);
			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				logger.info(entry.getKey() + "=" + entry.getValue());
				configs.put((String) entry.getKey(), (String) entry.getValue());
			}
			properties.clear();

			String autoreply_path = Thread.currentThread().getContextClassLoader().getResource("autoreply.xml").getFile();
			String menu_path = Thread.currentThread().getContextClassLoader().getResource("menu.xml").getFile();
			try {
				autoreply_path = URLDecoder.decode(autoreply_path, "UTF-8");
				menu_path = URLDecoder.decode(menu_path, "UTF-8");
			} catch (UnsupportedEncodingException ex) {
				logger.error("decode the autoreply_path or menu_path failed:" + ex.getMessage(), ex);
				return;
			}

			if (autoreply_path != null) {
				autoreply_path = new File(autoreply_path).getPath();
			} else {
				logger.error("get autoreply_path root error");
				return;
			}

			if (menu_path != null) {
				menu_path = new File(menu_path).getPath();
			} else {
				logger.error("get menu_path root error");
				return;
			}
			logger.info("autoreply_path=" + autoreply_path);
			logger.info("menu_path=" + menu_path);

			AUTOREPLY_ROOT = autoreply_path;
			MENU_ROOT = menu_path;
			logger.info("--------The system config init success---------");
		} catch (IOException e) {
			logger.error("--------The system config init failure:" + e.getMessage() + "---------");
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static String getValue(String key , String def) {
		return configs.get(key) == null ? def : configs.get(key);
	}

	public static String getValue(String key) {
		return getValue(key, "");
	}

}
