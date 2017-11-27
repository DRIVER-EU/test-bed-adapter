package tno.cisadapter.examples;

import com.frequentis.cis.connector.CISToolConnectorImpl;
import com.frequentis.cis.exception.CISException;

/*
 * This Deep Integration Example uses three configuration files (see config folder):
 * 
 * config.properties contains configuration for the CISCore (see documentation on the CommonInformationSpace Github)
 * application.properties specifies the port to be used by the CISCore Standalone
 * edxlde_parameters.properties specifies data to be put in the EDXL-DE wrapper by the CISCore
 */
public class DeepIntegrationExample {

	public static void main(String[] args) throws CISException {
		/*
		 * The CISToolConnectorImpl used here is from the CISConnectorTemplate
		 * artifact (see pom.xml for dependencies). The CISConnectorTemplate is
		 * a simple connector performing deep integration with the CISCore that
		 * handles some conversions for you out of the box. It also RUNS the
		 * CISCore, which is why the configuration for the CISCore must be
		 * included.
		 */
		CISToolConnectorImpl connector = new CISToolConnectorImpl();

		/*
		 * Register our very simple callback handler for CAP type messages. This
		 * means that the CISCore will now forward all CAP type messages to our
		 * callback handler.
		 */
		connector.registerForMsg("CAP", null, new DeepIntegrationCAPCallback());

		/*
		 * Publish our example CAP message on the CIS. The connector will
		 * forward the message to the CISCore which handles distribution on the
		 * CIS.
		 */
		connector.notify("CAP", exampleCAPmessage, null, false);
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
