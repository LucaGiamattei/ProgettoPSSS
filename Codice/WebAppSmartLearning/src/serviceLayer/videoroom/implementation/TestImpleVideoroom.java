package serviceLayer.videoroom.implementation;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestImpleVideoroom {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testVerifyFasciaOrariaIsInProgress() {
		System.out.println(LocalTime.now(ZoneId.of("Europe/Rome")));
	}

}
