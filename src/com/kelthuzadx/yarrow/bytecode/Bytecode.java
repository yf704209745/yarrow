package com.kelthuzadx.yarrow.bytecode;

import java.lang.reflect.Field;

public class Bytecode {
    public static final int NOP = 0; // 0x00
    public static final int ACONST_NULL = 1; // 0x01
    public static final int ICONST_M1 = 2; // 0x02
    public static final int ICONST_0 = 3; // 0x03
    public static final int ICONST_1 = 4; // 0x04
    public static final int ICONST_2 = 5; // 0x05
    public static final int ICONST_3 = 6; // 0x06
    public static final int ICONST_4 = 7; // 0x07
    public static final int ICONST_5 = 8; // 0x08
    public static final int LCONST_0 = 9; // 0x09
    public static final int LCONST_1 = 10; // 0x0A
    public static final int FCONST_0 = 11; // 0x0B
    public static final int FCONST_1 = 12; // 0x0C
    public static final int FCONST_2 = 13; // 0x0D
    public static final int DCONST_0 = 14; // 0x0E
    public static final int DCONST_1 = 15; // 0x0F
    public static final int BIPUSH = 16; // 0x10
    public static final int SIPUSH = 17; // 0x11
    public static final int LDC = 18; // 0x12
    public static final int LDC_W = 19; // 0x13
    public static final int LDC2_W = 20; // 0x14
    public static final int ILOAD = 21; // 0x15
    public static final int LLOAD = 22; // 0x16
    public static final int FLOAD = 23; // 0x17
    public static final int DLOAD = 24; // 0x18
    public static final int ALOAD = 25; // 0x19
    public static final int ILOAD_0 = 26; // 0x1A
    public static final int ILOAD_1 = 27; // 0x1B
    public static final int ILOAD_2 = 28; // 0x1C
    public static final int ILOAD_3 = 29; // 0x1D
    public static final int LLOAD_0 = 30; // 0x1E
    public static final int LLOAD_1 = 31; // 0x1F
    public static final int LLOAD_2 = 32; // 0x20
    public static final int LLOAD_3 = 33; // 0x21
    public static final int FLOAD_0 = 34; // 0x22
    public static final int FLOAD_1 = 35; // 0x23
    public static final int FLOAD_2 = 36; // 0x24
    public static final int FLOAD_3 = 37; // 0x25
    public static final int DLOAD_0 = 38; // 0x26
    public static final int DLOAD_1 = 39; // 0x27
    public static final int DLOAD_2 = 40; // 0x28
    public static final int DLOAD_3 = 41; // 0x29
    public static final int ALOAD_0 = 42; // 0x2A
    public static final int ALOAD_1 = 43; // 0x2B
    public static final int ALOAD_2 = 44; // 0x2C
    public static final int ALOAD_3 = 45; // 0x2D
    public static final int IALOAD = 46; // 0x2E
    public static final int LALOAD = 47; // 0x2F
    public static final int FALOAD = 48; // 0x30
    public static final int DALOAD = 49; // 0x31
    public static final int AALOAD = 50; // 0x32
    public static final int BALOAD = 51; // 0x33
    public static final int CALOAD = 52; // 0x34
    public static final int SALOAD = 53; // 0x35
    public static final int ISTORE = 54; // 0x36
    public static final int LSTORE = 55; // 0x37
    public static final int FSTORE = 56; // 0x38
    public static final int DSTORE = 57; // 0x39
    public static final int ASTORE = 58; // 0x3A
    public static final int ISTORE_0 = 59; // 0x3B
    public static final int ISTORE_1 = 60; // 0x3C
    public static final int ISTORE_2 = 61; // 0x3D
    public static final int ISTORE_3 = 62; // 0x3E
    public static final int LSTORE_0 = 63; // 0x3F
    public static final int LSTORE_1 = 64; // 0x40
    public static final int LSTORE_2 = 65; // 0x41
    public static final int LSTORE_3 = 66; // 0x42
    public static final int FSTORE_0 = 67; // 0x43
    public static final int FSTORE_1 = 68; // 0x44
    public static final int FSTORE_2 = 69; // 0x45
    public static final int FSTORE_3 = 70; // 0x46
    public static final int DSTORE_0 = 71; // 0x47
    public static final int DSTORE_1 = 72; // 0x48
    public static final int DSTORE_2 = 73; // 0x49
    public static final int DSTORE_3 = 74; // 0x4A
    public static final int ASTORE_0 = 75; // 0x4B
    public static final int ASTORE_1 = 76; // 0x4C
    public static final int ASTORE_2 = 77; // 0x4D
    public static final int ASTORE_3 = 78; // 0x4E
    public static final int IASTORE = 79; // 0x4F
    public static final int LASTORE = 80; // 0x50
    public static final int FASTORE = 81; // 0x51
    public static final int DASTORE = 82; // 0x52
    public static final int AASTORE = 83; // 0x53
    public static final int BASTORE = 84; // 0x54
    public static final int CASTORE = 85; // 0x55
    public static final int SASTORE = 86; // 0x56
    public static final int POP = 87; // 0x57
    public static final int POP2 = 88; // 0x58
    public static final int DUP = 89; // 0x59
    public static final int DUP_X1 = 90; // 0x5A
    public static final int DUP_X2 = 91; // 0x5B
    public static final int DUP2 = 92; // 0x5C
    public static final int DUP2_X1 = 93; // 0x5D
    public static final int DUP2_X2 = 94; // 0x5E
    public static final int SWAP = 95; // 0x5F
    public static final int IADD = 96; // 0x60
    public static final int LADD = 97; // 0x61
    public static final int FADD = 98; // 0x62
    public static final int DADD = 99; // 0x63
    public static final int ISUB = 100; // 0x64
    public static final int LSUB = 101; // 0x65
    public static final int FSUB = 102; // 0x66
    public static final int DSUB = 103; // 0x67
    public static final int IMUL = 104; // 0x68
    public static final int LMUL = 105; // 0x69
    public static final int FMUL = 106; // 0x6A
    public static final int DMUL = 107; // 0x6B
    public static final int IDIV = 108; // 0x6C
    public static final int LDIV = 109; // 0x6D
    public static final int FDIV = 110; // 0x6E
    public static final int DDIV = 111; // 0x6F
    public static final int IREM = 112; // 0x70
    public static final int LREM = 113; // 0x71
    public static final int FREM = 114; // 0x72
    public static final int DREM = 115; // 0x73
    public static final int INEG = 116; // 0x74
    public static final int LNEG = 117; // 0x75
    public static final int FNEG = 118; // 0x76
    public static final int DNEG = 119; // 0x77
    public static final int ISHL = 120; // 0x78
    public static final int LSHL = 121; // 0x79
    public static final int ISHR = 122; // 0x7A
    public static final int LSHR = 123; // 0x7B
    public static final int IUSHR = 124; // 0x7C
    public static final int LUSHR = 125; // 0x7D
    public static final int IAND = 126; // 0x7E
    public static final int LAND = 127; // 0x7F
    public static final int IOR = 128; // 0x80
    public static final int LOR = 129; // 0x81
    public static final int IXOR = 130; // 0x82
    public static final int LXOR = 131; // 0x83
    public static final int IINC = 132; // 0x84
    public static final int I2L = 133; // 0x85
    public static final int I2F = 134; // 0x86
    public static final int I2D = 135; // 0x87
    public static final int L2I = 136; // 0x88
    public static final int L2F = 137; // 0x89
    public static final int L2D = 138; // 0x8A
    public static final int F2I = 139; // 0x8B
    public static final int F2L = 140; // 0x8C
    public static final int F2D = 141; // 0x8D
    public static final int D2I = 142; // 0x8E
    public static final int D2L = 143; // 0x8F
    public static final int D2F = 144; // 0x90
    public static final int I2B = 145; // 0x91
    public static final int I2C = 146; // 0x92
    public static final int I2S = 147; // 0x93
    public static final int LCMP = 148; // 0x94
    public static final int FCMPL = 149; // 0x95
    public static final int FCMPG = 150; // 0x96
    public static final int DCMPL = 151; // 0x97
    public static final int DCMPG = 152; // 0x98
    public static final int IFEQ = 153; // 0x99
    public static final int IFNE = 154; // 0x9A
    public static final int IFLT = 155; // 0x9B
    public static final int IFGE = 156; // 0x9C
    public static final int IFGT = 157; // 0x9D
    public static final int IFLE = 158; // 0x9E
    public static final int IF_ICMPEQ = 159; // 0x9F
    public static final int IF_ICMPNE = 160; // 0xA0
    public static final int IF_ICMPLT = 161; // 0xA1
    public static final int IF_ICMPGE = 162; // 0xA2
    public static final int IF_ICMPGT = 163; // 0xA3
    public static final int IF_ICMPLE = 164; // 0xA4
    public static final int IF_ACMPEQ = 165; // 0xA5
    public static final int IF_ACMPNE = 166; // 0xA6
    public static final int GOTO = 167; // 0xA7
    public static final int JSR = 168; // 0xA8
    public static final int RET = 169; // 0xA9
    public static final int TABLESWITCH = 170; // 0xAA
    public static final int LOOKUPSWITCH = 171; // 0xAB
    public static final int IRETURN = 172; // 0xAC
    public static final int LRETURN = 173; // 0xAD
    public static final int FRETURN = 174; // 0xAE
    public static final int DRETURN = 175; // 0xAF
    public static final int ARETURN = 176; // 0xB0
    public static final int RETURN = 177; // 0xB1
    public static final int GETSTATIC = 178; // 0xB2
    public static final int PUTSTATIC = 179; // 0xB3
    public static final int GETFIELD = 180; // 0xB4
    public static final int PUTFIELD = 181; // 0xB5
    public static final int INVOKEVIRTUAL = 182; // 0xB6
    public static final int INVOKESPECIAL = 183; // 0xB7
    public static final int INVOKESTATIC = 184; // 0xB8
    public static final int INVOKEINTERFACE = 185; // 0xB9
    public static final int INVOKEDYNAMIC = 186; // 0xBA
    public static final int NEW = 187; // 0xBB
    public static final int NEWARRAY = 188; // 0xBC
    public static final int ANEWARRAY = 189; // 0xBD
    public static final int ARRAYLENGTH = 190; // 0xBE
    public static final int ATHROW = 191; // 0xBF
    public static final int CHECKCAST = 192; // 0xC0
    public static final int INSTANCEOF = 193; // 0xC1
    public static final int MONITORENTER = 194; // 0xC2
    public static final int MONITOREXIT = 195; // 0xC3
    public static final int WIDE = 196; // 0xC4
    public static final int MULTIANEWARRAY = 197; // 0xC5
    public static final int IFNULL = 198; // 0xC6
    public static final int IFNONNULL = 199; // 0xC7
    public static final int GOTO_W = 200; // 0xC8
    public static final int JSR_W = 201; // 0xC9
    public static final int BREAKPOINT = 202; // 0xCA

    public static final int ILLEGAL = 255;
    public static final int END = 256;

    public static String forName(int bc) {
        Field[] fs = Bytecode.class.getFields();
        try {
            for (Field field : fs) {
                field.setAccessible(true);
                if (field.getInt(null) == bc) {
                    return field.getName().toLowerCase();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "<NotFound>";
    }

    public static boolean canTrap(int bc) {
        switch (bc) {
            case Bytecode.LDC:
            case Bytecode.LDC_W:
            case Bytecode.LDC2_W:
            case Bytecode.IALOAD:
            case Bytecode.LALOAD:
            case Bytecode.FALOAD:
            case Bytecode.DALOAD:
            case Bytecode.AALOAD:
            case Bytecode.BALOAD:
            case Bytecode.CALOAD:
            case Bytecode.SALOAD:
            case Bytecode.IASTORE:
            case Bytecode.LASTORE:
            case Bytecode.FASTORE:
            case Bytecode.DASTORE:
            case Bytecode.AASTORE:
            case Bytecode.BASTORE:
            case Bytecode.CASTORE:
            case Bytecode.SASTORE:
            case Bytecode.IDIV:
            case Bytecode.LDIV:
            case Bytecode.IREM:
            case Bytecode.LREM:
            case Bytecode.GETSTATIC:
            case Bytecode.PUTSTATIC:
            case Bytecode.GETFIELD:
            case Bytecode.PUTFIELD:
            case Bytecode.INVOKEVIRTUAL:
            case Bytecode.INVOKESPECIAL:
            case Bytecode.INVOKESTATIC:
            case Bytecode.INVOKEDYNAMIC:
            case Bytecode.INVOKEINTERFACE:
            case Bytecode.NEW:
            case Bytecode.NEWARRAY:
            case Bytecode.ANEWARRAY:
            case Bytecode.ARRAYLENGTH:
            case Bytecode.ATHROW:
            case Bytecode.CHECKCAST:
            case Bytecode.INSTANCEOF:
            case Bytecode.MONITORENTER:
            case Bytecode.MULTIANEWARRAY:
                return true;
        }
        return false;
    }
}
