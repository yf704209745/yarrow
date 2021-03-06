package com.kelthuzadx.yarrow.hir.instr;

import com.kelthuzadx.yarrow.util.Logger;
import jdk.vm.ci.meta.JavaField;

public class StoreFieldInstr extends AccessFieldInstr {
    private HirInstr value;

    public StoreFieldInstr(HirInstr object, int offset, JavaField field, HirInstr value) {
        super(object, offset, field);
        this.value = value;
    }


    @Override
    public String toString() {
        return Logger.format("i{}: i{}.off+{} = i{} [{}.{}]",
                super.id, super.object.id, super.offset, value.id,
                super.field.getDeclaringClass().getUnqualifiedName(), super.field.getName());
    }
}
