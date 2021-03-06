package com.kelthuzadx.yarrow.hir;

import com.kelthuzadx.yarrow.hir.instr.BlockEndInstr;
import com.kelthuzadx.yarrow.hir.instr.BlockStartInstr;
import com.kelthuzadx.yarrow.hir.instr.HirInstr;
import com.kelthuzadx.yarrow.util.Logger;
import com.kelthuzadx.yarrow.util.Mode;
import jdk.vm.ci.hotspot.HotSpotResolvedJavaMethod;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Hir {
    private HotSpotResolvedJavaMethod method;
    private BlockStartInstr entry;
    private boolean writeFinal;
    private boolean writeVolatile;

    public Hir(HotSpotResolvedJavaMethod method, BlockStartInstr entry) {
        this.writeFinal = false;
        this.writeVolatile = false;
        this.entry = entry;
        this.method = method;
    }

    private static void iterateBytecodes(BlockStartInstr block, Consumer<HirInstr> closure) {
        HirInstr last = block;
        while (last != null && last != block.getBlockEnd()) {
            closure.accept(last);
            last = last.getNext();
        }
        if (last != null && last == block.getBlockEnd()) {
            closure.accept(last);
        }
    }

    private static void printHIR(Set<BlockStartInstr> visit, BlockStartInstr block) {
        if (block == null || visit.contains(block)) {
            return;
        }
        Logger.logf("{}", block.getVmState().toString());
        iterateBytecodes(block, instr -> Logger.logf("{}", instr.toString()));
        Logger.logf("");
        visit.add(block);
        for (BlockStartInstr succ : block.getBlockEnd().getSuccessor()) {
            printHIR(visit, succ);
        }
    }

    private static void printHIRToFile(Set<BlockStartInstr> visit, BlockStartInstr block, StringBuilder content) {
        if (block == null || visit.contains(block)) {
            return;
        }
        // Block successors
        BlockEndInstr end = block.getBlockEnd();
        for (BlockStartInstr succ : end.getSuccessor()) {
            content.append("\tB").append(block.id()).append("-> B").append(succ.id()).append(";\n");
        }
        // Block itself
        content.append("\tB").append(block.id()).append("[shape=record,label=\"");
        content.append("{ i").append(block.id()).append(" | ");
        HirInstr start = block;
        String temp = "";
        while (start != end) {
            temp = start.toString();
            // escape "<" and ">" in graphviz record text
            temp = temp.replaceAll("<", "\\\\<");
            temp = temp.replaceAll(">", "\\\\>");
            content.append(temp).append("\\l"); //left align
            start = start.getNext();
        }
        temp = start.toString();
        temp = temp.replaceAll("<", "\\\\<");
        temp = temp.replaceAll(">", "\\\\>");
        content.append(temp).append("\\l");
        content.append("}\"];\n");


        visit.add(block);
        for (BlockStartInstr succ : block.getBlockEnd().getSuccessor()) {
            printHIRToFile(visit, succ, content);
        }
    }

    public BlockStartInstr getEntryBlock() {
        return entry;
    }

    public void printHIR(boolean toFile) {
        if (!toFile) {
            printHIR(new HashSet<>(), entry);
        } else {
            StringBuilder content = new StringBuilder();
            content.append("digraph G{\n");
            content.append("\tgraph [ dpi = 500 ];\n");
            printHIRToFile(new HashSet<>(), entry, content);
            content.append("}");
            String fileName = method.getDeclaringClass().getUnqualifiedName() + "_" +
                    method.getName() + "_phase1.dot";
            fileName = fileName.replaceAll("<", "");
            fileName = fileName.replaceAll(">", "");
            Logger.log(Mode.File, fileName, content.toString());
        }
    }

    public void setWriteFinal() {
        this.writeFinal = true;
    }

    public void setWriteVolatile() {
        this.writeVolatile = true;
    }

    public boolean isWriteFinal() {
        return writeFinal;
    }

    public boolean isWriteVolatile() {
        return writeVolatile;
    }
}
