package dk.aau.cs.d403.ast.statements;

import dk.aau.cs.d403.ast.Enums;
import dk.aau.cs.d403.ast.expressions.ObjectArgumentNode;

import java.util.ArrayList;

public class ObjectDeclarationNode extends DeclarationNode {
    private Enums.ClassType objectType;
    private String variableName;

    ArrayList<ObjectArgumentNode> objectArgumentNodes;

    public ObjectDeclarationNode(Enums.ClassType objectType, String variableName) {
        this.objectType = objectType;
        this.variableName = variableName;
    }

    public ObjectDeclarationNode(Enums.ClassType objectType, String variableName, ArrayList<ObjectArgumentNode> objectArgumentNodes) {
        this.objectType = objectType;
        this.variableName = variableName;
        this.objectArgumentNodes = objectArgumentNodes;
    }

    public Enums.ClassType getObjectType() {
        return objectType;
    }

    public String getVariableName() {
        return variableName;
    }

    public ArrayList<ObjectArgumentNode> getObjectArgumentNodes() {
        return objectArgumentNodes;
    }

    @Override
    public String prettyPrint() {
        return "Object Declaration";
    }
}