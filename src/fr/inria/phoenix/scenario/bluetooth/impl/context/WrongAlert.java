package fr.inria.phoenix.scenario.bluetooth.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert;
import fr.inria.phoenix.diasuite.framework.device.notifier.ReplyFromNotifier;

/* (non-Javadoc)
 * The implementation of the WrongAlert context
 * @see fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert
 */
public class WrongAlert extends AbstractWrongAlert {
	
	/**
     * A constructor that instantiate the class WrongAlert
     */
    public WrongAlert(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /**
     * This method is called when a <code>Notifier</code> device on which we have subscribed publish on its <code>reply</code> source.
    <p>
    to change
    
    <pre>
    when provided reply  from Notifier
     *      maybe publish;
    </pre>
     * 
     * @param replyFromNotifier the value of the <code>reply</code> source and the <code>Notifier</code> device that published the value.
     * @return a {@link WrongAlertValuePublishable} that says if the context should publish a value and which value it should publish
     */
	@Override
	protected WrongAlertValuePublishable onReplyFromNotifier(ReplyFromNotifier replyFromNotifier) {
		if (replyFromNotifier.value() == 2) {// if response OK (people has not fallen or has rised after a fall
			return new WrongAlertValuePublishable(2,true); // go to the controller of wrong alert
		}
		return new WrongAlertValuePublishable(0,false); // nothing happened in the wrong alert part
	}
    

	
}
