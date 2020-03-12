package com.kelthuzadx.yarrow.hir.instr;

import com.kelthuzadx.yarrow.hir.Value;
import jdk.vm.ci.meta.JavaKind;

public class PhiInstr extends Instruction {
    private int index; // negate number for stack, and positive number for local
    private BlockStartInstr block;

    public PhiInstr(Value value, int index, BlockStartInstr block) {
        super(value);
        this.index = index;
        this.block = block;
    }
}