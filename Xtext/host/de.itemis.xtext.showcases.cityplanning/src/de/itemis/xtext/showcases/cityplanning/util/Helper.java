package de.itemis.xtext.showcases.cityplanning.util;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;

public class Helper {
	public static <T extends EObject> List<T> getAllOfType(EObject ctx, Class<T> type) {
		List<T> result;
		
		EObject root = EcoreUtil2.getRootContainer(ctx);
		result = EcoreUtil2.getAllContentsOfType(root, type);
		
		return result;
	}
}
