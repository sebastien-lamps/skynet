/**
 * $Licence$
 */
package com.photobox.hackathon.skynet;

import javax.xml.ws.WebFault;

@WebFault(name = "TechnicalException", faultBean = "RuntimeServiceFault")
public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = -3780384935286275768L;


	/**
	 * Constructor of TechnicalException
	 *
	 * @param msg
	 * @param cause
	 */
	public TechnicalException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Constructor of TechnicalException
	 *
	 * @param msg
	 */
	public TechnicalException(String msg) {
		super(msg);
	}

}
