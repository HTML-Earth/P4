package dk.aau.cs.d403.ast.statements;

import dk.aau.cs.d403.ast.ASTnode;
import dk.aau.cs.d403.ast.CodePosition;
import dk.aau.cs.d403.ast.expressions.ObjectArgumentNodePlural;

public class ObjectContructorNode implements ASTnode {
    private ObjectArgumentNodePlural objectArgumentNodePlural;
    private NonObjectFunctionCallNode nonObjectFunctionCallNode;
    private ObjectFunctionCallNode objectFunctionCallNode;
    private String objectVariableName;

    public ObjectContructorNode(ObjectArgumentNodePlural objectArgumentNodePlural) {
        this.objectArgumentNodePlural = objectArgumentNodePlural;
    }

    public ObjectContructorNode(NonObjectFunctionCallNode nonObjectFunctionCallNode) {
        this.nonObjectFunctionCallNode = nonObjectFunctionCallNode;
    }

    public ObjectContructorNode(ObjectFunctionCallNode objectFunctionCallNode) {
        this.objectFunctionCallNode = objectFunctionCallNode;
    }

    public ObjectContructorNode(String objectVariableName) {
        this.objectVariableName = objectVariableName;
    }

    public ObjectArgumentNodePlural getObjectArgumentNodePlural() {
        return objectArgumentNodePlural;
    }

    public NonObjectFunctionCallNode getNonObjectFunctionCallNode() {
        return nonObjectFunctionCallNode;
    }

    public ObjectFunctionCallNode getObjectFunctionCallNode() {
        return objectFunctionCallNode;
    }

    public String getObjectVariableName() {
        return objectVariableName;
    }

    @Override
    public String prettyPrint(int indent) {
        if (objectArgumentNodePlural != null)
            return objectArgumentNodePlural.prettyPrint(indent);
        else if (nonObjectFunctionCallNode != null)
            return nonObjectFunctionCallNode.prettyPrint(indent);
        else if (objectFunctionCallNode != null)
            return objectFunctionCallNode.prettyPrint(indent);
        else if (objectVariableName != null)
            return objectVariableName;
        else
            return "Invalid Object constructor";
    }

    private CodePosition codePosition;

    @Override
    public void setCodePosition(CodePosition codePosition) {
        this.codePosition = codePosition;
    }

    @Override
    public CodePosition getCodePosition() {
        return codePosition;
    }
}
