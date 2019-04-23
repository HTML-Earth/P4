parser grammar SpookParser;
options { tokenVocab=SpookLexer; }



/*      PROGRAM START        */
// Shader() {} (Needed)
// Comments, Classes and functions can be declared outside (under) of the main function
program
    : main (comment | classDecl | functionDecl)*;




/*      MAIN             */
// Shader() {} (Needed), Declarations (Optional), Comments (Optional)
main
    : MAIN block;

// Main block
block: LEFT_BRACKET (statement | comment)* RIGHT_BRACKET;

// Comment: Single-line
comment
    : COMMENT_STRING;

// Statements
statement
    : declaration SEMICOLON
    | assignment SEMICOLON
    | functionCall SEMICOLON
    | conditionalStatement
    | iterativeStatement;




/*      DECLARATIONS       */
declaration
    : variableDecl
    | objectDecl;

// Variable declaration
variableDecl
    : dataType (variableName | assignment);

// Object declaration
objectDecl
    : (classType | className) objectVariableName ASSIGN LEFT_PAREN objectArgs* RIGHT_PAREN;




/*      ASSIGNMENT       */
assignment
    : variableName ASSIGN (expression | functionCall);

expression
    : arithExpression
    | vector2Expression
    | vector3Expression
    | vector4Expression
    | boolExpression
    | ternaryOperator;

// Expressions
arithExpression: lowPrecedence;
vector2Expression: LEFT_PAREN arithExpression COMMA arithExpression RIGHT_PAREN;
vector3Expression: LEFT_PAREN arithExpression COMMA arithExpression COMMA arithExpression RIGHT_PAREN;
vector4Expression: LEFT_PAREN arithExpression COMMA arithExpression COMMA arithExpression COMMA arithExpression RIGHT_PAREN;
boolExpression: BOOL_LITERAL | boolOperations;
ternaryOperator: boolExpression QUESTION expression COLON expression;

//Precedence, goes through low to high, ends at atom
lowPrecedence
    : highPrecedence ((ADD | SUB) highPrecedence)*;
highPrecedence
    : atomPrecedence ((MUL | MOD | DIV) atomPrecedence)*;
atomPrecedence
    : SUB? arithOperand
    | LEFT_PAREN lowPrecedence RIGHT_PAREN;

arithOperand
    : realNumber | mathFunction | variableName | UNIFORM;

// Mathematical functions
mathFunction
    : function LEFT_PAREN lowPrecedence RIGHT_PAREN;

// Recursive boolean operations
boolOperations
    : boolOperation boolOperations
    | boolOperation;

// Boolean operations
boolOperation
    : (BOOL_LITERAL | variableName) boolOperator (BOOL_LITERAL | variableName | (LEFT_PAREN boolOperation RIGHT_PAREN))
    | boolOperator (BOOL_LITERAL | variableName | (LEFT_PAREN boolOperation RIGHT_PAREN))
    | LEFT_PAREN boolOperation RIGHT_PAREN;




/*      FUNCTION CALLS       */
functionCall
    : nonObjectFunctionCall
    | objectFunctionCall;


// Non-object funtion calls (Global functions)
nonObjectFunctionCall
    : functionName LEFT_PAREN objectArgs? RIGHT_PAREN;

// Object function calls
objectFunctionCall
    : objectVariableName DOT functionName ASSIGN (objectArgs? | LEFT_PAREN objectArgs? RIGHT_PAREN)
    | objectVariableName DOT functionName LEFT_PAREN objectArgs? RIGHT_PAREN;

// Object arguments
objectArgs
    : objectArg COMMA objectArgs
    | objectArg;
objectArg
    : lowPrecedence
    | colorFunctionCall
    | functionCall;

// Color function call (Color.Black) etc.
colorFunctionCall
    : COLOR DOT (variableName | predefinedFunctionName);




/*      CONDITIONAL STATEMENTS      */
conditionalStatement
    : ifElseStatement;

// If-else if-else
ifElseStatement: IF LEFT_PAREN boolExpression RIGHT_PAREN (block | statement) (ELSE_IF LEFT_PAREN boolExpression RIGHT_PAREN (block | statement))* (ELSE (block | statement))?;




/*      LOOPS        */
iterativeStatement
    : forStatement;

// For loop
forStatement
    : FOR LEFT_PAREN forLoopExpression TO forLoopExpression RIGHT_PAREN (block | statement);

forLoopExpression: (DIGIT | variableDecl | variableName | assignment);




/*      CLASSES     */
// Class declaration
classDecl
    : CLASS className ((EXTENDS | IMPLEMENTS) classType)? classBlock;

// Class block
classBlock: LEFT_BRACKET (declaration | functionDecl | comment)* RIGHT_BRACKET;




/*      FUNCTIONS    */
/* Function declaration */
functionDecl
    : returnType functionName LEFT_PAREN functionArgs? RIGHT_PAREN functionBlock
    | VOID functionName LEFT_PAREN functionArgs? RIGHT_PAREN block;

functionArgs
    : functionArg COMMA functionArgs
    | functionArg;
functionArg
    : dataType variableName;

// Function block
functionBlock: LEFT_BRACKET (statement | comment)* returnStatement RIGHT_BRACKET;

// Return statement
returnStatement: RETURN (variableName | realNumber | BOOL_LITERAL | expression) SEMICOLON;




/*      HELPERS       */
// Numbers
realNumber: integerNumber | FLOAT_DIGIT | FLOAT_DIGIT_NEGATIVE;
integerNumber: DIGIT | DIGIT_NEGATIVE;

// Boolean operators
boolOperator
    : EQUAL
    | OR
    | AND
    | NOT_EQUAL
    | NOT;

// Math functions
function
    : ABS
    | SIN
    | COS
    | TAN;

// Return types
returnType
    : dataType;

// Pre-defined classes
classType
    : CIRCLE
    | RECTANGLE
    | TRIANGLE
    | SHAPE
    | COLOR
    | CIRCLEGRADIENT
    | LINEGRADIENT;

// Data types
dataType
    : INT
    | FLOAT
    | BOOL
    | VECTOR2
    | VECTOR3
    | VECTOR4;

predefinedFunctionName
    : colorName;
colorName
    : BLACK
    | WHITE
    | RED
    | GREEN
    | BLUE;

// Variable names
objectVariableName
    : ID;
functionName
    : ID;
variableName
    : ID;
className
    : ID;