package dk.aau.cs.d403.ast;

public class FunctionDeclarationNode implements ASTnode {
    private String returnType;
    private String functionName;

    public FunctionDeclarationNode(String functionName, String returnType) {
        this.functionName = functionName;
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();

        sb.append(returnType);
        sb.append(" ");
        sb.append(functionName);
        sb.append("() {\n");

        //for each declaration / function

        sb.append("\n}");

        return sb.toString();
    }
}
