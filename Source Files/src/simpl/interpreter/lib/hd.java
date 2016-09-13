package simpl.interpreter.lib;

import javafx.scene.control.ListView;
import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class hd extends FunValue {

    public hd() {
        // TODO
        super(Env.empty, Symbol.symbol("hd argument"),new Expr(){
            
    public  TypeResult typecheck(TypeEnv E) throws TypeError{
        return TypeResult.of(new TypeVar(true));
    }

    public Value eval(State s) throws RuntimeError{
        ConsValue p = (ConsValue) s.E.get(Symbol.symbol("hd argument"));
        
        return p.v1;
    }
        });
    }
}
