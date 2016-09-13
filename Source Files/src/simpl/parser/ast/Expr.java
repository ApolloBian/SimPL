package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class Expr {

    public abstract TypeResult typecheck(TypeEnv E) throws TypeError;

    /**
     * relies on side effect
     * 
     * @param s
     * @return
     * @throws RuntimeError
     */
    //public int mark = 0;
    public abstract Value eval(State s) throws RuntimeError;
}
