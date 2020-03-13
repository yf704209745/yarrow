package com.kelthuzadx.yarrow.hir.instr;

import com.kelthuzadx.yarrow.hir.Value;
import com.kelthuzadx.yarrow.hir.VmState;
import jdk.vm.ci.meta.JavaKind;

import java.util.List;

public class TableSwitchInstr extends BlockEndInstr {
    private Instruction index;
    private int lowKey;
    private int highKey;

    public TableSwitchInstr(VmState stateBefore, List<BlockStartInstr> successor, Instruction index, int lowKey, int highKey) {
        super(new Value(JavaKind.Illegal), stateBefore, successor);
        this.index = index;
        this.lowKey = lowKey;
        this.highKey = highKey;
    }

    @Override
    public String toString() {
        return "TableSwitchInstr";
    }
}
