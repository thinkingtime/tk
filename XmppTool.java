package com.xmpp.client.util;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * @author Sam.Io
 * @time 2011/11/18
 * @project AdXmpp
 */
public class XmppTool {

	public static XMPPConnection con = null;
	private final static String server_ip = "172.25.83.99";
	private final static int server_port = 5222;
	/**
	 * 
	 */
	private static void openConnection() {
		new Thread() {
			public void run() {
				System.out.println("currentThread="+ Thread.currentThread().getId());
				try {
					ConnectionConfiguration connConfig = new ConnectionConfiguration(server_ip, server_port);
					// connConfig.setCompressionEnabled(true);
					connConfig.setSASLAuthenticationEnabled(true);
					con = new XMPPConnection(connConfig);
					// con = new XMPPConnection("172.25.83.99");
					con.connect();
				} catch (XMPPException xe) {
					xe.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * @return
	 */
	public static XMPPConnection getConnection() {
		if (null == con) {
			openConnection();
		}
		return con;
	}

	/**
	 * 
	 */
	public static void closeConnection() {
		if (null != con) {
			con.disconnect();
			con = null;
		}
	}
	
}
