package dk.aau.cs.d403.ast.structure;

import dk.aau.cs.d403.ast.ASTnode;
import dk.aau.cs.d403.ast.statements.StatementNode;

import java.util.ArrayList;

public class BlockNode implements ASTnode {
    private ArrayList<StatementNode> statementNodes;

    public BlockNode(ArrayList<StatementNode> statementNodes) {
        this.statementNodes = statementNodes;
    }

    public ArrayList<StatementNode> getStatementNodes() {
        return statementNodes;
    }

    @Override
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (StatementNode stmnt : statementNodes)
        {
            if (stmnt != null) {
                sb.append("\n\t");
                sb.append(stmnt.prettyPrint());
            }
        }

        sb.append("\n}");

        return sb.toString();
    }
}