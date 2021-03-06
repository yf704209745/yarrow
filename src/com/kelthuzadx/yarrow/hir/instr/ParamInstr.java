package com.kelthuzadx.yarrow.hir.instr;

import com.kelthuzadx.yarrow.util.Logger;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.JavaMethod;

public class ParamInstr extends HirInstr {
    private boolean isReceiver;
    private int index;
    private JavaMethod method;

    public ParamInstr(JavaKind type, JavaMethod method, boolean isReceiver, int index) {
        super(type);
        this.method = method;
        this.isReceiver = isReceiver;
        this.index = index;
    }

    @Override
    public String toString() {
        return Logger.format("i{}: param[{}]", super.id, isReceiver ? "this" : index);
    }
}
