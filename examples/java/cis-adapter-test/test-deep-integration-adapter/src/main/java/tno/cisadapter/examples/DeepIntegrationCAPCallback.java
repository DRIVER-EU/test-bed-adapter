package tno.cisadapter.examples;

import com.frequentis.cis.callback.AppCallback;
import com.frequentis.cis.core.payload.CISPayload;
import com.frequentis.cis.core.payload.objects.CISXMLContent;

public class DeepIntegrationCAPCallback implements AppCallback {

	/*
	 * This method handles the CIS Payload that is forwarded
	 * by the CISCore to this callback handler. In this example
	 * we simply print it out.
	 */
	public void msgReceived(CISPayload cisPayload) {
		CISXMLContent xmlContent = (CISXMLContent) cisPayload;
		System.out.println("CIS Payload Received: " + xmlContent.getEmbeddedXMLContent());
	}

	public void msgReceived(String cisMsg) {
		// This interface method is never called!
	}

}
