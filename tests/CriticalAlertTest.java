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
import fr.inria.phoenix.diasuite.framework.mocks.AddressBookMock;
import fr.inria.phoenix.diasuite.framework.mocks.ButtonMock;
import fr.inria.phoenix.diasuite.framework.mocks.CommunicationServiceMock;
import fr.inria.phoenix.diasuite.framework.mocks.InputMock;
import fr.inria.phoenix.diasuite.framework.mocks.NotifierMock;
import fr.inria.phoenix.diasuite.framework.mocks.PresenceDetectorMock;
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


		InputMock input = mockInput("smartphone13","chez soi","Guillaume") ; // mock representation of the input (message transmitted through the websocket and bluetooth emitted by the smartphone)
		ButtonMock button = mockButton("bouton12","chez soi","Clément") ; // button on the smarphone for emitting the call
		NotifierMock notifier = mockNotifier("Notif5") ; // mock Notifier on the principal tablet
		TimerMock timer = mockTimer("AlertTimerFall007") ; // mock timer initialization 
		AddressBookMock addressBook = mockAddressBook("comService1","Me") ; // mock address book initialization 
		CommunicationServiceMock communicationService = mockCommunicationService("comService1") ; // mock communicationService used for sending SMS initialization
		PresenceDetectorMock presenceDetector = mockPresenceDetector("presenceDetector"); // initialize presenceDetector mock

		List<Contact> newContactsValue = new ArrayList<Contact>(); // initialize the list of contact to add
        List<String> groups_friend = new ArrayList<String>(); // initialize a list of groups that will contain friends
        groups_friend.add("friends"); // add friends' group to this list
        List<String> groups_emergency = new ArrayList<String>(); // initialize a list of groups that will contain emergencyCall
        groups_emergency.add("emergencyCall"); // add emergencyCall' group to this list

        // create contacts (3 in group emergency and one in friends
		Contact aideSoignante = new Contact("aideSoignante","tata@toto","0300000000", "0700000000", null, groups_emergency);			
		Contact maSoeur = new Contact("Germaine","tete@tyty","0300000001", "0700000001", null, groups_emergency);
		Contact medecin = new Contact("medecin","titi@tutu","0300000002", "0700000002", null, groups_emergency);
		Contact josette = new Contact("Josette","jos@ette","0300000003", "0700000003", null, groups_friend);

		// add the contacts to the list that will be added to the addressBook
		newContactsValue.add(aideSoignante);
		newContactsValue.add(maSoeur);
		newContactsValue.add(medecin);
		newContactsValue.add(josette);

		Contact filter = new Contact();
		addressBook.setContacts(newContactsValue, filter); // add contacts to the addressBook
		
		
		presenceDetector.setInBedroom(false); // set the inBedroom property to false (not in the room)

		input.message("Une chute a eu lieu"); // send the event to the suspicious context of the fall
		String IdTimer = "AlertTimerFall007";
		Integer delayMs = 4*60*1000; // 4 min
		timer.expectSchedule(IdTimer, delayMs); // test if the timer has been schedules

		String IdNotification = "AlertNotificationFall007"; 		// notification construction
		List<String> answers = new ArrayList<String>();
		answers.add("Oui appeler les secours");
		answers.add("Non tout va bien");
		NonCriticalNotification notification = new NonCriticalNotification(IdNotification,"Fall Alert","Avez-vous chut� ?",answers,false);
		
		notifier.expectSendNonCriticalNotification(notification); // test if the notification has weel be triggered

		String title = "[EMERGENCY DIASUITE]"; // write the title of the sms
		String content = "My Name has probably fallen. Please call here or help her."; // write the content of the sms

		int test = 1 ; // test number

		///  test critical Alert 1
		if (test == 1) {
			button.pushed(true);
			IdTimer = "AlertTimerFall007";
			assertTrue(timer.expectCancel(IdTimer));
			System.out.println(filter);


			assertTrue(communicationService.expectSendTrustedMessage(aideSoignante, title, content));
			//assertFalse(communicationService.expectSendTrustedMessage(josette, title, content));

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
