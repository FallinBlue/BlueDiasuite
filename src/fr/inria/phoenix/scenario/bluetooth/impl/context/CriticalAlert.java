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
    
    public CriticalAlert(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }
    
    
	protected List<Contact> getEmergencyContacts(int typeOfDiscover,Object discover) {
		Contact filter = new Contact();
		filter.setName("EmergencyCall");
		List<Contact> emergencyContacts = null;
		if (typeOfDiscover == 1)
			emergencyContacts = ((DiscoverForPushedFromButton) discover).addressBooks().anyOne().getContacts(filter);
		else	
			emergencyContacts = ( (DiscoverForTimerTriggeredFromTimer) discover).addressBooks().anyOne().getContacts(filter);
		return emergencyContacts;
	}


	@Override
	protected List<Contact> onPushedFromButton(PushedFromButton pushedFromButton,
			DiscoverForPushedFromButton discover) {
		List<Contact> emergencyContacts = getEmergencyContacts(1,discover);
		return emergencyContacts;

	}



	@Override
	protected List<Contact> onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer,
			DiscoverForTimerTriggeredFromTimer discover) {
		List<Contact> emergencyContacts = getEmergencyContacts(2,discover);

		return emergencyContacts;//emergencyContacts;;
	}
}
