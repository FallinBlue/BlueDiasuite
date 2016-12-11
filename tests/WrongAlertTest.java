import static fr.inria.phoenix.diasuite.framework.mocks.Mock.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.phoenix.diasuite.framework.datatype.state.State;
import fr.inria.phoenix.diasuite.framework.mocks.MotionDetectorMock;
import fr.inria.phoenix.diasuite.framework.mocks.NotifierMock;
import fr.inria.phoenix.diasuite.framework.mocks.TimerMock;
import fr.inria.phoenix.scenario.bluetooth.impl.ComponentBinder;



public class WrongAlertTest {
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
			 
			 // initialize mock devices...
			 MotionDetectorMock motionDetector1 = mockMotionDetector("motionDetector1","cuisine","Remi") ;
			 MotionDetectorMock motionDetector2 = mockMotionDetector("motionDetector2","chambre","Remi") ;
			 MotionDetectorMock motionDetector3 = mockMotionDetector("motionDetector3","toilettes","Remi") ;
			 MotionDetectorMock motionDetector4 = mockMotionDetector("motionDetector4","douche","Remi") ;

			 NotifierMock notifier = mockNotifier("Notif5") ;

			 TimerMock timer = mockTimer("Timer11") ;

			 // set attributes...
			 State state1 = new State("active","2016-11-21 15:33:42.555","batteryHigh");
			 State state2 = new State("active","2016-11-21 15:35:43.505","batteryMedium");
			 State state3 = new State("active","2016-11-19 15:35:43.505","batteryMedium");
			 State state4 = new State("active","2016-11-20 15:35:43.505","batteryMedium");

			 motionDetector1.setMotion(state1);
			 motionDetector2.setMotion(state2);
			 motionDetector3.setMotion(state3);
			 motionDetector4.setMotion(state4);
		 
			 String IdTimer = "AlertTimerFall007";

  			 // notifier
			 String IdNotification = "AlertNotificationFall007";
			 
			 
			 int test = 1;
			 
			 

			 ///  test wrong alert 1
			 if (test == 1) {
				 notifier.reply(2, "");
				 IdTimer = "AlertTimerFall007";
				 assertTrue(timer.expectCancel(IdTimer));

				 IdNotification = "AlertNotificationFall007";
				 assertTrue(notifier.expectCancelNonCriticalNotification(IdNotification)); // already disabled
			 }
			 ///  test wrong alert 2 -> should do nothing
			 else if (test == 2) {
				 notifier.reply(3, "");
				 IdTimer = "AlertTimerFall007";
				 assertFalse(timer.expectCancel(IdTimer));

				 IdNotification = "AlertNotificationFall007";
				 assertFalse(notifier.expectCancelNonCriticalNotification(IdNotification));
			 }
		 }

}
