package de.itemis.xtext.showcases.cityplanning.validation;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;

import de.itemis.xtext.showcases.cityplanning.cityDsl.C_District;
import de.itemis.xtext.showcases.cityplanning.cityDsl.C_Element;
import de.itemis.xtext.showcases.cityplanning.cityDsl.C_Position;
import de.itemis.xtext.showcases.cityplanning.cityDsl.C_Street;
import de.itemis.xtext.showcases.cityplanning.cityDsl.CityDslPackage;
import de.itemis.xtext.showcases.cityplanning.util.Helper;
 

public class CityDslJavaValidator extends AbstractCityDslJavaValidator {
	@Check
	public void checkDistirctStartsWithCapital(C_District cDistrict) {
		if (!Character.isUpperCase(cDistrict.getName().charAt(0)))
			warning("Name should start with a capital.", CityDslPackage.Literals.CDISTRICT__NAME);
	}
	
	@Check
	public void checkStreetStartsWithCapital(C_Street cStreet) {
		if (!Character.isUpperCase(cStreet.getName().charAt(0)))
			warning("Name should start with a capital.", CityDslPackage.Literals.CELEMENT__NAME);
	}
	
	@Check
	private void checkPositionalContradictions(C_Element thisElement) {
		List<C_Element> cElements = Helper.getAllOfType(thisElement, C_Element.class);
		
		for (C_Element thatElement : cElements) {
			if(thisElement == thatElement)
				return;
			checkPositionalContradiction(thisElement, thatElement);
		}
	}
	
	private void checkPositionalContradiction(C_Element thisElement, C_Element thatElement) {
		C_Element thatElementOrParent = getThatOrParent(thatElement);	
		C_Position thisPosition = thisElement.getPosition();
		C_Position thatPosition = thatElementOrParent.getPosition();;
		
		if(thisPosition == null || thatPosition == null)
			return;
		
		if(thisPosition.getRef() == thatElement && thatPosition.getRef() == thisElement) {
			error("Position can only be declared on one side.", thisPosition.getRef(), CityDslPackage.Literals.CELEMENT__POSITION, 0);
			error("Position can only be declared on one side.", thatPosition.getRef(), CityDslPackage.Literals.CELEMENT__POSITION, 0);
		}
	}
	
	private C_Element getThatOrParent(C_Element thatElement) {
		EObject parent = thatElement.eContainer();
		if(parent instanceof C_Street)
			return (C_Element) parent;
		
		return thatElement;
	}
}
