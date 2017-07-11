import org.junit.Before;
import org.junit.Test;
import org.opentravel.ota.BaseInvCountType;
import org.opentravel.ota.OTAHotelInvCountNotifRQ;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.*;

public class TestInventory {

	private JAXBContext jaxbContext;
	private Unmarshaller unmarshaller;
	private Marshaller marshaller;

	@Before
	public void setup() throws Exception {
		this.jaxbContext = JAXBContext.newInstance("org.opentravel.ota");
		this.unmarshaller = jaxbContext.createUnmarshaller();
		this.marshaller = jaxbContext.createMarshaller();
	}

	@Test
	public void testUnmarshal() throws Exception {

		final String sampleXML =
				"<OTA_HotelInvCountNotifRQ TimeStamp=\"2015-07-01T11:25:15\" xmlns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
						"  <Inventories HotelCode=\"10001\" HotelName=\"Test Hotel\">\n" +
						"    <Inventory>\n" +
						"      <StatusApplicationControl AllInvCode=\"true\" Start=\"2015-01-01\" End=\"2015-01-31\" />\n" +
						"      <InvCounts>\n" +
						"        <InvCount CountType=\"1\" Count=\"10\" />\n" +
						"      </InvCounts>\n" +
						"    </Inventory>\n" +
						"    <Inventory>\n" +
						"      <StatusApplicationControl AllInvCode=\"true\" Start=\"2015-02-01\" End=\"2015-02-28\" />\n" +
						"      <InvCounts>\n" +
						"        <InvCount CountType=\"1\" Count=\"8\" />\n" +
						"      </InvCounts>\n" +
						"    </Inventory>\n" +
						"  </Inventories>\n" +
						"</OTA_HotelInvCountNotifRQ>";
		final OTAHotelInvCountNotifRQ o =
				(OTAHotelInvCountNotifRQ) this.unmarshaller.unmarshal(new StringReader(sampleXML));

		assertNotNull("Inventories can not be null", o.getInventories());
		assertNotNull("Inventorries can not be empty", o.getInventories().getInventory());
		assertSame("Number of inventory should be 2", 2, o.getInventories().getInventory().size());

		//checks for first inventory count
		{
			final BaseInvCountType inv = o.getInventories().getInventory().get(0);
			assertSame("all inventory should be true", true,
					inv.getStatusApplicationControl().isAllInvCode());
			assertEquals("start date must match", "2015-01-01", inv.getStatusApplicationControl().getStart());
			assertEquals("end date must match", "2015-01-31", inv.getStatusApplicationControl().getEnd());
			assertNotNull("InvCounts can not be null", inv.getInvCounts());
			assertNotNull("InvCount can not be emppty", inv.getInvCounts().getInvCount());
			assertEquals("InvCount must be 1", 1, inv.getInvCounts().getInvCount().size());
			assertEquals("InvCount count should be 8", 10,
					inv.getInvCounts().getInvCount().get(0).getCount().intValue());
		}

		//checks for second inventory count
		{
			final BaseInvCountType inv = o.getInventories().getInventory().get(1);
			assertSame("all inventory should be true", true,
					inv.getStatusApplicationControl().isAllInvCode());
			assertEquals("start date must match", "2015-02-01", inv.getStatusApplicationControl().getStart());
			assertEquals("end date must match", "2015-02-28", inv.getStatusApplicationControl().getEnd());
			assertNotNull("InvCounts can not be null", inv.getInvCounts());
			assertNotNull("InvCount can not be emppty", inv.getInvCounts().getInvCount());
			assertEquals("InvCount must be 1", 1, inv.getInvCounts().getInvCount().size());
			assertEquals("InvCount count should be 8", 8,
					inv.getInvCounts().getInvCount().get(0).getCount().intValue());
		}
	}
}
