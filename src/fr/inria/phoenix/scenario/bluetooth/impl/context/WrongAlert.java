package fr.inria.phoenix.scenario.bluetooth.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert;

import fr.inria.phoenix.diasuite.framework.device.notifier.ReplyFromNotifier;

/* (non-Javadoc)
 * The implementation of the WrongAlert context
 * @see fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert
 */
public class WrongAlert extends AbstractWrongAlert {
    
    public WrongAlert(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

	@Override
	protected WrongAlertValuePublishable onReplyFromNotifier(ReplyFromNotifier replyFromNotifier) {
		// TODO Auto-generated method stub
		if (replyFromNotifier.value() == 2) // si ça va
			return new WrongAlertValuePublishable(2,true);
	return new WrongAlertValuePublishable(0,false);
	}
    

	
}
