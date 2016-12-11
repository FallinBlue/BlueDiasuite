package fr.inria.phoenix.scenario.bluetooth.impl.context;

import java.util.List;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.criticalalert.AbstractCriticalAlert;
import fr.inria.phoenix.diasuite.framework.datatype.contact.Contact;
import fr.inria.phoenix.diasuite.framework.device.button.PushedFromButton;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;

/* (non-Javadoc)
 * The implementation of the CriticalAlert context
 * @see fr.inria.phoenix.diasuite.framework.context.criticalalert.AbstractCriticalAlert
 */
public class CriticalAlert extends AbstractCriticalAlert {
	
	/**
     * A constructor that instantiate the class CriticalAlert
     */
    public CriticalAlert(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }
    
    /**
     * Returns the list of contact for the controller
    <pre>
    emergencyContacts as List<Contact>
    </pre>
    @return the list of emergencyContacts
     */
	protected List<Contact> getEmergencyContacts(int typeOfDiscover,Object discover) {
		Contact filter = new Contact(); // create a null filter as initialized by my part
		List<Contact> emergencyContacts = null; // initialize the list of contact
		if (typeOfDiscover == 1) // depending of the class of discover used
			emergencyContacts = ((DiscoverForPushedFromButton) discover).addressBooks().anyOne().getContacts(filter);
		else	
			emergencyContacts = ( (DiscoverForTimerTriggeredFromTimer) discover).addressBooks().anyOne().getContacts(filter);
		return emergencyContacts;
	}

    /**
     * This method is called when a <code>Button</code> device on which we have subscribed publish on its <code>pushed</code> source.
     *
    <pre>
    when provided pushed from Button 
     *      	get contacts from AddressBook, timerTriggered from Timer
     *      	maybe publish;
    </pre>
     * 
     * @param pushedFromButton the value of the <code>pushed</code> source and the <code>Button</code> device that published the value.
     * @param discover a discover object to get value from devices and contexts
     * @return a {@link CriticalAlertValuePublishable} that says if the context should publish a value and which value it should publish
     */
	@Override
	protected CriticalAlertValuePublishable onPushedFromButton(PushedFromButton pushedFromButton,
			DiscoverForPushedFromButton discover) {
		
		List<Contact> emergencyContacts = getEmergencyContacts(1,discover);			// Get the list of contacts
		if (pushedFromButton.value()==true) { 										// Test if the button has been pushed before
			return new CriticalAlertValuePublishable(emergencyContacts,true);		// Go to the controller and give it the list of contacts
		}
		else {
			return new CriticalAlertValuePublishable(emergencyContacts,false);		// no button pushed
		}

	}


    /**
     * This method is called when a <code>Timer</code> device on which we have subscribed publish on its <code>timerTriggered</code> source.
     *     
    <pre>
    when provided timerTriggered from Timer	
     * 		get contacts from AddressBook
     *       	maybe publish;
    </pre>
     * 
     * @param timerTriggeredFromTimer the value of the <code>timerTriggered</code> source and the <code>Timer</code> device that published the value.
     * @param discover a discover object to get value from devices and contexts
     * @return a {@link CriticalAlertValuePublishable} that says if the context should publish a value and which value it should publish
     */
	@Override
	protected CriticalAlertValuePublishable onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer,
			DiscoverForTimerTriggeredFromTimer discover) {
		List<Contact> emergencyContacts = getEmergencyContacts(2,discover); // get the list of contact

		if (timerTriggeredFromTimer.value().equals("AlertTimerFall007")) { // test the id of the timerTriggered
			return new CriticalAlertValuePublishable(emergencyContacts,true); // if this is the one triggered before -> go to the controller and pass the list of contacts as parameter
		}
		else {
			return new CriticalAlertValuePublishable(emergencyContacts,false); // not the good id => No critical alert

		}
	

	}
}
