package tno.cisadapter.examples;

import static spark.Spark.post;

import com.frequentis.cis.connector.CISToolConnectorImpl;
import com.frequentis.cis.exception.config.CISConfigException;

/*
 * This example shows how to connect to the CIS via the REST Connector.
 * This means you need to run the CIS Adapter Core Standalone AS WELL!
 * 
 * One configuration file is used here:
 * 
 * config.properties contains the URL (hostname and port) of the CIS Adapter Core Standalone REST endpoint.
 */
public class RestAdapterExample {

	public static void main(String[] args) throws CISConfigException {
		/*
		 * This CISToolConnectorImpl implementation comes from the
		 * CISConnectorRestTemplate (see pom.xml for dependencies) which
		 * connects to the CIS Core via REST. This means that CIS Core
		 * configuration must be done where you are running the CIS Core.
		 */
		CISToolConnectorImpl connector = new CISToolConnectorImpl();

		/*
		 * Send a message to the CIS core. The connector will forward the the
		 * message to the CIS Core via REST.
		 */
		connector.notify("CAP", exampleCAPmessage, null, false);

		/*
		 * Simple REST endpoint that allows posting the received messages XML
		 * data This example uses Spark (see http://sparkjava.com/) but you are
		 * free to use whatever implementation you like.
		 * 
		 * IMPORTANT: this endpoint must end with 'xml' and it must be
		 * configured at the Adapter Core Standalone under the
		 * 'application.callback.rest.endpoint' property. In this case it should
		 * be configured to
		 * 'core.connector.rest.callback=http://localhost:4567/test-res-
		 * endpoint'
		 */
		post("/test-res-endpoint/xml", (req, res) -> {
			receiveMessage(req.body());
			res.body("Object was succesfully distributed!");
			res.status(200);
			return res;
		});
	}

	private static void receiveMessage(String msg) {
		System.out.println(msg);
	}

	// Example valid CAP 1.2 message taken from
	// https://cap-validator.appspot.com/validate#r
	private static String exampleCAPmessage = "<alert xmlns = \"urn:oasis:names:tc:emergency:cap:1.2\"> \r\n"
			+ " <identifier>KSTO1055887203</identifier>   \r\n" + " <sender>KSTO@NWS.NOAA.GOV</sender>   \r\n"
			+ " <sent>2003-06-17T14:57:00-07:00</sent> \r\n" + " <status>Actual</status>   \r\n"
			+ " <msgType>Alert</msgType> \r\n" + " <scope>Public</scope>   \r\n" + " <info> \r\n"
			+ "   <category>Met</category>     \r\n" + "   <event>SEVERE THUNDERSTORM</event>  \r\n"
			+ "   <responseType>Shelter</responseType>   \r\n" + "   <urgency>Immediate</urgency>     \r\n"
			+ "   <severity>Severe</severity>     \r\n" + "   <certainty>Observed</certainty> \r\n"
			+ "   <eventCode> \r\n" + "     <valueName>SAME</valueName> \r\n" + "     <value>SVR</value> \r\n"
			+ "   </eventCode> \r\n" + "   <expires>2003-06-17T16:00:00-07:00</expires>   \r\n"
			+ "   <senderName>NATIONAL WEATHER SERVICE SACRAMENTO CA</senderName> \r\n"
			+ "   <headline>SEVERE THUNDERSTORM WARNING</headline> \r\n"
			+ "   <description> AT 254 PM PDT...NATIONAL WEATHER SERVICE DOPPLER RADAR INDICATED A SEVERE \r\n"
			+ "THUNDERSTORM OVER SOUTH CENTRAL ALPINE COUNTY...OR ABOUT 18 MILES SOUTHEAST OF KIRKWOOD...MOVING \r\n"
			+ "SOUTHWEST AT 5 MPH. HAIL...INTENSE RAIN AND STRONG DAMAGING WINDS ARE LIKELY WITH THIS \r\n"
			+ "STORM.</description> \r\n"
			+ "   <instruction>TAKE COVER IN A SUBSTANTIAL SHELTER UNTIL THE STORM PASSES.</instruction> \r\n"
			+ "   <contact>BARUFFALDI/JUSKIE</contact> \r\n" + "   <area>         \r\n"
			+ "     <areaDesc>EXTREME NORTH CENTRAL TUOLUMNE COUNTY IN CALIFORNIA, EXTREME NORTHEASTERN \r\n"
			+ "CALAVERAS COUNTY IN CALIFORNIA, SOUTHWESTERN ALPINE COUNTY IN CALIFORNIA</areaDesc> \r\n"
			+ "     <polygon>38.47,-120.14 38.34,-119.95 38.52,-119.74 38.62,-119.89 38.47,-120.14</polygon> \r\n"
			+ "     <geocode> \r\n" + "       <valueName>SAME</valueName> \r\n" + "       <value>006109</value> \r\n"
			+ "     </geocode> \r\n" + "     <geocode> \r\n" + "       <valueName>SAME</valueName> \r\n"
			+ "       <value>006009</value> \r\n" + "     </geocode> \r\n" + "     <geocode> \r\n"
			+ "       <valueName>SAME</valueName> \r\n" + "       <value>006003</value> \r\n" + "     </geocode> \r\n"
			+ "   </area>  \r\n" + " </info> \r\n" + "</alert>";

}
