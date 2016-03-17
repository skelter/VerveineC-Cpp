package eu.synectique.verveine.extractor.c;

import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IBinding;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTNamedTypeSpecifier;

import eu.synectique.verveine.core.gen.famix.Method;
import eu.synectique.verveine.core.gen.famix.StructuralEntity;
import eu.synectique.verveine.core.gen.famix.Type;

/** Specialized visitor for parameter declaration of a FunctionDeclarator
 * This simplifies visiting IASTDeclSpecifier (type of parameters) and IASTDeclarator (parameter itself)
 * @author anquetil
 */
public class ParamDeclVisitor extends VerveineVisitor {

	/** the FamixMethod for which we are visiting the IASTParameterDeclaration
	 */
	private Method fmxMth;

	/** (Famix)Type of the parameter
	 */
	private Type paramType;

	/** Name of the parameter
	 */
	private String name;

	/** constructor, receives the FamixMethod for which we are visiting the IASTParameterDeclaration 
	 */
	public ParamDeclVisitor(CppDictionary dico, Method fmx) {
		super(dico);
		this.fmxMth = fmx;
	}

	// VISITING METHODS

	
	/*
	 * 
			// creating the method's parameters
			// unless classSummary is true in which case we might need to create References between classes
			List<VariableDeclaration> paramAsVarList;
			for (SingleVariableDeclaration param : (List<SingleVariableDeclaration>)node.parameters()) {
				// Note: method and ParamTyp bindings are null for ParameterType :-(
				paramAsVarList = new ArrayList<VariableDeclaration>(1);
				paramAsVarList.add(param);

				eu.synectique.verveine.core.gen.famix.Type varTyp = referedType(param.getType(), fmx, false);
				visitVariablesDeclarations(node, varTyp, paramAsVarList, fmx);
			}
*/	
	
	@Override
	public int visit(IASTParameterDeclaration node) {
		traceup("### IASTParameterDeclaration ");
		return super.visit(node);
	}

	@Override
	public int leave(IASTParameterDeclaration node) {
		dico.ensureFamixParameter((IBinding)null, name, paramType, fmxMth, /*persistIt*/true);
		return super.visit(node);
	}

	@Override
	public int visit(IASTDeclSpecifier node) {
		if (node instanceof CPPASTNamedTypeSpecifier) {
			
		}
		return super.visit(node);
	}

	@Override
	public int visit(IASTDeclarator node) {
		name = node.getName().toString();
		return super.visit(node);
	}

}
