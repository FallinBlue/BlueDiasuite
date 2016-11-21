package fr.inria.phoenix.scenario.bluetooth.impl;
        
import fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AbstractAlertSuspectRegulation;
import fr.inria.phoenix.diasuite.framework.context.criticalalert.AbstractCriticalAlert;
import fr.inria.phoenix.diasuite.framework.context.wrongalert.AbstractWrongAlert;
import fr.inria.phoenix.diasuite.framework.controller.criticalalertcontroller.AbstractCriticalAlertController;
import fr.inria.phoenix.diasuite.framework.controller.razcontroller.AbstractRAZController;
import fr.inria.phoenix.diasuite.framework.controller.suspiciousalertcontroller.AbstractSuspiciousAlertController;
import fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder;
import fr.inria.phoenix.scenario.bluetooth.impl.context.AlertSuspectRegulation;
import fr.inria.phoenix.scenario.bluetooth.impl.context.CriticalAlert;
import fr.inria.phoenix.scenario.bluetooth.impl.context.WrongAlert;
import fr.inria.phoenix.scenario.bluetooth.impl.controller.CriticalAlertController;
import fr.inria.phoenix.scenario.bluetooth.impl.controller.RAZController;
import fr.inria.phoenix.scenario.bluetooth.impl.controller.SuspiciousAlertController;

/* (non-Javadoc)
 * The binder to provides the various components of the application
 * @see fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder
 */
public class ComponentBinder extends AppComponentBinder {

	@Override
	public Class<? extends AbstractAlertSuspectRegulation> getAlertSuspectRegulationClass() {
		// TODO Auto-generated method stub
		return AlertSuspectRegulation.class;
	}

	@Override
	public Class<? extends AbstractCriticalAlert> getCriticalAlertClass() {
		// TODO Auto-generated method stub
		return CriticalAlert.class;
	}

	@Override
	public Class<? extends AbstractWrongAlert> getWrongAlertClass() {
		// TODO Auto-generated method stub
		return WrongAlert.class;
	}

	@Override
	public Class<? extends AbstractCriticalAlertController> getCriticalAlertControllerClass() {
		// TODO Auto-generated method stub
		return CriticalAlertController.class;
	}

	@Override
	public Class<? extends AbstractRAZController> getRAZControllerClass() {
		// TODO Auto-generated method stub
		return RAZController.class;
	}

	@Override
	public Class<? extends AbstractSuspiciousAlertController> getSuspiciousAlertControllerClass() {
		// TODO Auto-generated method stub
		return SuspiciousAlertController.class;
	}
}
