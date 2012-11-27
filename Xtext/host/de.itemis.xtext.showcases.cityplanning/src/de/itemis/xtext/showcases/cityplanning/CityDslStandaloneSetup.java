
package de.itemis.xtext.showcases.cityplanning;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class CityDslStandaloneSetup extends CityDslStandaloneSetupGenerated{

	public static void doSetup() {
		new CityDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

