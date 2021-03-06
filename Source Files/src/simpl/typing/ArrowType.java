package simpl.typing;

public final class ArrowType extends Type {

    public Type t1, t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return false;
        //return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        if (t instanceof ArrowType) {
            ArrowType t1 = (ArrowType) t;
            //System.out.println("unify " + t1 + "        " + this);
            Substitution s1 = t1.t2.unify(this.t2);
            Substitution s2 = s1.apply(t1.t1).unify(s1.apply(this.t1));
            return s2.compose(s1);
        }
        throw new TypeMismatchError();
        //return null;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return this.t1.contains(tv) || this.t2.contains(tv);
        //return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        return new ArrowType(this.t1.replace(a, t),this.t2.replace(a, t));
        //return null;
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
