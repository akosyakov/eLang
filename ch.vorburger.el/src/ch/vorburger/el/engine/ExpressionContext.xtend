package ch.vorburger.el.engine

import org.eclipse.emf.ecore.util.EContentAdapter
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.emf.common.notify.Notifier
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.EcoreUtil2

/**
 * <p>An {@link ExpressionContext} provides variables that should be available during the expression
 * parsing and/or execution.</p>
 * 
 * <p>These variables are put into the context as {@link JvmType}s.</p>
 * 
 * <p>Furthermore, the valid type of an expression can be constrained. 
 * This constraint is already checked at parsing time, not merely during execution.</p>
 */
class ExpressionContext extends EContentAdapter {

	@Property JvmTypeReference type

	val ResourceSet resourceSet
	val variables = <String, JvmTypeReference>newHashMap

	new(Notifier context) {
		resourceSet = EcoreUtil2.getResourceSet(context)
	}

	def getResourceSet() {
		return resourceSet
	}

	def addVariable(String name, JvmTypeReference type) {
		variables.put(name, type)
	}

	def getVariableNames() {
		variables.keySet
	}

	def getVariableType(String name) {
		variables.get(name)
	}

	def removeVariable(String name) {
		variables.remove(name)
	}

	def removeVariables() {
		variables.clear
	}

	override isAdapterForType(Object type) {
		type == ExpressionContext
	}

}
