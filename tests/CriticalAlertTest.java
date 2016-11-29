import static fr.inria.phoenix.diasuite.framework.mocks.Mock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import fr.inria.phoenix.diasuite.framework.datatype.contact.Contact;
import fr.inria.phoenix.diasuite.framework.datatype.noncriticalnotification.NonCriticalNotification;
import fr.inria.phoenix.diasuite.framework.datatype.state.State;
import fr.inria.phoenix.diasuite.framework.mocks.AddressBookMock;
import fr.inria.phoenix.diasuite.framework.mocks.ButtonMock;
import fr.inria.phoenix.diasuite.framework.mocks.CommunicationServiceMock;
import fr.inria.phoenix.diasuite.framework.mocks.InputMock;
import fr.inria.phoenix.diasuite.framework.mocks.MotionDetectorMock;
import fr.inria.phoenix.diasuite.framework.mocks.NotifierMock;
import fr.inria.phoenix.diasuite.framework.mocks.TimerMock;
import fr.inria.phoenix.scenario.bluetooth.impl.ComponentBinder;



public class CriticalAlertTest {
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

		InputMock input = mockInput("smartphone13","chez soi","Guillaume") ;
		ButtonMock button = mockButton("bouton12","chez soi","Clément") ;
		NotifierMock notifier = mockNotifier("Notif5") ;

		TimerMock timer = mockTimer("Timer11") ;
		AddressBookMock addressBook = mockAddressBook("comService1","Me") ;

		List<Contact> newContactsValue = new ArrayList<Contact>();
		Contact aideSoignante = new Contact("aideSoignante","tata@toto","0300000000", "0700000000", null, null);			
		Contact maSoeur = new Contact("maSoeur","tete@tyty","0300000001", "0700000001", null, null);
		Contact medecin = new Contact("medecin","titi@tutu","0300000002", "0700000002", null, null);
		newContactsValue.add(aideSoignante);
		newContactsValue.add(maSoeur);
		newContactsValue.add(medecin);

		Contact filter = new Contact();
		filter.setName("EmergencyCall");

		addressBook.setContacts(newContactsValue, filter);
		
		//add someone else with other filter
//		Contact bibi = new Contact("bibi","tt@tt","0300000003", "0700000003", null, null);
//		filter.setName("Friend");
//		newContactsValue.clear();
//		newContactsValue.add(bibi);
//		addressBook.setContacts(newContactsValue, filter);
		
		
		CommunicationServiceMock communicationService = mockCommunicationService("comService1") ;

		// set attributes...
		State state1 = new State("active","2016-11-21 15:33:42.555","batteryHigh");
		State state2 = new State("active","2016-11-21 15:35:43.505","batteryMedium");
		State state3 = new State("active","2016-11-19 15:35:43.505","batteryMedium");
		State state4 = new State("active","2016-11-20 15:35:43.505","batteryMedium");

		motionDetector1.setMotion(state1);
		motionDetector2.setMotion(state2);
		motionDetector3.setMotion(state3);
		motionDetector4.setMotion(state4);

		input.message("lieDown");
		String IdTimer = "AlertTimerFall007";
		Integer delayMs = 4*60*1000; // 4 min
		timer.expectSchedule(IdTimer, delayMs); // voir si le message est bien envoyé par la bonne personne

		// notifier
		String IdNotification = "AlertNotificationFall007";
		List<String> answers = new ArrayList<String>();
		answers.add("Oui appeler les secours");
		answers.add("Non tout va bien");
		NonCriticalNotification notification = new NonCriticalNotification(IdNotification,"Fall Alert","Avez-vous chuté ?",answers,false);

		notifier.expectSendNonCriticalNotification(notification);


		String title = "[EMERGENCY DIASUITE]";
		String content = "My Name has probably fallen. Please call here or help her.";

		int test = 1 ;

		///  test critical Alert 1
		if (test == 1) {
			button.pushed(true);
			IdTimer = "AlertTimerFall007";
			assertTrue(timer.expectCancel(IdTimer));
			System.out.println(filter);


			assertTrue(communicationService.expectSendTrustedMessage(aideSoignante, title, content));

		}



		///  test critical Alert 3
		if (test == 2) { 
			notifier.reply(2, ""); // wrong reply
			IdTimer = "AlertTimerFall007";
			assertFalse(communicationService.expectSendTrustedMessage(maSoeur, title, content));
		}


		///  test critical Alert 4 fin du timer /// COMMENT TESTER ???
		if (test == 3) { 

			IdTimer = "AlertTimerFall007";
			assertTrue(communicationService.expectSendTrustedMessage(medecin, title, content));			 }

	}

}
