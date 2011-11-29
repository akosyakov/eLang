package ch.vorburger.el.engine;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.util.EContentAdapter;

public class ExpressionContext extends EContentAdapter {

	protected Map<String, Type> types = new HashMap<String, Type>();
	protected Map<String, Object> elements = new HashMap<String, Object>();
	
	public void putInstance(String name, Object instance) {
		Object mappedInstance = mapToExpressionType(instance);
		elements.put(name, mappedInstance);
		putType(name, mappedInstance.getClass());
	}

	public void putType(String name, Type type) {
		Type mappedType = mapToExpressionType(type);
		types.put(name, mappedType);
	}
	
	public Object getInstance(String name) {
		return elements.get(name);
	}
	
	public Type getType(String name) {
		return types.get(name);
	}
	
	public Iterable<String> getElementNames() {
		return elements.keySet();
	}
	
	public Iterable<Type> getDeclaredTypes() {
		return types.values();
	}

	private Object mapToExpressionType(Object instance) {
		if(instance instanceof Number) {
			return new BigDecimal(instance.toString());
		}
		return instance;
	}

	private Type mapToExpressionType(Type type) {
		if(Number.class.isAssignableFrom(type.getClass())) {
			return BigDecimal.class;
		}
		return type;
	}
}
