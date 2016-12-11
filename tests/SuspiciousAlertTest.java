import static fr.inria.phoenix.diasuite.framework.mocks.Mock.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.phoenix.diasuite.framework.datatype.noncriticalnotification.NonCriticalNotification;
import fr.inria.phoenix.diasuite.framework.mocks.InputMock;
import fr.inria.phoenix.diasuite.framework.mocks.NotifierMock;
import fr.inria.phoenix.diasuite.framework.mocks.PresenceDetectorMock;
import fr.inria.phoenix.diasuite.framework.mocks.TimerMock;
import fr.inria.phoenix.scenario.bluetooth.impl.ComponentBinder;



public class SuspiciousAlertTest {
	@Before
	 public void setUp() throws Exception {
		 underTest(ComponentBinder.class);
		 }
		 @After
		 public void tearDown() throws Exception {
		 shutdown();
		 }

		 @Test
		 public void test() {
			 

			 PresenceDetectorMock presenceDetector = mockPresenceDetector("presenceDetector");
			 presenceDetector.setInBedroom(false);

			 
			 InputMock input = mockInput("smartphone13","chez soi","Guillaume") ;
			 NotifierMock notifier = mockNotifier("Notif5") ;
			 TimerMock timer = mockTimer("Timer11") ;
			 
			 input.message("Une chute a eu lieu");
			 String IdTimer = "AlertTimerFall007";
			 Integer delayMs = 4*60*1000; // 4 min
			 assertTrue(timer.expectSchedule(IdTimer, delayMs)); // voir si le message est bien envoy� par la bonne personne
			
			 // notifier
			 String IdNotification = "AlertNotificationFall007";
			 List<String> answers = new ArrayList<String>();
			 answers.add("Oui appeler les secours");
			 answers.add("Non tout va bien");
			 NonCriticalNotification notification = new NonCriticalNotification(IdNotification,"Fall Alert","Avez-vous chut� ?",answers,false);

			 assertTrue(notifier.expectSendNonCriticalNotification(notification));

		 
		 }

}
