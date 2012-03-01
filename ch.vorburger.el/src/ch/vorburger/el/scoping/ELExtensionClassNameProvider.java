package ch.vorburger.el.scoping;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import org.eclipse.xtext.xbase.scoping.featurecalls.StaticImplicitMethodsFeatureForTypeProvider.ExtensionClassNameProvider;

import ch.vorburger.el.lib.DateExtensions;
import ch.vorburger.el.lib.DecimalExtensions;

import com.google.common.collect.Multimap;

@SuppressWarnings("restriction")
public class ELExtensionClassNameProvider extends ExtensionClassNameProvider {

	@Override
	protected Multimap<Class<?>, Class<?>> simpleComputeExtensionClasses() {
		Multimap<Class<?>, Class<?>> result = super.simpleComputeExtensionClasses();

		// adding our own extensions here
		result.put(BigDecimal.class, DecimalExtensions.class);
		result.put(GregorianCalendar.class, DateExtensions.class);
		return result;
	}

}
