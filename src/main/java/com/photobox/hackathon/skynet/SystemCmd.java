package com.photobox.hackathon.skynet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SystemCmd {

	private final static Logger logger = LoggerFactory.getLogger(SystemCmd.class);

	public static String run(String cmd, String logDescription) throws Exception {

		Process process = null;
		BufferedReader input = null;
		BufferedReader error = null;
		int returnProcess = 0;
		String line;
		StringBuilder stringBuilder = new StringBuilder();

		logger.debug(logDescription + " - running command: " + cmd);

		try {
			process = Runtime.getRuntime().exec(cmd);
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = input.readLine()) != null) {
				stringBuilder.append(line).append(System.getProperty("line.separator"));
			}
			error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = error.readLine()) != null) {
				stringBuilder.append(line).append(System.getProperty("line.separator"));
			}
			returnProcess = process.waitFor();

			if (returnProcess == 0) {
				logger.debug(logDescription + " - command completed successfully");
			} else {
				throw new Exception(logDescription + " - command failed with error code "
						+ returnProcess + ", console output: " +
						System.getProperty("line.separator") + stringBuilder.toString());
			}
		} catch (Exception e) {
			logger.error(logDescription + " - command failed with error code " +
					returnProcess + ", console output: " +
					System.getProperty("line.separator") + stringBuilder.toString());
			throw e;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// nop
				}
			}
			if (error != null) {
				try {
					error.close();
				} catch (IOException e) {
					// nop
				}
			}
			if (process != null) {
				OutputStream oustream = process.getOutputStream();
				if (oustream != null) {
					try {
						oustream.close();
					} catch (IOException e) {
						// nop
					}
					oustream = null;
				}
				InputStream errorStream = process.getErrorStream();
				if (errorStream != null) {
					try {
						errorStream.close();
					} catch (IOException e) {
						// nop
					}
					errorStream = null;
				}
			}
			process = null;
		}

		return stringBuilder.toString();
	}

}
