package com.kuaiba.site.service.utils;

import org.slf4j.Logger;

import com.google.common.base.Throwables;

public class LogUtils {
	
	public static void info(Logger logger, String message) {
		try {
			logger.debug(message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void info(Logger logger, Throwable e) {
		try {
			logger.debug(Throwables.getStackTraceAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void info(Logger logger, Throwable e, String message) {
		try {
			logger.info(message);
			logger.info(Throwables.getStackTraceAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void warn(Logger logger, String message) {
		try {
			logger.debug(message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void warn(Logger logger, Throwable e) {
		try {
			logger.debug(Throwables.getStackTraceAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void warn(Logger logger, Throwable e, String message) {
		try {
			logger.warn(message);
			logger.warn(Throwables.getStackTraceAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void debug(Logger logger, String message) {
		try {
			logger.debug(message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void debug(Logger logger, Throwable e) {
		try {
			logger.debug(Throwables.getStackTraceAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void debug(Logger logger, Throwable e, String message) {
		try {
			logger.debug(message);
			logger.debug(Throwables.getStackTraceAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void error(Logger logger, String message) {
		try {
			logger.debug(message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void error(Logger logger, Throwable e) {
		try {
			logger.debug(Throwables.getStackTraceAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void error(Logger logger, Throwable e, String message) {
		try {
			logger.error(message);
			logger.error(Throwables.getStackTraceAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
