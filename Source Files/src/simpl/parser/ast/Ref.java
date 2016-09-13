package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.Int;
import simpl.interpreter.IntValue;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e.typecheck(E);
        return TypeResult.of(t1.s,new RefType(t1.s.apply(t1.t)));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Int p = new Int(s.p.get());
        
        /*garbage collection*/
        if(p.get() > 4)
        {
        	System.out.println("EnterGC");
        	Env env = s.E;
        	while(env != Env.empty)
        	{
        		
        		Value val = env.getValue();
        		while(val.getClass().toString().intern() 
        				== "class simpl.interpreter.RefValue"
        				&&val.mark ==0)	
        		{	
        			val.mark = 1;
        			val = s.M.get(((RefValue)val).p);
        		}
        			val.mark = 1;
        			env = env.getEnv();
        	}
        	
        	/*
        	System.out.println("----before collection----");
        	for (int i = 0; i< p.get(); i++)
        	{
        			System.out.println(s.M.get(i));
        		
        	}
        	System.out.println("----end of memory----");
        	*/
        	
        	for (int i = 0; i< p.get(); i++)
        	{
        		if (s.M.get(i).mark == 0)
        		{
        			System.out.println("collect");
        			System.out.println(i);
        			s.M.put(i, null);
        		}
        	}
        	
        	/*
        	System.out.println("----after collection----");
        	for (int i = 0; i< p.get(); i++)
        	{
        			System.out.println(s.M.get(i));
        		
        	}
        	System.out.println("----end of memory----");
        	*/
        }
        /*GC end */
        
        s.p.set(s.p.get()+1);
        Value v1 = e.eval(s);
        s.M.put(p.get(), v1);
        RefValue p1 = new RefValue(p.get());
        //System.out.println(s.p.get());
        //System.out.println(v1);
        return p1;
        
        //return null;
    }
}
