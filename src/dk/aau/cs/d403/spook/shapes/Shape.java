package dk.aau.cs.d403.spook.shapes;

import dk.aau.cs.d403.ast.expressions.AtomPrecedenceNode;
import dk.aau.cs.d403.ast.expressions.HighPrecedenceNode;
import dk.aau.cs.d403.spook.Vector4;
import dk.aau.cs.d403.spook.color.Colorable;
import dk.aau.cs.d403.spook.SpookObject;

public abstract class Shape extends SpookObject implements Colorable {
    protected Vector4 color;

    @Override
    public Vector4 getColor() {
        return color;
    }

    @Override
    public void setColor(Vector4 color) {
        this.color = color;
    }

    public abstract String getCheckCall(String spaceName);

    public static String getRotationFunctionSignature2D() {
        return "mat2 rotate2D(float angle)";
    }

    public static String getRotationFunctionBody2D() {
        return "float rad = -angle * PI / 180.0;\n\treturn mat2(cos(rad), -sin(rad), sin(rad), cos(rad));";
    }

    public static String getScaleFunctionSignature2D() {
        return "mat2 scale2D(vec2 scale)";
    }

    public static String getScaleFunctionBody2D() {
        return "return mat2(1.0/scale.x, 0.0, 0.0, 1.0/scale.y);";
    }

    protected String getColorApplication() {
        //IF ALPHA IS FULL (1)
        if (color.getW().getLowPrecedence().getHighPrecedenceNodes().size() == 1) {
            HighPrecedenceNode highPrecedenceNode = color.getW().getLowPrecedence().getHighPrecedenceNodes().get(0);
            if (highPrecedenceNode.getAtomPrecedenceNodes().size() == 1) {
                AtomPrecedenceNode atomPrecedenceNode = highPrecedenceNode.getAtomPrecedenceNodes().get(0);
                if (atomPrecedenceNode.getOperator() == null) {
                    if (atomPrecedenceNode.getOperand().getRealNumberNode() != null) {
                        if (atomPrecedenceNode.getOperand().getRealNumberNode().getNumber() == 1) {
                            return " {\n\t\t" +
                                    "fragColor = " + name + ".color;\n\t" +
                                    "}";
                        }
                    }
                }
            }
        }

        //IF ALPHA CAN BE LESS THAN 1
        return " {\n\t\t" +
                "fragColor = mix(fragColor, " + name + ".color, " + name + ".color.a);\n\t" +
                "}";
    }
}
