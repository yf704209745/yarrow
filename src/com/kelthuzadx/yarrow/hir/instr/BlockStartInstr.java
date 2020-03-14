package com.kelthuzadx.yarrow.hir.instr;

import com.kelthuzadx.yarrow.hir.Value;
import com.kelthuzadx.yarrow.hir.VmState;
import com.kelthuzadx.yarrow.util.CompilerErrors;
import com.kelthuzadx.yarrow.util.Constraint;
import com.kelthuzadx.yarrow.util.Logger;
import jdk.vm.ci.meta.ExceptionHandler;
import jdk.vm.ci.meta.JavaKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BlockStartInstr extends StateInstr {
    // For constructing control flow graph
    private int blockId;
    private int startBci;
    private int endBci;
    // Successor of this block, when HIR construction accomplish, it will be cleared
    private List<BlockStartInstr> successor;
    private boolean mayThrowEx;
    private boolean loopHeader;
    private ExceptionHandler xhandler;

    // For instruction itself
    private BlockEndInstr blockEnd;
    private List<BlockStartInstr> predecessor;

    public BlockStartInstr(int blockId, int bci) {
        super(new Value(JavaKind.Illegal), null);
        this.blockId = blockId;
        this.startBci = this.endBci = bci;
        this.successor = new ArrayList<>();
        this.predecessor = new ArrayList<>();
        this.mayThrowEx = false;
        this.loopHeader = false;
        this.blockEnd = null;
    }

    public int getEndBci() {
        return endBci;
    }

    public void setEndBci(int endBci) {
        this.endBci = endBci;
    }

    public int getStartBci() {
        return startBci;
    }

    public void setStartBci(int startBci) {
        this.startBci = startBci;
    }

    public void addSuccessor(BlockStartInstr block) {
        this.successor.add(block);
    }

    public List<BlockStartInstr> getSuccessor() {
        return successor;
    }

    public List<BlockStartInstr> getPredecessor() {
        return predecessor;
    }

    public void removeSuccessor() {
        this.successor.clear();
    }

    public boolean isLoopHeader() {
        return loopHeader;
    }

    public void setLoopHeader(boolean loopHeader) {
        this.loopHeader = loopHeader;
    }

    public boolean isMayThrowEx() {
        return mayThrowEx;
    }

    public void setMayThrowEx(boolean mayThrowEx) {
        this.mayThrowEx = mayThrowEx;
    }

    public int getBlockId() {
        return blockId;
    }

    public ExceptionHandler getXhandler() {
        return xhandler;
    }

    public void setXhandler(ExceptionHandler xhandler) {
        this.xhandler = xhandler;
    }

    public BlockEndInstr getBlockEnd() {
        return blockEnd;
    }

    public void setBlockEnd(BlockEndInstr blockEnd) {
        // Connect to BlockEndInstr and remove BlockStartInstr's successors
        this.blockEnd = blockEnd;
        this.removeSuccessor();

        // Set predecessors of BlockStartInstr
        for (BlockStartInstr succ : blockEnd.getSuccessor()) {
            succ.getPredecessor().add(this);
        }
        blockEnd.setBlockStart(this);
    }

    /**
     * If a block has more than one predecessor, PhiInstrc might be needed at
     * the beginning of this block. If I find different values of the same variable,
     * I will merge existing VmState(this.getVmState()) and new VmState.
     * @param newState state of one of predecessors
     */
    public void mergeVmState(VmState newState) {
        if (getVmState() == null) {
            VmState state = newState.copy();
            if (this.isLoopHeader()) {
                for (int i = 0; i < state.getStackSize(); i++) {
                    state.createPhiForStack(this, i);
                }
                for (int i = 0; i < state.getLocalSize(); i++) {
                    state.createPhiForLocal(this, i);
                }
            }
            setVmState(state);
        } else {
            Constraint.matchVmState(getVmState(), newState);
            if (this.isLoopHeader()) {
                for (int i = 0; i < newState.getLocalSize(); i++) {
                    if (newState.get(i) == null || !newState.get(i).isType(getVmState().get(i).getType())) {
                        CompilerErrors.bailOut();
                    }
                }
            } else {
                for (int i = 0; i < getVmState().getStackSize(); i++) {
                    Instruction val = newState.getStack().get(i);
                    if(val!=newState.getStack().get(i) ||
                            (!(val instanceof PhiInstr) && ((PhiInstr)val).getBlock()!=this)
                    ){
                        getVmState().createPhiForStack(this,i);
                    }
                }
                for (int i = 0; i < getVmState().getLocalSize(); i++) {
                    Instruction val = newState.getLocal()[i];
                    if(val!=null && !Instruction.matchType(val,newState.getLocal()[i])){
                        if(val!=newState.getLocal()[i] ||
                                (!(val instanceof PhiInstr) && ((PhiInstr)val).getBlock()!=this)
                        ){
                            getVmState().createPhiForLocal(this,i);
                        }
                    }else{
                        getVmState().getLocal()[i] = null;
                    }
                }
            }
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BlockStartInstr) {
            return ((BlockStartInstr) obj).blockId == this.blockId;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId);
    }

    public String toCFGString() {
        String successorString = successor.stream().map(
                b -> "#" + b.getBlockId() + " " + (b.xhandler != null ? "!" : "") + "[" + b.getStartBci() + "," + b.getEndBci() + "]"
        ).collect(Collectors.toList()).toString();

        return "#" +
                blockId +
                " " +
                (xhandler != null ? "!" : "") +
                "[" +
                startBci +
                "," +
                endBci +
                "]" +
                " => " +
                successorString;
    }


    @Override
    public String toString() {
        return Logger.format("i{}: block_start", super.id);
    }
}
