package fr.inria.phoenix.scenario.bluetooth.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert;
import fr.inria.phoenix.diasuite.framework.device.devicebutton.PushedFromDeviceButton;
import fr.inria.phoenix.diasuite.framework.device.notifier.NotificationResponseFromNotifier;

/* (non-Javadoc)
 * The implementation of the WrongAlert context
 * @see fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert
 */
public class WrongAlert extends AbstractWrongAlert {
    
    public WrongAlert(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }
    
    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert#onNotificationResponseFromNotifier(NotificationResponseFromNotifier, DiscoverForNotificationResponseFromNotifier)
     */
    @Override
    protected java.lang.Boolean onNotificationResponseFromNotifier(NotificationResponseFromNotifier notificationResponseFromNotifier, DiscoverForNotificationResponseFromNotifier discover) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert#onPushedFromDeviceButton(PushedFromDeviceButton, DiscoverForPushedFromDeviceButton)
     */
    @Override
    protected java.lang.Boolean onPushedFromDeviceButton(PushedFromDeviceButton pushedFromDeviceButton, DiscoverForPushedFromDeviceButton discover) {
        // TODO Auto-generated method stub
        return null;
    }
}
